<<<<<<< HEAD
import { useState } from "react";
import * as S from "./style.ts";

function FT_HobbyTest_Description_Box() {
  // 5개의 버튼 중 하나만 true일 수 있도록 관리
const [activeIndex, setActiveIndex] = useState<number | null>(null);

  // 클릭한 버튼 인덱스만 true로 설정
const handleClick = (index: number) => {
    setActiveIndex((prevIndex) => (prevIndex === index ? null : index));
    };
    return (
    <S.DesBox>
        <S.Text>저는 취향테스트를 거짓된 부분 없이 응하겠습니다.</S.Text>
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
=======
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
>>>>>>> 240c33b22f560ae63c68a42ea4015ae853a1b962
    </S.DesBox>
  );
}

export default FT_HobbyTest_Description_Box;
