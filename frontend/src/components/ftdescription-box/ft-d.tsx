import { useState } from "react";
import * as S from "./style.ts";

function FT_HobbyTest_Description_Box() {
  // 5개의 버튼 중 하나만 선택
  const [activeIndex, setActiveIndex] = useState<number | null>(null);

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
            {/* 0번 */}
            <S.LL_CheckBox
              className={activeIndex === 0 ? "action" : ""}
              onClick={() => handleClick(0)}
              type="radio"
              name="box"
              checked={activeIndex === 0}
              readOnly
            />

            {/* 1번 */}
            <S.L_CheckBox
              className={activeIndex === 1 ? "action" : ""}
              onClick={() => handleClick(1)}
              type="radio"
              name="box"
              checked={activeIndex === 1}
              readOnly
            />

            {/* 2번 */}
            <S.M_CheckBox
              className={activeIndex === 2 ? "action" : ""}
              onClick={() => handleClick(2)}
              type="radio"
              name="box"
              checked={activeIndex === 2}
              readOnly
            />

            {/* 3번 */}
            <S.R_CheckBox
              className={activeIndex === 3 ? "action" : ""}
              onClick={() => handleClick(3)}
              type="radio"
              name="box"
              checked={activeIndex === 3}
              readOnly
            />

            {/* 4번 */}
            <S.RR_CheckBox
              className={activeIndex === 4 ? "action" : ""}
              onClick={() => handleClick(4)}
              type="radio"
              name="box"
              checked={activeIndex === 4}
              readOnly
            />
          </S.Select_Button_Box>

          <S.R_Text>동의하지 않음</S.R_Text>
        </S.Select_Box>
      </form>
    </S.DesBox>
  );
}

export default FT_HobbyTest_Description_Box;
