import * as S from "./style.ts";
import Header from "../../components/normal_header/nh.tsx";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";

export default function TestResult() {
  const navigate = useNavigate();
  const location = useLocation();
  const { finalAverage, recommendations } = location.state || {};

  return (
    <S.Background>
      <Header />
      <S.Wrrapper>
        <S.SemiContainer1>
          <S.Title>알고리즘 테스트 결과</S.Title>
          <S.LeftContainer>
            <S.ResultLeft>
              <S.ResultBox>
                <S.Subtitle>회원님의 사회성 점수</S.Subtitle>
                <S.Scoretitle>{finalAverage?.social?.toFixed(1)}</S.Scoretitle>
              </S.ResultBox>
              <S.ResultBox>
                <S.Subtitle>회원님의 학습력 점수</S.Subtitle>
                <S.Scoretitle>{finalAverage?.learning?.toFixed(1)}</S.Scoretitle>
              </S.ResultBox>
              <S.ResultBox>
                <S.Subtitle>회원님의 계획력 점수</S.Subtitle>
                <S.Scoretitle>{finalAverage?.planning?.toFixed(1)}</S.Scoretitle>
              </S.ResultBox>
            </S.ResultLeft>

            <S.ResultRight>
              <S.ResultBox>
                <S.Subtitle>회원님의 집중력 점수</S.Subtitle>
                <S.Scoretitle>{finalAverage?.focus?.toFixed(1)}</S.Scoretitle>
              </S.ResultBox>
              <S.ResultBox>
                <S.Subtitle>회원님의 창의성 점수</S.Subtitle>
                <S.Scoretitle>{finalAverage?.creativity?.toFixed(1)}</S.Scoretitle>
              </S.ResultBox>
            </S.ResultRight>
          </S.LeftContainer>
        </S.SemiContainer1>

        <S.SemiContainer2>
          <S.RightContainer>
            {recommendations && recommendations.length > 0 ? (
              recommendations.map((rec: any, index: number) => (
                <S.RecommaendBox key={index}>
                  {index + 1}. {rec.hobbyName}
                </S.RecommaendBox>
              ))
            ) : (
              <S.RecommaendBox>추천 활동이 없습니다.</S.RecommaendBox>
            )}
          </S.RightContainer>
          <S.ArrowImage
            onClick={() =>
              navigate("/thankyou", {
                state: {
                  type: "HobbyTest",
                  message: "테스트에 응해주셔서 감사합니다.",
                },
              })
            }
          >
            <img src={Arrow} alt="" />
          </S.ArrowImage>
        </S.SemiContainer2>
      </S.Wrrapper>
    </S.Background>
  );
}
