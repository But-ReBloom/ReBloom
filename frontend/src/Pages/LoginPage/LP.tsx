import Left_box from "../../components/loginpage-left_box/Lplb";
import Right_box from "../../components/loginpage-right_box/Lprb";
import * as S from "./style.ts";

function LoginPage() {
    //로그인 페이지
    return (
        <>
        <S.LoginContainer>
            <S.Box>
                <Left_box />
                <Right_box />
            </S.Box>
        </S.LoginContainer>
        </>
    );
}

export default LoginPage;