import * as S from "./style.ts";
import QuestionBox from "../questionbox/qb.tsx";

interface FT_HobbyTest_Description_BoxProps {
  onSelect: (value: number | null) => void; // 부모로 선택값 전달
}

function FT_HobbyTest_Description_Box({ onSelect }: FT_HobbyTest_Description_BoxProps) {
  return (
    <S.DesBox>
      <S.Text>저는 취향테스트를 거짓된 부분 없이 응하겠습니다.</S.Text>
      <QuestionBox onSelect={onSelect} />
    </S.DesBox>
  );
}

export default FT_HobbyTest_Description_Box;
