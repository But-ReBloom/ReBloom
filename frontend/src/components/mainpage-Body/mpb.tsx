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
          <S.IntroBig>Taste Management Platform</S.IntroBig>
          <S.IntroSmall>
            Find your taste, and do related activities
          </S.IntroSmall>
        </S.IntroTextBox>
        <S.BodyButtons>
          <Link to="/explore/taste">
            <S.GoFindTasteButton>Taste Activity</S.GoFindTasteButton>
          </Link>
          <S.IntroButtons
            onClick={() => {
              navigate("/taste/hobby");
            }}
            id="go-findTaste_Hobbytest"
          >
            <S.DYT>
              <b>Discover your taste</b>
            </S.DYT>
            <img src={arrow} alt="" />
          </S.IntroButtons>
        </S.BodyButtons>
      </S.BodyContainer>
    </>
  );
};
