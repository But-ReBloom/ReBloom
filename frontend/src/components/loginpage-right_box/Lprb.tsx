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
  const handleSubmit = async () => {
    // 로그인 영역 , 비밀번호 영역 중 하나라도 공백이면 에러
    if (!userEmail.trim() || !password.trim()) {
      toast.error("빈 칸이 있어선 안됩니다.");
      return;
    }

    // 그게 아니라면 바로 리스폰스 시도
    try {
      const response = await fetch("", {
        method: "POST",
        body: JSON.stringify({
          userEmail: userEmail,
          userPassword: password,
          provider: "SELF",
        }),
      });

      // 주소가 알맞지 않다면 에러 처리
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      // 데이터 가공
      const data = await response.json();
      // console.log("서버 응답:", data);

      // 로그인 성공 시
      toast.success(
        <>
          환영합니다! {data.userName}님 <br /> 이동 중입니다...
        </>
      );
      setTimeout(() => {
        navigate("/", { replace: true });
      }, 2000);
    } catch (error) {
      // 잘못된 정보인 데이터가 확인된다면  처리
      console.error("Fetch error:", error);
      toast.error(<>로그인에 실패했습니다. 다시 시도해주세요.</>);
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
