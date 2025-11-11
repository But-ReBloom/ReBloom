import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";
import MepDetail from "../myexps_detail/mep-detail.tsx";
import { useState } from "react";

export default function MepCb() {
  const [step, setStep] = useState("index"); // 'index' | 'detail'

  return (
    <>
      {step === "index" && <Index setStep={setStep} />}
      {step === "detail" && <MepDetail />}
    </>
  );
}

function Index({ setStep }) {
  const exps = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14];
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 7;

  const startIdx = (currentPage - 1) * itemsPerPage;
  const endIdx = startIdx + itemsPerPage;
  const currentExps = exps.slice(startIdx, endIdx);

  return (
    <S.Wrapper>
      <S.Container>
        <S.TitleBox>
          <S.Question>어떤 활동에 대한 리뷰를 남기고 싶으신가요?</S.Question>
          <S.Line />
        </S.TitleBox>

        <List myexps={currentExps} setStep={setStep} />

        <PageNum
          myexps={exps}
          itemsPerPage={itemsPerPage}
          currentPage={currentPage}
          setCurrentPage={setCurrentPage}
        />
      </S.Container>
    </S.Wrapper>
  );
}

function PageNum(props) {
  const pageCount = Math.ceil(props.myexps.length / props.itemsPerPage);
  const pageArray = Array.from({ length: pageCount }, (_, i) => i + 1);

  return (
    <S.PageNumBox>
      {pageArray.map((num) => (
        <S.PageCountBox key={num} onClick={() => props.setCurrentPage(num)}>
          {num}
        </S.PageCountBox>
      ))}
    </S.PageNumBox>
  );
}
