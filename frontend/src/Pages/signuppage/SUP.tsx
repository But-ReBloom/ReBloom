import Left_box from "../../components/loginpage-left_box/Lplb.tsx";
import Right_box from "../../components/signup-right_box/Suprb.tsx";
import * as S from "./style.ts"

export default function SUP() {
    return(
        <>
        <S.SignupContainer>
            <Left_box/>
            <Right_box/>
        </S.SignupContainer>
        </>
    );
}