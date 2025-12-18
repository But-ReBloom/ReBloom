import * as S from "./style";
import Header from "../../components/normal_header/nh";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";
import type { HobbyTestResponse } from "../../types/hobby";

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
              {testResult?.hobbyScores?.slice(0, 3).map((hobby) => (
                <S.RecommaendBox key={hobby.hobbyName}>
                  {hobby.hobbyName}
                  <S.addTree
                    onClick={() => {
                      // 선택된 hobby를 activity 형태로 만들어서 전달
                      const newActivity = {
                        activityId: Date.now(), // 임시 ID
                        activityName: hobby.hobbyName,
                        activityStart: new Date().toISOString().slice(0, 10),
                        activityRecent: new Date().toISOString().slice(0, 10),
                        linkedHobbyId: hobby.hobbyId,
                        linkedHobbyName: hobby.hobbyName,
                        // 추가: 좌표값 지정 (원하는 위치로)
                        top: "480px", // 예: 원하는 top 값
                        left: "45%", // 예: 원하는 left 값
                      };

                      navigate("/mypage", {
                        state: {
                          newActivities: [newActivity], // 배열로 전달
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
