import logo from "../../assets/images/Rebloom-logo.svg";
import { Link, useNavigate } from "react-router-dom";
import * as S from "./style.ts";
import Menu_Bar from "../menu-bar/mb.tsx";

function Header({ props }: { props?: any }) {
  const navigate = useNavigate();
  const userName = props?.state?.id || localStorage.getItem("userName"); // 로그인 사용자 이름

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userName");
    localStorage.removeItem("userId");
    navigate("/", { state: null }); // 상태 초기화
  };

  return (
    <S.HeaderContainer>
      <Link to="/">
        <S.Logo_svg src={logo} />
      </Link>

      <S.HeaderRight>
        <Menu_Bar />

        {userName ? (
          <div className="user-info-box">
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
          </div>
        ) : (
          <div onClick={() => navigate("/login")} className="go-login-button">
            로그인
          </div>
        )}

      </S.HeaderRight>
    </S.HeaderContainer>
  );
}

export default Header;
