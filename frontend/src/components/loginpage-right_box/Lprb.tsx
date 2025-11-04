import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

export default function Right_box() {
  const navigate = useNavigate(); // 페이지 이동에 필요한 요소

  //로그인 데이터 동기화하여 가져와 사용. value 값을 들고오기에 필요한 요소
  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");

  // 로그인 함수
  // 로그인 함수
  const handleSubmit = async () => {
    if (!userEmail.trim() || !password.trim()) {
      toast.error("빈 칸이 있어선 안됩니다.");
      return;
    }

    try {
      const response = await fetch("", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userEmail: userEmail,
          userPassword: password,
          provider: "SELF",
        }),
      });

      if (!response.ok) {
        throw new Error(
          `서버 응답이 올바르지 않습니다. | 상태 코드: ${response.status}`
        );
      }

      const data = await response.json();

      if (data.success) {
        navigate("/", { state: { id: data.userID } });
      } else {
        console.error("통신 오류:", data.error);
        toast.error("서버와의 통신 중 오류가 발생했습니다.");
      }
    } catch (error) {
      console.error("데이터 오류:", error);
      toast.error("이메일 또는 비밀번호가 일치하지 않습니다.");
    }
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
              value={userEmail} // 동기화된 이메일 값
              onChange={(e) => setUserEmail(e.target.value)}
              placeholder="Enter your e-mail"
              autoFocus
            />
          </div>
          <div>
            <S.InputLabel>Password</S.InputLabel>
            <S.Input
              type="password"
              value={password} // 동기화된 비밀번호 값
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
