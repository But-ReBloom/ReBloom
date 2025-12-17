import * as S from "./style.ts";
import Select_Box from "../../components/findtaste-selectbox/ftsb.tsx";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import Header from "../../components/normal_header/nh.tsx";
import type { InitialTest } from "../../types/hobby";

/* ===============================
   더미 데이터 (10문항 / API 명세 기반)
================================ */

const dummyQuestions: InitialTest[] = [
  {
    initialTestId: 1,
    initialTestSetNumber: 1,
    initialTestCategory: "Social",
    initialTestQuestion: "사람들과 어울리는 것을 즐긴다.",
    initialTestSocialWeight: 4,
    initialTestLearningWeight: 1,
    initialTestPlanningWeight: 1,
    initialTestFocusWeight: 0,
    initialTestCreativityWeight: 1,
  },
  {
    initialTestId: 2,
    initialTestSetNumber: 1,
    initialTestCategory: "Learning",
    initialTestQuestion: "새로운 지식을 배우는 것이 좋다.",
    initialTestSocialWeight: 1,
    initialTestLearningWeight: 4,
    initialTestPlanningWeight: 1,
    initialTestFocusWeight: 1,
    initialTestCreativityWeight: 2,
  },
  {
    initialTestId: 3,
    initialTestSetNumber: 2,
    initialTestCategory: "Planning",
    initialTestQuestion: "일을 시작하기 전 계획을 세운다.",
    initialTestSocialWeight: 0,
    initialTestLearningWeight: 1,
    initialTestPlanningWeight: 4,
    initialTestFocusWeight: 2,
    initialTestCreativityWeight: 0,
  },
  {
    initialTestId: 4,
    initialTestSetNumber: 2,
    initialTestCategory: "Focus",
    initialTestQuestion: "한 가지 일에 오래 집중할 수 있다.",
    initialTestSocialWeight: 0,
    initialTestLearningWeight: 1,
    initialTestPlanningWeight: 2,
    initialTestFocusWeight: 4,
    initialTestCreativityWeight: 0,
  },
  {
    initialTestId: 5,
    initialTestSetNumber: 3,
    initialTestCategory: "Creativity",
    initialTestQuestion: "창의적인 아이디어가 자주 떠오른다.",
    initialTestSocialWeight: 1,
    initialTestLearningWeight: 2,
    initialTestPlanningWeight: 0,
    initialTestFocusWeight: 1,
    initialTestCreativityWeight: 4,
  },
  {
    initialTestId: 6,
    initialTestSetNumber: 3,
    initialTestCategory: "Social",
    initialTestQuestion: "팀 활동이 혼자 하는 것보다 좋다.",
    initialTestSocialWeight: 4,
    initialTestLearningWeight: 1,
    initialTestPlanningWeight: 1,
    initialTestFocusWeight: 0,
    initialTestCreativityWeight: 1,
  },
  {
    initialTestId: 7,
    initialTestSetNumber: 4,
    initialTestCategory: "Learning",
    initialTestQuestion: "배운 내용을 바로 적용해보고 싶다.",
    initialTestSocialWeight: 1,
    initialTestLearningWeight: 4,
    initialTestPlanningWeight: 1,
    initialTestFocusWeight: 1,
    initialTestCreativityWeight: 2,
  },
  {
    initialTestId: 8,
    initialTestSetNumber: 4,
    initialTestCategory: "Planning",
    initialTestQuestion: "목표를 세우고 관리하는 편이다.",
    initialTestSocialWeight: 0,
    initialTestLearningWeight: 1,
    initialTestPlanningWeight: 4,
    initialTestFocusWeight: 2,
    initialTestCreativityWeight: 0,
  },
  {
    initialTestId: 9,
    initialTestSetNumber: 5,
    initialTestCategory: "Focus",
    initialTestQuestion: "방해 요소가 있어도 집중을 유지한다.",
    initialTestSocialWeight: 0,
    initialTestLearningWeight: 1,
    initialTestPlanningWeight: 2,
    initialTestFocusWeight: 4,
    initialTestCreativityWeight: 0,
  },
  {
    initialTestId: 10,
    initialTestSetNumber: 5,
    initialTestCategory: "Creativity",
    initialTestQuestion: "기존 방식보다 새로운 방식을 선호한다.",
    initialTestSocialWeight: 1,
    initialTestLearningWeight: 2,
    initialTestPlanningWeight: 0,
    initialTestFocusWeight: 1,
    initialTestCreativityWeight: 4,
  },
];

