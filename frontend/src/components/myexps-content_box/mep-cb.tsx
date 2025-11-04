import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";
import { useState } from "react";

export default function MepCb() {
  const exps = [0, 1, 2, 3, 4, 5, 6,7, 8, 9,10,11,12,13,14];
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 7;

  const startIdx = (currentPage - 1) * itemsPerPage;
  const endIdx = startIdx + itemsPerPage;
  const currentExps = exps.slice(startIdx, endIdx);

  return (
    <>
      <S.Wrapper>
        <S.Container>
          <S.TitleBox>
            {/*타이틀*/}
            <S.Question>어떤 활동에 대한 리뷰를 남기고 싶으신가요?</S.Question>
            <S.Line />
          </S.TitleBox>
          <List myexps={currentExps} /> {/*항목들*/}
          <PageNum
            myexps={exps}
            itemsPerPage={itemsPerPage}
            currentPage={currentPage}
            setCurrentPage={setCurrentPage}
          />
        </S.Container>
      </S.Wrapper>
    </>
  );
}

function PageNum(props) {
  const pageCount = Math.ceil(props.myexps.length / props.itemsPerPage);
  const pageArray = Array.from({ length: pageCount }, (_, i) => i + 1);

  return (
    <>
      <S.PageNumBox>
        {pageArray.map((num) => (
          <S.PageCountBox key={num} onClick={() => props.setCurrentPage(num)}>{num}</S.PageCountBox>
        ))}
      </S.PageNumBox>
    </>
  );
}