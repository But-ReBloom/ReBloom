import * as S from "./style";
import Header from "../../components/normal_header/nh";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";
import type { HobbyTestResponse, HobbyScoreResponse } from "../../types/hobby";

const normalizeToRange = (value: number) => {
  return ((value + 1) / 4) * 4 - 2;
};

export default function TestResult() {
  const navigate = useNavigate();
  const location = useLocation();
  const { finalAverage, result } = location.state || {};
  const testResult = result as HobbyTestResponse;

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
              {testResult?.hobbyScores
                ?.slice(0, 3)
                .map((hobby: HobbyScoreResponse) => (
                  <S.RecommaendBox key={hobby.hobbyName}>
                    {hobby.hobbyName}
                    <S.addTree
                      onClick={() => {
                        // hobbyId가 없으면 임시 ID 사용
                        const newActivity = {
                          activityId: hobby.hobbyId ?? Date.now(),
                          activityName: hobby.hobbyName,
                          activityStart: new Date().toISOString().slice(0, 10),
                          activityRecent: new Date().toISOString().slice(0, 10),
                          linkedHobbyId: hobby.hobbyId ?? Date.now(),
                          linkedHobbyName: hobby.hobbyName,
                          top: "480px", // 좌표값 예시
                          left: "45%",
                        };

                        navigate("/mypage", {
                          state: {
                            newActivities: [newActivity],
                          },
                        });
                      }}
                    >
                      나무에 추가
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
