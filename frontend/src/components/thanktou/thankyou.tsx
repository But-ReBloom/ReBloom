import * as S from "./style.ts";
import Header from "../normal_header/nh.tsx";
import { useNavigate } from "react-router-dom";

export default function Thankyou() {
    const navigate = useNavigate();
  return (
    <S.Wrapper>
      <Header />
      <S.Container>
        <S.Title>
            수고하셨습니다.
        </S.Title>
        <S.SubTitle>
            소중한 리뷰 감사합니다. 다음에 또 이용해주세요.
        </S.SubTitle>
        <S.HomeButton onClick={()=>{navigate("/")}}>
            다음
        </S.HomeButton>
      </S.Container>
    </S.Wrapper>
  );
}
