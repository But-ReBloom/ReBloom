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
        </S.Select_Box>
      </form>
    </S.DesBox>
  );
}

export default Select_Box;
    