import * as S from "./style";
import Header from "../../components/normal_header/nh";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";
import type { HobbyTestResponse, HobbyScoreResponse, ReviewAnswerResponse } from "../../types/hobby";
import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import { hobbyApi } from "../../api/hobby";
import { authApi } from "../../api/auth";

const normalizeToRange = (value: number) => {
  return ((value + 1) / 4) * 4 - 2;
};

export default function TestResult() {
  const navigate = useNavigate();
  const location = useLocation();
  const { finalAverage, result } = location.state || {};
  const testResult = result as HobbyTestResponse;

  // 모달 상태
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedHobby, setSelectedHobby] = useState<HobbyScoreResponse | null>(null);
  const [reviewQuestion, setReviewQuestion] = useState("");
  const [activityReviewId, setActivityReviewId] = useState<number | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  
  // 5개 카테고리 답변
  const [answers, setAnswers] = useState({
    socialAnswer: "",
    learningAnswer: "",
    planningAnswer: "",
    focusAnswer: "",
    creativityAnswer: "",
  });

  // 새로 추천된 취미
  const [newRecommendations, setNewRecommendations] = useState<ReviewAnswerResponse | null>(null);

  if (!finalAverage) return null;

  const categories = [
    { label: "사회성", value: normalizeToRange(finalAverage.socialScore) },
    { label: "학습력", value: normalizeToRange(finalAverage.learningScore) },
    { label: "계획력", value: normalizeToRange(finalAverage.planningScore) },
    { label: "집중력", value: normalizeToRange(finalAverage.focusScore) },
    { label: "창의성", value: normalizeToRange(finalAverage.creativityScore) },
  ];

  const BAR_UNIT = 175;
  const maxAbsValue = Math.max(...categories.map((c) => Math.abs(c.value)));

  return (
    <S.Background>
      <Header />

      <S.Wrrapper>
        <S.MainColumn>
          <S.Title>취향 테스트 후 결과</S.Title>

          {/* ================= 그래프 ================= */}
          <S.GraphSection>
            <S.RelativeChart>
              {categories.map((item) => (
                <S.RelativeBarItem key={item.label}>
                  <S.BarContainer $height={maxAbsValue * BAR_UNIT}>
                    <S.PositiveArea>
                      {item.value > 0 && (
                        <S.PositiveBar $value={item.value} $unit={BAR_UNIT} />
                      )}
                    </S.PositiveArea>

                    <S.ZeroLine />

                    <S.NegativeArea>
                      {item.value < 0 && (
                        <S.NegativeBar $value={item.value} $unit={BAR_UNIT} />
                      )}
                    </S.NegativeArea>
                  </S.BarContainer>

                  <S.ScoreArea>
                    <S.BarValue>{item.value.toFixed(2)}</S.BarValue>
                    <S.BarLabel>{item.label}</S.BarLabel>
                  </S.ScoreArea>
                </S.RelativeBarItem>
              ))}
            </S.RelativeChart>
          </S.GraphSection>

          {/* ================= 추천 ================= */}
          <S.GraphTitle>추천 활동</S.GraphTitle>

          <S.RecommendSection>
            <S.RecommendRow>
              {(newRecommendations ? [
                { hobbyName: newRecommendations.hobby1, hobbyId: undefined, distance: 0 },
                { hobbyName: newRecommendations.hobby2, hobbyId: undefined, distance: 0 },
                { hobbyName: newRecommendations.hobby3, hobbyId: undefined, distance: 0 },
              ] : testResult?.hobbyScores?.slice(0, 3))?.map((hobby: HobbyScoreResponse) => (
                  <S.RecommaendBox key={hobby.hobbyName}>
                    {hobby.hobbyName}
                    <S.addTree
                      onClick={async () => {
                        try {
                          setIsLoading(true);
                          // 1. 사용자 정보 가져오기
                          const userRes = await authApi.findCurrentUser();
                          if (!userRes.success) {
                            toast.error("로그인이 필요합니다.");
                            navigate("/login", { state: { from: "hobby-test" } });
                            return;
                          }
                          
                          // 2. 취미 ID 찾기 (이름으로 전체 목록에서 검색)
                          const allHobbiesRes = await hobbyApi.findAllHobbies();
                          if (!allHobbiesRes.success) {
                            toast.error("취미 정보를 가져오는데 실패했습니다.");
                            return;
                          }
                          
                          const foundHobby = allHobbiesRes.data.hobbies.find(
                            (h) => h.hobbyName === hobby.hobbyName
                          );
                          
                          if (!foundHobby) {
                            toast.error("해당 취미를 찾을 수 없습니다.");
                            return;
                          }
                          
                          // 3. 활동 추가 API 호출
                          const addRes = await hobbyApi.addActivity({
                            hobbyId: foundHobby.hobbyId,
                            userEmail: userRes.data.userEmail,
                          });
                          
                          if (addRes.success) {
                            toast.success(`${hobby.hobbyName} 활동이 나무에 추가되었습니다!`);
                            
                            // 4. 활동 리뷰 질문 생성 API 호출
                            const reviewRes = await hobbyApi.createReviewQuestion({
                              hobbyId: foundHobby.hobbyId,
                            });
                            
                            if (reviewRes.success) {
                              setSelectedHobby({ ...hobby, hobbyId: foundHobby.hobbyId });
                              setReviewQuestion(reviewRes.data.reviewQuestion);
                              setActivityReviewId(foundHobby.hobbyId); // hobbyId를 activityReviewId로 사용
                              setIsModalOpen(true);
                            }
                          } else {
                            toast.error("활동 추가에 실패했습니다.");
                          }
                        } catch (error) {
                          console.error(error);
                          toast.error("오류가 발생했습니다.");
                        } finally {
                          setIsLoading(false);
                        }
                      }}
                      disabled={isLoading}
                    >
                      {isLoading ? "처리중..." : "나무에 추가"}
                    </S.addTree>
                  </S.RecommaendBox>
                ))}

              <S.ArrowImage onClick={() => navigate("/explore/taste")}>
                <img src={Arrow} alt="메인으로 이동" />
              </S.ArrowImage>
            </S.RecommendRow>
          </S.RecommendSection>
        </S.MainColumn>
      </S.Wrrapper>
    </S.Background>
  );
}
