import * as S from "./style.ts";
import Select_Box from "../../components/findtaste-selectbox/ftsb.tsx";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import Header from "../../components/normal_header/nh.tsx";
import type { InitialTest } from "../../types/hobby";
import { hobbyApi } from "../../api/hobby";
import LoadingPage from "../loadingpage/loading.tsx";


/* ===============================
   컴포넌트
================================ */

export default function FT_TestPage() {
  const navigate = useNavigate();

  const QUESTIONS_PER_PAGE = 2;

  const [page, setPage] = useState(1);
  const [questionList, setQuestionList] = useState<InitialTest[]>([]);
  const [answers, setAnswers] = useState<(number | null)[]>([]);
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [totalPages, setTotalPages] = useState(0);

  /* API 데이터 로딩 */
  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await hobbyApi.getQuestions();
        if (response.success) {
          setQuestionList(response.data);
          setAnswers(Array(response.data.length).fill(null));
          setTotalPages(Math.ceil(response.data.length / QUESTIONS_PER_PAGE));
        } else {
          toast.error("질문을 불러오는데 실패했습니다.");
        }
      } catch (error) {
        console.error(error);
        toast.error("서버 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };
    fetchQuestions();
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

    if (page < totalPages) setPage(page + 1);
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
      socialScore: sum.social / count,
      learningScore: sum.learning / count,
      planningScore: sum.planning / count,
      focusScore: sum.focus / count,
      creativityScore: sum.creativity / count,
    };
  };

  /* 제출 */
  const handleSubmit = async () => {
    const finalAverage = calculateFinalAverage();
    
    // 로그인 여부 확인
    const token = localStorage.getItem("token");
    if (!token) {
      // 로그인 안 되어 있으면 점수 저장 후 로그인 페이지로 이동
      localStorage.setItem("pendingHobbyTest", JSON.stringify(finalAverage));
      toast.info("로그인이 필요합니다. 로그인 후 결과를 확인할 수 있습니다.");
      navigate("/login", { state: { from: "hobby-test" } });
      return;
    }

    setSubmitting(true);

    try {
      const response = await hobbyApi.recommendHobby(finalAverage);
      if (response.success) {
        navigate("/test/result", {
          state: {
            type: "HobbyTest",
            finalAverage,
            result: response.data,
          },
        });
      } else {
        toast.error("결과를 불러오는데 실패했습니다.");
      }
    } catch (error) {
      console.error(error);
      toast.error("서버 오류가 발생했습니다.");
    } finally {
      setSubmitting(false);
    }
  };

  if (loading || submitting) return <LoadingPage />;

  return (
    <S.Background>
      <Header />

      <S.MainContainer>
        <S.Select_Box>
          <S.Page_num>
            {page} / {totalPages}
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

            {page < totalPages ? (
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
