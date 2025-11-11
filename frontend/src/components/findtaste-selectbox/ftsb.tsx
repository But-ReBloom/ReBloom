import { useState, useEffect } from "react";
import * as S from "./style.ts";

interface SelectBoxProps {
  pro: string; // 질문 내용
  selectedValue: number | null; // 현재 선택된 값 (-2 ~ 2)
  onSelect: (value: number | null) => void; // 선택 시 부모로 전달
}

function Select_Box({ pro, selectedValue, onSelect }: SelectBoxProps) {
  const [activeIndex, setActiveIndex] = useState<number | null>(null);

  // 인덱스 ↔ 값 변환 테이블
  const indexToValue = [2, 1, 0, -1, -2];
  const valueToIndex = (value: number | null): number | null => {
    if (value === null) return null;
    return indexToValue.indexOf(value);
  };

  // 부모에서 받은 값(selectedValue)이 변경될 때 activeIndex 갱신
  useEffect(() => {
    setActiveIndex(valueToIndex(selectedValue));
  }, [selectedValue]);

  // 클릭 시 처리
  const handleClick = (index: number) => {
    const newIndex = activeIndex === index ? null : index;
    setActiveIndex(newIndex);

    // 변환된 값 (-2~2)을 부모로 전달
    onSelect(newIndex !== null ? indexToValue[newIndex] : null);
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
                  name={`box-${pro}`} // 같은 질문 그룹 유지
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
