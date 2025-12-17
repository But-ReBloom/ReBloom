import arrow from "../../assets/images/arrow.svg";
import { Link, useNavigate } from "react-router-dom";
import * as S from "./style.ts";

export const Body = () => {
  const navigate = useNavigate();

  //메인페이지_바디
  return (
    <>
      <S.BodyContainer>
        <S.IntroTextBox>
          <S.IntroBig>
            취미를 다시 발견하고 함께 공유하고 성장을 돕는 플랫폼
          </S.IntroBig>
          <S.IntroSmall>
            잊어버린 취향을 다시 발견하고, 이를 힘든 일상 속 쉼터로 발전시키는
            ReBloom
          </S.IntroSmall>
        </S.IntroTextBox>
        <S.BodyButtons>
          <Link to="/explore/taste">
            <S.GoFindTasteButton>활동하러가기</S.GoFindTasteButton>
          </Link>
          <S.IntroButtons
            onClick={() => {
              navigate("/taste/hobby");
            }}
            id="go-findTaste_Hobbytest"
          >
            <S.DYT>
              <b>취향테스트 바로가기</b>
            </S.DYT>
            <img src={arrow} alt="" />
          </S.IntroButtons>
        </S.BodyButtons>
      </S.BodyContainer>
    </>
  );
};
