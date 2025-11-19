import * as S from "./style.ts";
import Header from "../../components/normal_header/nh.tsx";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";

export default function TestResult() {
  const navigate = useNavigate();
  const location = useLocation();
  const { finalAverage } = location.state || {};

  return (
    <S.Background>
      <Header />
      <S.Wrrapper>
        <S.SemiContainer1>
          <S.Title>알고리즘 테스트 결과</S.Title>
          <S.LeftContainer>
            <S.ResultLeft>
              <S.ResultBox>
                <S.Subtitle>사회성 : </S.Subtitle>
                {finalAverage.social}
              </S.ResultBox>
              <S.ResultBox>
                <S.Subtitle>학습력 : </S.Subtitle>
                {finalAverage.learning}
              </S.ResultBox>
              <S.ResultBox>
                <S.Subtitle>계획력 : </S.Subtitle>
                {finalAverage.planning}
              </S.ResultBox>
            </S.ResultLeft>

            <S.ResultRight>
              <S.ResultBox>
                <S.Subtitle>집중력 : </S.Subtitle>
                {finalAverage.focus}
              </S.ResultBox>
              <S.ResultBox>
                <S.Subtitle>창의성 : </S.Subtitle>
                {finalAverage.creativity}
              </S.ResultBox>
            </S.ResultRight>
          </S.LeftContainer>
        </S.SemiContainer1>

        <S.SemiContainer2>
          <S.RightContainer>
            <S.RecommaendBox>
              테스트 결과로 인한 추천 활동 위치 1
            </S.RecommaendBox>

            <S.RecommaendBox>
              테스트 결과로 인한 추천 활동 위치 2
            </S.RecommaendBox>

            <S.RecommaendBox>
              테스트 결과로 인한 추천 활동 위치 3
            </S.RecommaendBox>
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
