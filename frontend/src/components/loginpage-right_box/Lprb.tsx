import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { toast } from 'react-toastify';


export default function Right_box() {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    if (location.state && location.state.toastMessage) {
      toast.success(location.state.toastMessage);
    }
  }, [location.state]);

  const users = [
        { id: "testuser", password: "1234", email: "testuser@example.com" },
        { id: "yongjun", password: "abcd", email: "yongjun@example.com" },
        { id: "guest", password: "guest", email: "guest@sample.com" },
    ];

    const [userid, setUserid] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (event?: React.FormEvent) => {
        if (event) event.preventDefault();

        const user = users.find(
            (u) => u.email === userid && u.password === password
        );

        if (user) {
          navigate("/", { 
              replace: true,
              state: { toastMessage: `환영합니다! ${user.id}님` }
          });
        } else {
            toast.error("이메일 또는 비밀번호가 올바르지 않습니다.");
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
          <S.Input type="text"
                value={userid}
                onChange={(e) => setUserid(e.target.value)}
                placeholder="Enter your ID"
                autoFocus />
        </div>
        <div>
          <S.InputLabel>Password</S.InputLabel>
          <S.Input 
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Enter your password"/>
        </div>

        <S.ButtonBox>
          <S.Forgots>
            <S.Forgot_a to="/forgot/email" >Forgot ID?</S.Forgot_a>
            <S.Forgot_a to="/forgot/password">Forgot password?</S.Forgot_a>
          </S.Forgots>
          <S.LoginButton onClick={handleSubmit} type="button" >Log In</S.LoginButton>
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
      <S.SignUpTag onClick={() => { navigate('/signup'); }}>Haven't you signed up yet?</S.SignUpTag>
    </S.LoginContainer>
    </>
  );
}
