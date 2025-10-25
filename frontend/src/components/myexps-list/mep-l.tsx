import * as S from "./style.ts";
import OneExpInfo from "../myexp-box/meb.tsx";

export default function MepL() {
  return (
    <>
      <S.Head>
        <S.Tags>활동명</S.Tags>
        <S.Tags>활동 날짜</S.Tags>
        <S.Tags>관련 태그</S.Tags>
        <S.Tags>리뷰 여부</S.Tags>
      </S.Head>
      <OneExpInfo />
      <OneExpInfo />
      <OneExpInfo />
      <OneExpInfo />
    </>
  );
}
