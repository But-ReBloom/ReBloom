import { Link } from "react-router-dom";
import * as S from "./style.ts";

export default function Body() {
  return (
    <>
      <S.BodyContainer>
        <S.IntroTextBox>
          <S.IntroBig>404 NOT FOUND</S.IntroBig>
          <S.IntroSmall>Oh no! You didn't find the wrong page</S.IntroSmall>
        </S.IntroTextBox>
        <S.BodyButtons>
          <Link to="/">
            <S.GoFindTasteButton>Home</S.GoFindTasteButton>
          </Link>
        </S.BodyButtons>
      </S.BodyContainer>
    </>
  );
}