/* ===============================
   컴포넌트
================================ */

export default function FT_TestPage() {
  const navigate = useNavigate();

  const QUESTIONS_PER_PAGE = 2;
  const TOTAL_PAGES = 5;

  const [page, setPage] = useState(1);
  const [questionList, setQuestionList] = useState<InitialTest[]>([]);
  const [answers, setAnswers] = useState<(number | null)[]>([]);
  const [loading, setLoading] = useState(true);

  /* 더미 데이터 로딩 */
  useEffect(() => {
    setQuestionList(dummyQuestions);
    setAnswers(Array(dummyQuestions.length).fill(null));
    setLoading(false);
  }, []);

  const startIndex = (page - 1) * QUESTIONS_PER_PAGE;
  const currentQuestions = questionList.slice(
    startIndex,
    startIndex + QUESTIONS_PER_PAGE
  );

  /* 선택 처리 */
  const handleSelect = (index: number, value: number | null) => {
    const updated = [...answers];
    updated[index] = value;
    setAnswers(updated);
  };

  /* 다음 페이지 */
  const handleNext = () => {
    const unselected = currentQuestions.find(
      (_, idx) => answers[startIndex + idx] === null
    );

    if (unselected) {
      toast.warning("모든 질문을 선택해야 합니다.");
      return;
    }

    if (page < TOTAL_PAGES) setPage(page + 1);
  };

  /* 평균 계산 */
  const calculateFinalAverage = () => {
    const valid = questionList
      .map((q, i) => {
        const v = answers[i];
        if (v === null) return null;
        return {
          social: (v + q.initialTestSocialWeight) / 2,
          learning: (v + q.initialTestLearningWeight) / 2,
          planning: (v + q.initialTestPlanningWeight) / 2,
          focus: (v + q.initialTestFocusWeight) / 2,
          creativity: (v + q.initialTestCreativityWeight) / 2,
        };
      })
      .filter(Boolean) as any[];

    const count = valid.length;

    const sum = valid.reduce(
      (a, c) => ({
        social: a.social + c.social,
        learning: a.learning + c.learning,
        planning: a.planning + c.planning,
        focus: a.focus + c.focus,
        creativity: a.creativity + c.creativity,
      }),
      { social: 0, learning: 0, planning: 0, focus: 0, creativity: 0 }
    );

    return {
      social: sum.social / count,
      learning: sum.learning / count,
      planning: sum.planning / count,
      focus: sum.focus / count,
      creativity: sum.creativity / count,
    };
  };

  /* 제출 */
  const handleSubmit = () => {
    const finalAverage = calculateFinalAverage();

    navigate("/test/result", {
      state: {
        type: "HobbyTest",
        finalAverage,
      },
    });
  };

  if (loading) return <div>Loading...</div>;

  return (
    <S.Background>
      <Header />

      <S.MainContainer>
        <S.Select_Box>
          <S.Page_num>
            {page} / {TOTAL_PAGES}
          </S.Page_num>

          {currentQuestions.map((q, idx) => {
            const questionIndex = startIndex + idx;
            return (
              <Select_Box
                key={q.initialTestId}
                pro={q.initialTestQuestion}
                weights={[2, 1, 0, -1, -2]}
                selectedValue={answers[questionIndex]}
                onSelect={(val) => handleSelect(questionIndex, val)}
              />
            );
          })}

          <S.Button_Box>
            {page > 1 && (
              <S.Before_button onClick={() => setPage(page - 1)}>
                <S.NextButton>이전</S.NextButton>
              </S.Before_button>
            )}

            {page < TOTAL_PAGES ? (
              <S.After_button onClick={handleNext}>
                <S.NextButton>다음</S.NextButton>
              </S.After_button>
            ) : (
              <S.After_button onClick={handleSubmit}>
                <S.NextButton>제출하기</S.NextButton>
              </S.After_button>
            )}
          </S.Button_Box>
        </S.Select_Box>
      </S.MainContainer>

      <ToastContainer position="top-right" autoClose={2000} />
    </S.Background>
  );
}
