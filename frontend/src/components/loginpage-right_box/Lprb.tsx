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
  const handleSubmit = () => {
    // 로그인 영역 , 비밀번호 영역 중 하나라도 공백이면 에러
    if (!userEmail.trim() || !password.trim()) {
      toast.error("빈 칸이 있어선 안됩니다.");
      return;
    }

    // POST 과정 (이메일과 패스워드 정보를 보내어 사용자 정보 확인)
    fetch("", {
      method: "POST",
      headers: {
        "Content-Type": "application/json", // json 형식으로 데이터 전송
      },
      body: JSON.stringify({
        userEmail: userEmail,
        userPassword: password,
        provider: "SELF",
      }),
    })
      .then((response) => {
        if (!response.ok) {
          // response가 정상적이지 않으면 에러 처리
          throw new Error(
            `서버 응답이 올바르지 않습니다. | 현 상태: ${response.status}`
          );
        } else {
          return response.json(); // 아니라면 json으로 데이터 변환 후 다음 then으로 전달
        }
      })

      .then((data) => {
        if (data.success) {
          // POST한 데이터가 일치하면 환영 메시지와 함께 메인 페이지로 이동
          toast.success(
            <>
              환영합니다! {data.userName}님 <br /> 이동 중입니다...
            </>
          );
          setTimeout(() => {
            navigate("/", { replace: true });
          }, 2000);
        } else {
          // 일치하지 않다면 오류 출력
          console.error("통신 오류: ", data.error);
          toast.error("서버와의 통신 중 오류가 발생했습니다.");
        }
      })

      .catch((error) => {
        // 서버 통신 중 에러 메세지 출력
        console.error("데이터 오류:", error);
        toast.error("이메일 또는 비밀번호가 일치하지 않습니다.");
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
