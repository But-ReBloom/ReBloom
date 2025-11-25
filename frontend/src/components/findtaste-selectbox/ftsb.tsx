<<<<<<< HEAD
import { useState } from "react";
import * as S from "./style.ts";

function Select_Box({ pro }: Props) {
  // 5개의 버튼 중 하나만 true일 수 있도록 관리
const [activeIndex, setActiveIndex] = useState<number | null>(null);

  // 클릭한 버튼 인덱스만 true로 설정
const handleClick = (index: number) => {
    setActiveIndex((prevIndex) => (prevIndex === index ? null : index));
    };
    return (
    <S.DesBox>
        <S.Text>{ pro }</S.Text>
        <form>
        <S.Select_Box>
            <S.L_Text>동의함</S.L_Text>
            <S.Select_Button_Box>
            <S.LL_CheckBox
                className={activeIndex === 0 ? "action" : ""}
                onClick={() => handleClick(0)}
                type="radio"
                name="box"
            />
            <S.L_CheckBox
                className={activeIndex === 1 ? "action" : ""}
                onClick={() => handleClick(1)}
                type="radio"
                name="box"
            />
            <S.M_CheckBox
                className={activeIndex === 2 ? "action" : ""}
                onClick={() => handleClick(2)}
                type="radio"
                name="box"
            />
            <S.R_CheckBox
                className={activeIndex === 3 ? "action" : ""}
                onClick={() => handleClick(3)}
                type="radio"
                name="box"
            />
            <S.RR_CheckBox
                className={activeIndex === 4 ? "action" : ""}
                onClick={() => handleClick(4)}
                type="radio"
                name="box"
            />
            </S.Select_Button_Box>
            <S.R_Text>동의하지 않음</S.R_Text>
=======
import { useState, useEffect } from "react";
import * as S from "./style.ts";

interface SelectBoxProps {
  pro: string;
  weights: number[]; // 각 질문마다 다른 가중치 배열
  selectedValue: number | null;
  onSelect: (value: number | null) => void;
}

function Select_Box({ pro, weights, selectedValue, onSelect }: SelectBoxProps) {
  const [activeIndex, setActiveIndex] = useState<number | null>(null);

  // ex) [-2, -1, 0, 1, 2]
  const indexToValue = weights;

  const valueToIndex = (value: number | null): number | null => {
    if (value === null) return null;
    return indexToValue.indexOf(value);
  };

  // 부모에게 받은 selectedValue → UI에 반영
  useEffect(() => {
    setActiveIndex(valueToIndex(selectedValue));
  }, [selectedValue]);

  const handleClick = (index: number) => {
    const newIndex = activeIndex === index ? null : index;
    setActiveIndex(newIndex);

    // 선택값(가중치)을 부모로 콜백
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
                  name={`select-${pro}`}
                />
              );
            })}
          </S.Select_Button_Box>

          <S.R_Text>동의하지 않음</S.R_Text>
>>>>>>> 240c33b22f560ae63c68a42ea4015ae853a1b962
        </S.Select_Box>
      </form>
    </S.DesBox>
  );
}

<<<<<<< HEAD
export default Select_Box;
    
=======
export default Select_Box;
>>>>>>> 240c33b22f560ae63c68a42ea4015ae853a1b962
