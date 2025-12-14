import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";
import MepDetail from "../myexps_detail/mep-detail.tsx";
import { useState, useEffect } from "react";
import { hobbyApi } from "../../api/hobby";
import type { FindActivityResponse } from "../../types/hobby";
import { toast } from "react-toastify";

export default function MepCb() {
  const [step, setStep] = useState("index"); // 'index' | 'detail'
  const [selectedExp, setSelectedExp] = useState<FindActivityResponse | null>(null); // 선택된 활동

  return (
    <>
      {step === "index" && (
        <Index setStep={setStep} setSelectedExp={setSelectedExp} />
      )}
      {step === "detail" && selectedExp && (
        <MepDetail setStep={setStep} exp={selectedExp} />
      )}
    </>
  );
}

function Index({ setStep, setSelectedExp }: { setStep: any, setSelectedExp: any }) {
  const [exps, setExps] = useState<FindActivityResponse[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchActivities = async () => {
      try {
        const response = await hobbyApi.findAllActivities();
        if (response.success) {
          setExps(response.data);
        } else {
          toast.error(response.message || "활동 목록을 불러오는데 실패했습니다.");
        }
      } catch (error) {
        console.error(error);
        toast.error("서버 통신 오류!");
      } finally {
        setLoading(false);
      }
    };

    fetchActivities();
  }, []);

  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 7;

  const startIdx = (currentPage - 1) * itemsPerPage;
  const endIdx = startIdx + itemsPerPage;
  const currentExps = exps.slice(startIdx, endIdx);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <S.Wrapper>
      <S.Container>
        <S.TitleBox>
          <S.Question>어떤 활동에 대한 리뷰를 남기고 싶으신가요?</S.Question>
          <S.Line />
        </S.TitleBox>

        <List
          myexps={currentExps}
          setStep={setStep}
          setSelectedExp={setSelectedExp}
        />

        <PageNum
          myexps={exps}
          itemsPerPage={itemsPerPage}
          setCurrentPage={setCurrentPage}
        />
      </S.Container>
    </S.Wrapper>
  );
}

interface PageNumProps {
  myexps: any[];
  itemsPerPage: number;
  setCurrentPage: (page: number) => void;
}

function PageNum({ myexps, itemsPerPage, setCurrentPage }: PageNumProps) {
  const pageCount = Math.ceil(myexps.length / itemsPerPage);
  const pageArray = Array.from({ length: pageCount }, (_, i) => i + 1);

  return (
    <S.PageNumBox>
      {pageArray.map((num) => (
        <S.PageCountBox key={num} onClick={() => setCurrentPage(num)}>
          {num}
        </S.PageCountBox>
      ))}
    </S.PageNumBox>
  );
}
