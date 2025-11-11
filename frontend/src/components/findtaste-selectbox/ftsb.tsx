import { useState, useEffect } from "react";
import * as S from "./style.ts";

interface SelectBoxProps {
  pro: string; // 질문 내용
  selectedIndex: number | null; // 현재 선택된 버튼 인덱스
  onSelect: (value: number | null) => void; // 선택 시 부모로 전달 (값은 -2 ~ 2)
}

function Select_Box({ pro, selectedIndex, onSelect }: SelectBoxProps) {
  const [activeIndex, setActiveIndex] = useState<number | null>(selectedIndex);

  // 인덱스를 값으로 변환 (0~4 → 2~(-2))
  const indexToValue = (index: number | null): number | null => {
    if (index === null) return null;
    const valueMap = [2, 1, 0, -1, -2];
    return valueMap[index];
  };

  // 부모에서 넘어온 selectedIndex가 바뀌면 내부 상태 동기화
  useEffect(() => {
    setActiveIndex(selectedIndex);
  }, [selectedIndex]);

  // 클릭 시 처리
  const handleClick = (index: number) => {
    const newIndex = activeIndex === index ? null : index;
    setActiveIndex(newIndex);

    // 변환된 값 (-2~2) 부모에게 전달
    onSelect(indexToValue(newIndex));
  };

  return (
    <S.DesBox>
      <S.Text>{pro}</S.Text>
      <form>
        <S.Select_Box>
          <S.L_Text>동의함</S.L_Text>

          <S.Select_Button_Box>
            {[0, 1, 2, 3, 4].map((i) => {
              const CheckBoxComp =
                i === 0
                  ? S.LL_CheckBox
                  : i === 1
                  ? S.L_CheckBox
                  : i === 2
                  ? S.M_CheckBox
                  : i === 3
                  ? S.R_CheckBox
                  : S.RR_CheckBox;

              return (
                <CheckBoxComp
                  key={i}
                  className={activeIndex === i ? "action" : ""}
                  onClick={() => handleClick(i)}
                  type="radio"
                  name={`box-${pro}`} // 같은 질문 내 그룹 유지
                />
              );
            })}
          </S.Select_Button_Box>

          <S.R_Text>동의하지 않음</S.R_Text>
        </S.Select_Box>
      </form>
    </S.DesBox>
  );
}

export default Select_Box;
