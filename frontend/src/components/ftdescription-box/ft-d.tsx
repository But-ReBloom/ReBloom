import * as S from "./style.ts";
import QuestionBox from "../questionbox/qb.tsx";

function FT_HobbyTest_Description_Box() {
  return (
    <S.DesBox>
      <S.Text>저는 취향테스트를 거짓된 부분 없이 응하겠습니다.</S.Text>
      <QuestionBox/>
    </S.DesBox>
  );
}

export default FT_HobbyTest_Description_Box;
