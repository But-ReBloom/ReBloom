import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";
import MepDetail from "../myexps_detail/mep-detail.tsx";
import { useState, useEffect } from "react";
import LoadingPage from "../../Pages/loadingpage/loading.tsx";
import { hobbyApi } from "../../api/hobby";
import { toast } from "react-toastify";

/* ===============================
   타입 정의
================================ */

export type FindActivityResponse = {
  activityId: number;
  activityName: string;     // 활동명
  activityDate: string;     // 활동 날짜
  tags: string[];           // 관련 태그
  isReviewed: boolean;      // 리뷰 여부
  linkedHobbyId: number;    // 연결된 취미 ID
};

/* ===============================
   메인 컴포넌트
================================ */

export default function MepCb() {
  const [step, setStep] = useState<"index" | "detail">("index");
  const [selectedExp, setSelectedExp] =
    useState<FindActivityResponse | null>(null);

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

/* ===============================
   리스트 페이지
================================ */

function Index({
  setStep,
  setSelectedExp,
}: {
  setStep: (step: "index" | "detail") => void;
  setSelectedExp: (exp: FindActivityResponse) => void;
}) {
  const [exps, setExps] = useState<FindActivityResponse[]>([]);
  const [loading, setLoading] = useState(true);

  /* API 데이터 로딩 */
  useEffect(() => {
    const fetchActivities = async () => {
      try {
        const response = await hobbyApi.findAllActivities();
        if (response.success) {
          const mappedActivities = response.data.map((item) => ({
            activityId: item.activityId,
            activityName: item.activityName,
            activityDate: item.activityRecent,
            tags: [],
            isReviewed: false,
            linkedHobbyId: item.linkedHobbyId,
          }));
          setExps(mappedActivities);
        } else {
          toast.error("활동 목록을 불러오는데 실패했습니다.");
        }
      } catch (error) {
        console.error(error);
        toast.error("서버 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };
    fetchActivities();
  }, []);

  /* 페이지네이션 */
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;

  const startIdx = (currentPage - 1) * itemsPerPage;
  const currentExps = exps.slice(startIdx, startIdx + itemsPerPage);

  if (loading) return <LoadingPage/>;

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
          totalItems={exps.length}
          itemsPerPage={itemsPerPage}
          setCurrentPage={setCurrentPage}
        />
      </S.Container>
    </S.Wrapper>
  );
}

/* ===============================
   페이지 번호
================================ */

function PageNum({
  totalItems,
  itemsPerPage,
  setCurrentPage,
}: {
  totalItems: number;
  itemsPerPage: number;
  setCurrentPage: (page: number) => void;
}) {
  const pageCount = Math.ceil(totalItems / itemsPerPage);

  return (
    <S.PageNumBox>
      {Array.from({ length: pageCount }, (_, i) => (
        <S.PageCountBox key={i} onClick={() => setCurrentPage(i + 1)}>
          {i + 1}
        </S.PageCountBox>
      ))}
    </S.PageNumBox>
  );
}
