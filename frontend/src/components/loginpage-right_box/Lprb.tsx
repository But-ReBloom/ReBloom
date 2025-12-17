import { useEffect, useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import * as S from "./style.ts";
import Google from "../../assets/images/Google.svg";
import { authApi } from "../../api/auth";


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
      const response = await authApi.login({
        userEmail,
        userPassword: password,
        userProvider: "SELF",
      });

      if (response.success) {
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("userEmail", response.data.userEmail);
        navigate("/", { state: { id: response.data.userEmail } });
      } else {
        toast.error(
          response.message || "서버와의 통신 중 오류가 발생했습니다."
        );
      }
    } catch (error) {
      toast.error("이메일 또는 비밀번호가 일치하지 않습니다.");
      console.error(error);
    }
  };

  const handleGoogleLogin = () => {
    const clientId = import.meta.env.VITE_GOOGLE_CLIENT_ID;
    const redirectUri = `${window.location.origin}/auth/google/callback`;
    const scope = "email profile";
    const responseType = "code";

    if (!clientId || clientId === "YOUR_GOOGLE_CLIENT_ID_HERE") {
      toast.error("Google Client ID가 설정되지 않았습니다. .env 파일을 확인해주세요.");
      return;
    }

    const googleAuthUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=${responseType}&scope=${scope}`;
    
    window.location.href = googleAuthUrl;
  };

  return (
    <>
      <S.LoginContainer>
        <S.LoginTextBox>
          <S.Title>ReBloom에 오신것을 환영합니다!</S.Title>
          <S.Subtitle>로그인을 진행해주십시오.</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
          <div>
            <S.InputLabel>이메일</S.InputLabel>
            <S.Input
              type="text"
              value={userEmail}
              onChange={(e) => setUserEmail(e.target.value)}
              placeholder="이메일을 입력해주세요."
            />
          </div>

          <div>
            <S.InputLabel>비밀번호</S.InputLabel>
            <S.Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="비밀번호를 입력해주세요."
            />
          </div>

          <S.ButtonBox>
            <S.Forgots>
              <S.Forgot_a to="/forgot/email">이메일을 잊으셨나요?</S.Forgot_a>
              <S.Forgot_a to="/forgot/password">아이디를 잊으셨나요?</S.Forgot_a>
            </S.Forgots>
            <S.LoginButton onClick={handleSubmit} type="button">
              로그인
            </S.LoginButton>
          </S.ButtonBox>
        </S.InputBox>

        <S.OAuthFamily>
          <S.OAuthButton onClick={handleGoogleLogin}>
            <img src={Google} alt="Google" />
          </S.OAuthButton>
        </S.OAuthFamily>

        <S.SignUpTag onClick={() => navigate("/signup")}>
          아직 회원가입을 진행하지 않았나요?
        </S.SignUpTag>
      </S.LoginContainer>

      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
