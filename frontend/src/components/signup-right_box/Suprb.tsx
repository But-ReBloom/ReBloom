import * as S from "./style.ts";
import Google from "../../assets/images/Google.svg";
import Dodum from "../../assets/images/dodamdodam.svg";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

export default function Suprb() {
  const navigate = useNavigate();

  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");
  const [userID, setUserID] = useState("");
  const [userName, setUserName] = useState("");

   const handleSubmit = () => {
    toast.success(`환영합니다! ${userID}님!`);

    setTimeout(() => {
        navigate("/login", { replace: true });
    }, 2000);
  };
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
