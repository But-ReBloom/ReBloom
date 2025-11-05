import { useState } from "react";
import * as S from "./style.ts";

export default function QuestionBox() {
  const [activeIndex, setActiveIndex] = useState<number | null>(null);

  const handleClick = (index: number, value: number) => {
    console.log(value)
    setActiveIndex((prevIndex) => (prevIndex === index ? null : index));
  };
  return (
    <>
      <form>
        <S.Select_Box>
          <S.L_Text>동의함</S.L_Text>
          <S.Select_Button_Box>
            <S.LL_CheckBox
              className={activeIndex === 0 ? "action" : ""}
              onClick={() => handleClick(0, 2)}
              type="radio"
              name="box"
            />
            <S.L_CheckBox
              className={activeIndex === 1 ? "action" : ""}
              onClick={() => handleClick(1, 1)}
              type="radio"
              name="box"
            />
            <S.M_CheckBox
              className={activeIndex === 2 ? "action" : ""}
              onClick={() => handleClick(2, 0)}
              type="radio"
              name="box"
            />
            <S.R_CheckBox
              className={activeIndex === 3 ? "action" : ""}
              onClick={() => handleClick(3, -1)}
              type="radio"
              name="box"
            />
            <S.RR_CheckBox
              className={activeIndex === 4 ? "action" : ""}
              onClick={() => handleClick(4, -2)}
              type="radio"
              name="box"
            />
          </S.Select_Button_Box>
          <S.R_Text>동의하지 않음</S.R_Text>
        </S.Select_Box>
      </form>
    </>
  );
}
