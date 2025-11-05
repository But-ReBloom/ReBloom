import * as S from "./style.ts";
import BlackArrowImg from "../../assets/images/blackarrow.svg";

export default function MyexpsDetail() {
  return (
    <>
      <S.Wrapper>
        <S.Container>
          <S.Arrow>
            <img src={BlackArrowImg} alt="이전으로 가는 화살표" />
          </S.Arrow>
        </S.Container>
      </S.Wrapper>
    </>
  );
}
