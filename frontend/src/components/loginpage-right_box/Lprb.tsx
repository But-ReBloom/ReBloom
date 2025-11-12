import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";

export default function Right_box() {
  const navigate = useNavigate();
  const location = useLocation();

  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    const userId = location.state?.id;
    if (userId) {
      toast.success(`로그인을 진행해주세요! ${userId}님!`);
    }
  }, [location.state]); // state가 바뀔 때만 실행

  const handleSubmit = async () => {
    if (!userEmail.trim() || !password.trim()) {
      toast.error("빈 칸이 있어선 안됩니다.");
      return;
    }

    try {
      const response = await fetch("/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          userEmail,
          userPassword: password,
          provider: "SELF",
        }),
      });

      if (!response.ok) {
        throw new Error(`서버 응답이 올바르지 않습니다. (${response.status})`);
      }

      const data = await response.json();

      if (data.success) {
        navigate("/", { state: { id: data.userID } });
      } else {
        toast.error("서버와의 통신 중 오류가 발생했습니다.");
      }
    } catch (error) {
      toast.error("이메일 또는 비밀번호가 일치하지 않습니다.");
      console.error(error);
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
              value={userEmail}
              onChange={(e) => setUserEmail(e.target.value)}
              placeholder="Enter your e-mail"
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
