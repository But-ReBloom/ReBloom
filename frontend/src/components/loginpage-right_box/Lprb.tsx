import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";

export default function Right_box() {
  const navigate = useNavigate();

  const users = [
    { id: "testuser", password: "1234", email: "testuser@example.com" },
    { id: "yongjun", password: "abcd", email: "yongjun@example.com" },
    { id: "guest", password: "guest", email: "guest@sample.com" },
  ];

  const [userEmail, setUserEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = () => {
    if (userEmail.trim() && password.trim()) {
      const user = users.find(
        (u) => u.password === password && u.email === userEmail
      );

      if (user) {
        toast.success(
          <>
            환영합니다! {user.id}님 <br /> 기다려 주세요. 이동중 입니다.
          </>
        );

        setTimeout(() => {
          navigate("/", { replace: true });
        }, 2500);
      } else {
        toast.error(<>알맞은 정보가 없습니다.</>);
      }
    } else {
      toast.error(<>빈 칸이 있어선 안됩니다.</>);
    }
  };
  // 로그인_오른쪽박스
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
        <S.SignUpTag
          onClick={() => {
            navigate("/signup");
          }}
        >
          Haven't you signed up yet?
        </S.SignUpTag>
      </S.LoginContainer>
      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
