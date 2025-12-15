import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";
import MepDetail from "../myexps_detail/mep-detail.tsx";
import { useState, useEffect } from "react";

/* ===============================
   타입 정의
================================ */

export type FindActivityResponse = {
  activityId: number;
  activityName: string;     // 활동명
  activityDate: string;     // 활동 날짜
  tags: string[];           // 관련 태그
  isReviewed: boolean;      // 리뷰 여부
};

/* ===============================
   더미 데이터
================================ */

const dummyActivities: FindActivityResponse[] = [
  {
    activityId: 1,
    activityName: "봉사 활동 참여",
    activityDate: "2024-11-20",
    tags: ["사회성", "봉사", "팀활동"],
    isReviewed: true,
  },
  {
    activityId: 2,
    activityName: "스터디 모임",
    activityDate: "2024-11-18",
    tags: ["학습", "자기계발"],
    isReviewed: false,
  },
  {
    activityId: 3,
    activityName: "플래너 작성",
    activityDate: "2024-11-15",
    tags: ["계획", "자기관리"],
    isReviewed: true,
  },
  {
    activityId: 4,
    activityName: "명상 & 요가",
    activityDate: "2024-11-10",
    tags: ["집중력", "휴식"],
    isReviewed: false,
  },
  {
    activityId: 5,
    activityName: "그림 그리기",
    activityDate: "2024-11-08",
    tags: ["창의성", "예술"],
    isReviewed: true,
  },
  {
    activityId: 6,
    activityName: "팀 스포츠",
    activityDate: "2024-11-05",
    tags: ["사회성", "운동"],
    isReviewed: false,
  },
  {
    activityId: 7,
    activityName: "코딩 프로젝트",
    activityDate: "2024-11-01",
    tags: ["학습", "계획", "개발"],
    isReviewed: true,
  },
];

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

  /* 더미 데이터 로딩 */
  useEffect(() => {
    setTimeout(() => {
      setExps(dummyActivities);
      setLoading(false);
    }, 400);
  }, []);

  /* 페이지네이션 */
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;

  const startIdx = (currentPage - 1) * itemsPerPage;
  const currentExps = exps.slice(startIdx, startIdx + itemsPerPage);

  if (loading) return <div>Loading...</div>;

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
