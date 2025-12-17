import * as S from "./style";
import Header from "../../components/normal_header/nh";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";

/* ===============================
   FTTP 결과 보정 함수
   FTTP 결과 범위: -1 ~ 3
   그래프 범위:     -2 ~ 2
================================ */
const normalizeToRange = (value: number) => {
  return ((value + 1) / 4) * 4 - 2;
};

export default function TestResult() {
  const navigate = useNavigate();
  const location = useLocation();
  const { finalAverage } = location.state || {};

  if (!finalAverage) return null;

  const categories = [
    { label: "사회성", value: normalizeToRange(finalAverage.social) },
    { label: "학습력", value: normalizeToRange(finalAverage.learning) },
    { label: "계획력", value: normalizeToRange(finalAverage.planning) },
    { label: "집중력", value: normalizeToRange(finalAverage.focus) },
    { label: "창의성", value: normalizeToRange(finalAverage.creativity) },
  ];

  return (
    <S.Background>
      <Header />

      <S.Wrrapper>
        <S.MainColumn>
          <S.Title>알고리즘 테스트 결과</S.Title>

          {/* ================= 그래프 ================= */}
          <S.GraphSection>
            <S.GraphTitle>카테고리별 상대 점수</S.GraphTitle>

            <S.RelativeChart>
              {categories.map((item) => (
                <div
                  style={{
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    gap: "8px",
                  }}
                  key={item.label}
                >
                  <S.RelativeBarItem key={item.label}>
                    <S.RelativeBarWrapper>
                      <S.RelativeBar $value={item.value} />
                    </S.RelativeBarWrapper>
                  </S.RelativeBarItem>
                  <S.BarValue>{item.value.toFixed(2)}</S.BarValue>
                  <S.BarLabel>{item.label}</S.BarLabel>
                </div>
              ))}
            </S.RelativeChart>
          </S.GraphSection>

          {/* ================= 추천 활동 ================= */}

          <S.GraphTitle>추천 활동</S.GraphTitle>
          <S.RecommendSection>
            <S.RecommendRow>
              <S.RecommaendBox>토론 활동</S.RecommaendBox>
              <S.RecommaendBox>문제 해결 프로젝트</S.RecommaendBox>
              <S.RecommaendBox>집중력 강화 훈련</S.RecommaendBox>
            </S.RecommendRow>
            {/* ================= 이동 ================= */}
            <S.ArrowImage onClick={() => navigate("/")}>
              <img src={Arrow} alt="메인으로 이동" />
            </S.ArrowImage>
          </S.RecommendSection>
        </S.MainColumn>
      </S.Wrrapper>
    </S.Background>
  );
}
