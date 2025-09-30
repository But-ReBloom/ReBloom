import * as S from "./style.ts";
import Left_box from "../../components/loginpage-left_box/Lplb.tsx";
import Right_box from "../../components/findpassword-right_box/Fpwrb.tsx";

export default function FindEmail(){
    
    //비밀번호찾기 페이지
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