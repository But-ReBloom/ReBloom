import logo from "../../assets/images/Rebloom-logo.svg";
import * as S from "./style.ts";
import { Link } from "react-router-dom";

function Left_box() {
  //로그인_왼쪽박스_Auth와 관련한 모든 박스의 왼쪽에 사용되는 박스
  return (
    <S.LeftBox>
      <Link to="/"><S.Logo src={logo} alt="Rebloom Logo" /></Link>
      <S.TextBox>
        <S.FindYourTaste>Find your taste</S.FindYourTaste>
        <S.UseReBloom>Use Re: Bloom to find your taste</S.UseReBloom>
        <S.BloomingAgain>and you will blooming again!</S.BloomingAgain>
      </S.TextBox>
    </S.LeftBox>
  );
}
export default Left_box;
