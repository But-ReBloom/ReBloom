import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";
import { useNavigate } from "react-router-dom";

function Right_box() {
  const navigate = useNavigate();

  return (
    <S.LoginContainer>
      <S.LoginTextBox>
        <S.Title>Welcome to Rebloom</S.Title>
        <S.Subtitle>Please take a moment to login</S.Subtitle>
      </S.LoginTextBox>

      <S.InputBox>
        <div>
          <S.InputLabel>Email</S.InputLabel>
          <S.Input type="text" placeholder="Enter your Email" autoFocus />
        </div>
        <div>
          <S.InputLabel>Password</S.InputLabel>
          <S.Input type="password" placeholder="Enter your password" />
        </div>
      </S.InputBox>

      <S.ButtonBox>
        <S.ForgotPassword to="/">Forgot password?</S.ForgotPassword>
        <S.LoginButton onClick={() => { navigate('/'); }}>Log In</S.LoginButton>
      </S.ButtonBox>

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
  );
}

export default Right_box;
