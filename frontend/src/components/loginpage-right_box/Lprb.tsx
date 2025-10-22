import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

export default function Right_box() {
  const navigate = useNavigate();

  //로그인 데이터 가져오기
  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");

  // 로그인 함수
  const handleSubmit = () => {
    // 로그인 영역 , 비밀번호 영역 중 하나라도 공백이면 에러
    if (!userEmail.trim() || !password.trim()) {
      toast.error("빈 칸이 있어선 안됩니다.");
      return;
    }

    fetch("", {
      method: "POST",
      body: JSON.stringify({
        userEmail: userEmail,
        userPassword: password,
        provider: "SELF",
      }),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! 현 상태: ${response.status}`);
        }else{
          return response.json();
        }
      })
      .then((data) => {
        toast.success(<>환영합니다! {data.userName}님 <br /> 이동 중입니다...</>);
        setTimeout(() => {
          navigate("/", { replace: true });
        }, 2000);
      })
      .catch((error) => {
        console.error("Fetch error:", error);
        toast.error(<>로그인에 실패했습니다. 다시 시도해주세요.</>);
      });
  };

  return (
    <>
      <S.LoginContainer>
        <S.LoginTextBox>
          <S.Title>Welcome to Rebloom</S.Title>
          <S.Subtitle>Please take a moment to login</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
          <div>
            <S.InputLabel>Email</S.InputLabel>
            <S.Input
              type="text"
              value={userEmail}
              onChange={(e) => setUserEmail(e.target.value)}
              placeholder="Enter your e-mail"
              autoFocus
            />
          </div>
          <div>
            <S.InputLabel>Password</S.InputLabel>
            <S.Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password"
            />
          </div>

          <S.ButtonBox>
            <S.Forgots>
              <S.Forgot_a to="/forgot/email">Forgot email?</S.Forgot_a>
              <S.Forgot_a to="/forgot/password">Forgot password?</S.Forgot_a>
            </S.Forgots>
            <S.LoginButton onClick={handleSubmit} type="button">
              Log In
            </S.LoginButton>
          </S.ButtonBox>
        </S.InputBox>

        <S.OAuthFamily>
          <S.OAuthButton>
            <img src={Google} alt="Google" />
          </S.OAuthButton>
          <S.OAuthButton>
            <img src={Dodum} alt="Dodum" />
          </S.OAuthButton>
        </S.OAuthFamily>
        <S.SignUpTag onClick={() => navigate("/signup")}>
          Haven't you signed up yet?
        </S.SignUpTag>
      </S.LoginContainer>

      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
