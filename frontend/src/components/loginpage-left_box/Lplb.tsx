import logo from "../../assets/images/Rebloom-logo.svg";
import * as S from "./style.ts";

function Left_box() {
  return (
    <S.LeftBox>
      <S.Logo src={logo} alt="Rebloom Logo" />
      <S.TextBox>
        <S.FindYourTaste>Find your taste</S.FindYourTaste>
        <S.UseReBloom>Use Re: Bloom to find your taste</S.UseReBloom>
        <S.BloomingAgain>and you will blooming again!</S.BloomingAgain>
      </S.TextBox>
    </S.LeftBox>
  );
}
export default Left_box;
