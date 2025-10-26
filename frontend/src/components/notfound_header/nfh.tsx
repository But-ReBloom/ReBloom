import Logo from "../../assets/images/Rebloom-logo.svg";
import { Link } from "react-router-dom";
import * as S from "./style.ts";
import Exit from "../../assets/images/exit.svg";

function Header() {
  //메인 페이지_헤더
  return (
    <>
      <S.HeaderContainer>
        <Link to="/">
          <S.Logo_svg src={Logo} />
        </Link>
        <Link to="/">
          <S.ExitBtn>
            <S.Exit_img src={Exit} alt="나가기 버튼" />
          </S.ExitBtn>
        </Link>
      </S.HeaderContainer>
    </>
  );
}

export default Header;
