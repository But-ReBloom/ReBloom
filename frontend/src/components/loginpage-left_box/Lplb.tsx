import logo from "../../assets/images/Rebloom-logo.svg";
import * as S from "./style.ts";
import { Link } from "react-router-dom";

function Left_box() {
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
