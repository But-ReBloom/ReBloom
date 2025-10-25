import * as S from "./style.ts";
import Hello from "../../assets/images/HelloHand.svg";
import Arrow from "../../assets/images/whitearrow.svg";
import { useNavigate } from "react-router-dom";

export default function ExpreviewContentBox() {
  const navigate = useNavigate();

  return (
    <S.Content>
      <S.Hihand src={Hello} alt="hi" />
      <S.Thankyou>활동 리뷰에 참여해 주셔서 감사합니다.</S.Thankyou>
      <S.Announce>
        솔직한 답변이 더 좋은 결과를 만듭니다, 지금 바로 리뷰를 시작해 보세요!
      </S.Announce>
      <S.StartBtn
        onClick={() => {
          navigate("/myexps");
        }}
      >
        활동리뷰 하기<S.Arrow src={Arrow} alt="arrow"></S.Arrow>
      </S.StartBtn>
    </S.Content>
  );
}
