import * as S from './style.ts';
import Google from '../../assets/images/Google.svg';
import Dodum from '../../assets/images/dodamdodam.svg';
import { useNavigate } from 'react-router-dom';

export default function Suprb() {
    const navigate = useNavigate();
    return(
    <>
    <S.LoginContainer>
        <S.LoginTextBox>
            <S.Title>Let's Sign Up</S.Title>
            <S.Subtitle>Please take a moment to sign in</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
            <S.Input_title>
                <S.InputLabel htmlFor='email-input'>Email</S.InputLabel>
                <S.Input type="email-input" placeholder="Enter your Email" autoFocus/>
            </S.Input_title>
            <S.Input_title>
                <S.InputLabel htmlFor='ID-input'>ID</S.InputLabel>
                <S.Input type="ID-input" placeholder="Enter your ID" />
            </S.Input_title>
            <S.Input_title>
                <S.InputLabel htmlFor='paasword-input'>Confirm Password</S.InputLabel>
                <S.Input type="password-input" placeholder="Enter your password" />
            </S.Input_title>
            <S.Input_title>
                <S.InputLabel htmlFor='name-input'>Your Name</S.InputLabel>
                <S.Input type="name" placeholder="Enter your name" />
            </S.Input_title>
        </S.InputBox>

        <S.ButtonBox>
            <S.ForgotPassword to="/"></S.ForgotPassword>
            <S.LoginButton onClick={() => { navigate('/login'); }}>Sign Up</S.LoginButton>
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
    </>
    );
}