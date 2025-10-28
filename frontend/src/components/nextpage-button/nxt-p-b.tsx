import * as S from "./style.ts";
import BlackArrow from "../../assets/images/blackarrow.svg";

const NextPButton = () => {
  return (
    <>
      <S.BlackArrowImg
        src={BlackArrow}
        alt="검은색 화살표"
      ></S.BlackArrowImg>
    </>
  );
};

export default NextPButton;
