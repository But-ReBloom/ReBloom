import * as S from "./style.ts";
import Dodum from "../../assets/images/dodamdodam.svg";
import Google from "../../assets/images/Google.svg";

function Right_box() {
  return (
    <S.LoginContainer>
      <S.LoginTextBox>
        <S.Title>Welcome to Rebloom</S.Title>
        <S.Subtitle>Please take a moment to login</S.Subtitle>
      </S.LoginTextBox>

      <S.InputBox>
        <div>
          <S.InputLabel>Email</S.InputLabel>
          <S.Input type="text" placeholder="Enter your Email" />
        </div>
        <div>
          <S.InputLabel>Password</S.InputLabel>
          <S.Input type="password" placeholder="Enter your password" />
        </div>
      </S.InputBox>

      <S.ButtonBox>
        <S.ForgotPassword to="/">Forgot password?</S.ForgotPassword>
        <S.LoginButton>Log In</S.LoginButton>
      </S.ButtonBox>

      <S.OAuthFamily>
        <S.OAuthButton>
          <img src={Dodum} alt="Dodum" />
        </S.OAuthButton>
        <S.OAuthButton>
          <img src={Google} alt="Google" />
        </S.OAuthButton>
      </S.OAuthFamily>
    </S.LoginContainer>
  );
}

export default Right_box;
