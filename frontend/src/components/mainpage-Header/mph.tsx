import logo from "../../assets/images/Rebloom-logo.svg";
import { Link, useNavigate } from "react-router-dom";
import * as S from "./style.ts";
import Menu_Bar from "../menu-bar/mb.tsx";

function Header({ props }) {
  const navigate = useNavigate();
  const userId = props?.state?.id; // ← 로그인 이메일/아이디

  const handleLogout = () => {
    // 필요 시 토큰 삭제
    // localStorage.removeItem("token");

    navigate("/", { state: null }); // 상태 초기화
  };

  return (
    <S.HeaderContainer>
      <Link to="/">
        <S.Logo_svg src={logo} />
      </Link>

      <S.HeaderRight>
        <Menu_Bar />

        {userId ? (
          <div className="user-info-box">
            <S.LogoutContainer>
              <span className="user-name">{userId} 님</span>
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
          <div className="go-login-button">
            <Link to="/login">
              <button className="login-button-go-lp" id="login-box_button">
                로그인
              </button>
            </Link>
          </div>
        )}
      </S.HeaderRight>
    </S.HeaderContainer>
  );
}

export default Header;
