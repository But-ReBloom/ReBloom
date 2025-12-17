import * as S from "./style";
import Header from "../../components/normal_header/nh";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";
import type { HobbyTestResponse } from "../../types/hobby";

/* ===============================
   FTTP 결과 보정 함수
================================ */
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

  /* ===============================
     그래프 최대 높이 계산
     기준: value 2 → 350px
  ================================ */
  const BAR_UNIT = 175; // 1점당 px
  const maxBarHeight =
    Math.max(...categories.map((c) => Math.abs(c.value))) * BAR_UNIT;

  return (
    <S.Background>
      <Header />

      <S.Wrrapper>
        <S.MainColumn>
          <S.Title>알고리즘 테스트 결과</S.Title>

          {/* ================= 그래프 ================= */}
          <S.GraphSection $graphHeight={maxBarHeight}>
            <S.GraphTitle>카테고리별 상대 점수</S.GraphTitle>

            <S.RelativeChart>
              {categories.map((item) => (
                <S.RelativeBarItem key={item.label}>
                  <S.RelativeBarWrapper $height={maxBarHeight}>
                    <S.RelativeBar $value={item.value} $unit={BAR_UNIT} />
                  </S.RelativeBarWrapper>

                  <S.BarValue>{item.value.toFixed(2)}</S.BarValue>
                  <S.BarLabel>{item.label}</S.BarLabel>
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
                </S.RecommaendBox>
              ))}
              {!testResult?.hobbyScores && (
                <>
                  <S.RecommaendBox>토론 활동</S.RecommaendBox>
                  <S.RecommaendBox>문제 해결 프로젝트</S.RecommaendBox>
                  <S.RecommaendBox>집중력 강화 훈련</S.RecommaendBox>
                </>
              )}
            </S.RecommendRow>

            <S.ArrowImage onClick={() => navigate("/")}>
              <img src={Arrow} alt="메인으로 이동" />
            </S.ArrowImage>
          </S.RecommendSection>
        </S.MainColumn>
      </S.Wrrapper>
    </S.Background>
  );
}
