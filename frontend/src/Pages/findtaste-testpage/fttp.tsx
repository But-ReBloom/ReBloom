<<<<<<< HEAD
import * as S from './style.ts'
import FT_Header from "../../components/findtaste-Header/fth.tsx";
import Select_Box from '../../components/findtaste-selectbox/ftsb.tsx';
import { useState } from 'react';


function FT_TestPage() {
    const [page, setPage] = useState<number>(1);
    return (
        <S.Background>
            <title>Rebloom - 취향테스트</title>
            <S.Header>
                <FT_Header/>
            </S.Header>
            <S.MainContainer>
                <S.RowContainer>
                    <S.Select_Box>
                        <S.Page_num>{page}/10</S.Page_num>
                        <Select_Box pro='당신의 이름은 인가요?'/>
                        <S.Button_Box>
                            <S.Before_button onClick={() => {setPage(page-1)}}>{'<'}</S.Before_button>
                            <Select_Box pro='당신은 귀엽나요?'/>
                            <S.After_button onClick={() => {setPage(page+1)}}>{'>'}</S.After_button>
                        </S.Button_Box>
                        <Select_Box pro='당신은 사랑받기 위해 태어났나요?'/>
                    </S.Select_Box>
                </S.RowContainer>
            </S.MainContainer>
        </S.Background>
        )
}

// export const Proms() {
//     const [page, setPage] = useState<number>(1);
//     return (
//         <>
//             <S.Page_num>{page}/10</S.Page_num>
//             <Select_Box pro='질문1'/>
//             <S.Button_Box>
//                 <S.Before_button onClick={() => {setPage(page-1)}}>{'<'}</S.Before_button> 
//                 <Select_Box pro='질문2'/>
//                 <S.After_button onClick={() => {setPage(page+1)}}>{'>'}</S.After_button>
//             </S.Button_Box>
//             <Select_Box pro='질문3'/>
//         </>
//     )
// }

export default FT_TestPage;


=======
import * as S from "./style.ts";
import Select_Box from "../../components/findtaste-selectbox/ftsb.tsx";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import Header from "../../components/normal_header/nh.tsx";
import { hobbyApi } from "../../api/hobby";
import type { InitialTest } from "../../types/hobby";

// 알고리즘 구현 완료
export default function FT_TestPage() {
  const navigate = useNavigate();
  const [page, setPage] = useState(1);
  const [questionList, setQuestionList] = useState<InitialTest[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await hobbyApi.getQuestions();
        if (response.success) {
          setQuestionList(response.data);
        } else {
          toast.error("질문을 불러오는데 실패했습니다.");
        }
      } catch (error) {
        console.error(error);
        toast.error("서버 통신 오류!");
      } finally {
        setLoading(false);
      }
    };

    fetchQuestions();
  }, []);

  const totalPages = Math.ceil(questionList.length / 2);
  const startIndex = (page - 1) * 2;
  const currentQuestions = questionList.slice(startIndex, startIndex + 2);

  const [answers, setAnswers] = useState<(number | null)[]>([]);

  useEffect(() => {
    if (questionList.length > 0) {
      setAnswers(Array(questionList.length).fill(null));
    }
  }, [questionList]);

  const handleSelect = (
    questionIndex: number,
    selectedValue: number | null
  ) => {
    const updatedAnswers = [...answers];
    updatedAnswers[questionIndex] = selectedValue;
    setAnswers(updatedAnswers);
  };

  const handleNext = () => {
    const unselected = currentQuestions.find(
      (_, idx) => answers[startIndex + idx] === null
    );

    if (unselected !== undefined) {
      toast.warning("모든 질문을 선택해야 다음으로 넘어갈 수 있습니다.");
      return;
    }
    if (page < totalPages) setPage(page + 1);
  };

  // 🔥 최종 평균 계산 함수
  const calculateFinalAverage = () => {
    const results = questionList
      .map((q, i) => {
        const userValue = answers[i];
        if (userValue === null) return null;

        // 각 질문별 계산 ((userValue + weight) / 2)
        return {
          social: (userValue + q.initialTestSocialWeight) / 2,
          learning: (userValue + q.initialTestLearningWeight) / 2,
          planning: (userValue + q.initialTestPlanningWeight) / 2,
          focus: (userValue + q.initialTestFocusWeight) / 2,
          creativity: (userValue + q.initialTestCreativityWeight) / 2,
        };
      })
      .filter((r) => r !== null);

    const count = results.length;
    if (count === 0) return { social: 0, learning: 0, planning: 0, focus: 0, creativity: 0 };

    const finalScore = results.reduce(
      (acc, cur) => ({
        social: acc.social + cur.social,
        learning: acc.learning + cur.learning,
        planning: acc.planning + cur.planning,
        focus: acc.focus + cur.focus,
        creativity: acc.creativity + cur.creativity,
      }),
      { social: 0, learning: 0, planning: 0, focus: 0, creativity: 0 }
    );

    return {
      social: finalScore.social / count,
      learning: finalScore.learning / count,
      planning: finalScore.planning / count,
      focus: finalScore.focus / count,
      creativity: finalScore.creativity / count,
    };
  };

  const handleSubmit = async () => {
    const unselected = currentQuestions.find(
      (_, idx) => answers[startIndex + idx] === null
    );
    if (unselected !== undefined) {
      toast.warning("모든 질문을 선택해야 제출할 수 있습니다.");
      return;
    }

    const finalAverage = calculateFinalAverage();
    console.log("최종 평균 결과:", finalAverage);

    try {
      const response = await hobbyApi.recommendHobby({
        socialScore: finalAverage.social,
        learningScore: finalAverage.learning,
        planningScore: finalAverage.planning,
        focusScore: finalAverage.focus,
        creativityScore: finalAverage.creativity,
      });

      if (response.success) {
        navigate("/test/result", {
          state: { 
            message: "취향테스트 완료!", 
            type: "HobbyTest", 
            finalAverage,
            recommendations: response.data 
          },
        });
      } else {
        toast.error(response.message || "결과를 받아오는데 실패했습니다.");
      }
    } catch (error) {
      console.error(error);
      toast.error("서버 통신 오류!");
    }
  };

  if (loading) {
    return <div>Loading...</div>; // Or a proper loading component
  }

  return (
    <S.Background>
      <S.Header>
        <Header />
      </S.Header>

      <S.MainContainer>
        <S.RowContainer>
          <S.Select_Box>
            <S.Page_num>
              {page}/{totalPages}
            </S.Page_num>

            <div>
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
            </div>

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
        </S.RowContainer>
      </S.MainContainer>

      <ToastContainer position="top-right" autoClose={2000} />
    </S.Background>
  );
}
>>>>>>> 240c33b22f560ae63c68a42ea4015ae853a1b962
