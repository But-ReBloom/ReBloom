import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";
import MepDetail from "../myexps_detail/mep-detail.tsx";
import { useState } from "react";

export default function MepCb() {
  const [step, setStep] = useState("index"); // 'index' | 'detail'
  const [selectedExp, setSelectedExp] = useState(null); // 선택된 활동

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

function Index({ setStep, setSelectedExp }) {
  const exps = [
    {
      data: {
        activityName: "Project Alpha 0",
        activityStart: "2024-05-12",
        activityRecent: "2024-06-02",
        userEmail: "user0_xzp@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Workshop Delta 1",
        activityStart: "2024-02-19",
        activityRecent: "2024-02-27",
        userEmail: "user1_kqa@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Seminar Nova 2",
        activityStart: "2024-03-01",
        activityRecent: "2024-03-22",
        userEmail: "user2_wmf@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Mission Orbit 3",
        activityStart: "2024-07-08",
        activityRecent: "2024-07-14",
        userEmail: "user3_asd@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Activity Pulse 4",
        activityStart: "2024-01-11",
        activityRecent: "2024-01-30",
        userEmail: "user4_qpt@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Training Sigma 5",
        activityStart: "2024-09-05",
        activityRecent: "2024-09-12",
        userEmail: "user5_zla@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Camp Horizon 6",
        activityStart: "2024-10-02",
        activityRecent: "2024-10-29",
        userEmail: "user6_uxr@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Study Vector 7",
        activityStart: "2024-06-17",
        activityRecent: "2024-06-25",
        userEmail: "user7_mnh@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Session Echo 8",
        activityStart: "2024-04-03",
        activityRecent: "2024-04-21",
        userEmail: "user8_fbr@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Program Lunar 9",
        activityStart: "2024-08-13",
        activityRecent: "2024-08-31",
        userEmail: "user9_gqe@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Task Vertex 10",
        activityStart: "2024-02-06",
        activityRecent: "2024-02-28",
        userEmail: "user10_tmk@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Event Omega 11",
        activityStart: "2024-03-25",
        activityRecent: "2024-04-15",
        userEmail: "user11_ewl@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Challenge Rune 12",
        activityStart: "2024-11-01",
        activityRecent: "2024-11-20",
        userEmail: "user12_hcz@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Activity Solar 13",
        activityStart: "2024-12-10",
        activityRecent: "2024-12-25",
        userEmail: "user13_yts@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
    {
      data: {
        activityName: "Workshop Neon 14",
        activityStart: "2024-05-03",
        activityRecent: "2024-05-28",
        userEmail: "user14_plo@example.com",
      },
      success: true,
      message: null,
      error: null,
    },
  ];

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

function PageNum({ myexps, itemsPerPage, setCurrentPage }) {
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
