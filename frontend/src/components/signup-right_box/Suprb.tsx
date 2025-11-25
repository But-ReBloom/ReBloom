import * as S from "./style.ts";
import Google from "../../assets/images/Google.svg";
import Dodum from "../../assets/images/dodamdodam.svg";
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

  //회원가입_오른쪽박스
  return (
    <>
      <S.LoginContainer>
        <S.LoginTextBox>
          <S.Title>Let's Sign Up</S.Title>
          <S.Subtitle>Please take a moment to sign in</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
          <S.Input_title>
            <S.InputLabel htmlFor="email-input">Email</S.InputLabel>
            <S.Input
              type="text"
              value={userEmail}
              onChange={(e) => setUserEmail(e.target.value)}
              placeholder="Enter your Email"
              autoFocus
            />
          </S.Input_title>
          <S.Input_title>
            <S.InputLabel htmlFor="ID-input">ID</S.InputLabel>
            <S.Input
              type="text"
              value={userID}
              onChange={(e) => setUserID(e.target.value)}
              placeholder="Enter your ID"
            />
          </S.Input_title>
          <S.Input_title>
            <S.InputLabel htmlFor="paasword-input">
              Confirm Password
            </S.InputLabel>
            <S.Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password"
            />
          </S.Input_title>
          <S.Input_title>
            <S.InputLabel htmlFor="name-input">Your Name</S.InputLabel>
            <S.Input
              type="name"
              value={userName}
              onChange={(e) => setUserName(e.target.value)}
              placeholder="Enter your name"
            />
          </S.Input_title>
        </S.InputBox>

        <S.ButtonBox>
          <S.ForgotPassword to="/"></S.ForgotPassword>
          <S.LoginButton onClick={handleSubmit}>Sign Up</S.LoginButton>
        </S.ButtonBox>

        <S.OAuthFamily>
          <S.OAuthButton>
            <img src={Google} alt="Google" />
          </S.OAuthButton>
          <S.OAuthButton>
            <img src={Dodum} alt="Dodum" />
          </S.OAuthButton>
        </S.OAuthFamily>
      </S.LoginContainer>
      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
