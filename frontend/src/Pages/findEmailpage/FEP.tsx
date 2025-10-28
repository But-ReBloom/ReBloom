import * as S from "./style.ts";
import Left_box from "../../components/loginpage-left_box/Lplb.tsx";
import Right_box from "../../components/findemail-right_box/Ferb.tsx";

export default function FindEmail(){
    //이메일 찾기 페이지
    return(
    <>
    <S.FindContainer>
        <S.Box>
            <Left_box/>
            <Right_box/>
        </S.Box>
    </S.FindContainer>
    </>
    );
}