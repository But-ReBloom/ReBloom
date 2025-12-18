import * as S from "./style.ts";
import Google from "../../assets/images/Google.svg";
import { useState } from "react";

import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import { authApi } from "../../api/auth";

export default function Suprb() {
  const navigate = useNavigate();

  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userID, setUserID] = useState("");
  const [userName, setUserName] = useState("");

  const handleSubmit = async () => {
    if (
      userEmail.trim() &&
      password.trim() &&
      userID.trim() &&
      userName.trim()
    ) {
      try {
        const response = await authApi.signup({
          userEmail,
          userId: userID,
          userPassword: password,
          userName,
          userProvider: "SELF",
        });

        if (response.success) {
          toast.success(
            <>
              환영합니다! {userID}님! <br /> 기다려주세요. 이동중 입니다.
            </>
          );
          navigate("/login", { state: { id: userID } });
        } else {
          toast.error(response.message || "회원가입 중 오류가 발생했습니다.");
        }
      } catch (error) {
        toast.error("회원가입 중 오류가 발생했습니다.");
        console.error(error);
      }
    } else {
      toast.error(<>빈 칸이 있어선 안됩니다.</>);
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

    const googleAuthUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}&response_type=${responseType}&scope=${encodeURIComponent(scope)}`;
    
    window.location.href = googleAuthUrl;
  };

  //회원가입_오른쪽박스
  return (

    <>
      <S.LoginContainer>
        <S.LoginTextBox>
          <S.Title>회원가입을 진행해주세요!</S.Title>
          <S.Subtitle>빈 칸을 입력해주십시오.</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
          <S.Input_title>
            <S.InputLabel htmlFor="email-input">이메일</S.InputLabel>
            <S.Input
              type="text"
              value={userEmail}
              onChange={(e) => setUserEmail(e.target.value)}
              placeholder="이메일을 입력해주십시오."
              autoFocus
            />
          </S.Input_title>
          <S.Input_title>
            <S.InputLabel htmlFor="ID-input">아이디</S.InputLabel>
            <S.Input
              type="text"
              value={userID}
              onChange={(e) => setUserID(e.target.value)}
              placeholder="아이디에는 숫자를 포함하여 입력해주십시오."
            />
          </S.Input_title>
          <S.Input_title>
            <S.InputLabel htmlFor="paasword-input">비밀번호</S.InputLabel>
            <S.Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="대문자, 숫자, 특수문자 포함 8자 이상 입력해주십시오."
            />
          </S.Input_title>
          <S.Input_title>
            <S.InputLabel htmlFor="name-input">이름</S.InputLabel>
            <S.Input
              type="name"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              placeholder="이름을 입력해주십시오."
            />
          </S.Input_title>
        </S.InputBox>

        <S.ButtonBox>
          <S.ForgotPassword to="/"></S.ForgotPassword>
          <S.LoginButton onClick={handleSubmit}>회원가입</S.LoginButton>
        </S.ButtonBox>

        <S.OAuthFamily>
          <S.OAuthButton onClick={handleGoogleLogin}>
            <img src={Google} alt="Google" />
          </S.OAuthButton>
        </S.OAuthFamily>

      </S.LoginContainer>
      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
