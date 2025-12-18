import logo from "../../assets/images/Rebloom-logo.svg";
import { Link, useNavigate } from "react-router-dom";
import * as S from "./style";
import Menu_Bar from "../menu-bar/mb";

function Header() {
  const navigate = useNavigate();

  // 로그인 사용자 이름
  const userName = localStorage.getItem("userName");

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userName");
    localStorage.removeItem("userId");
    navigate("/", { replace: true });
  };

  return (
    <S.HeaderContainer>
      <Link to="/">
        <S.Logo_svg src={logo} alt="ReBloom 로고" />
      </Link>

      <S.HeaderRight>
        <Menu_Bar />

        {userName ? (
          <S.LogoutContainer>
            <span className="user-name">{userName} 님</span>
            <button
              className="login-button-go-lp"
              id="login-box_button"
              onClick={handleLogout}
            >
              로그아웃
            </button>
          </S.LogoutContainer>
        ) : (
          <div className="go-login-button" onClick={() => navigate("/login")}>
            로그인
          </div>
        )}
      </S.HeaderRight>
    </S.HeaderContainer>
  );
}

export default Header;
