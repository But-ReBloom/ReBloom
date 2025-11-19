import * as S from "./style.ts";
import Select_Box from "../../components/findtaste-selectbox/ftsb.tsx";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import Header from "../../components/normal_header/nh.tsx";

// 알고리즘 구현 완료
export default function FT_TestPage() {
  const navigate = useNavigate();
  const [page, setPage] = useState(1);

  // 질문 받아오기
  // const QuestionData = fetch("/hobby-test/questions", {
  //   method: "GET",
  //   headers: {
  //     "Content-Type": "application/json",
  //   },
  //   body: JSON.stringify({
  //   }),
  // })
  //   .then((response) => {
  //     if (!response.ok) {
  //       throw new Error(`HTTP error! status: ${response.status}`);
  //     }
  //     return response.json();
  //   })
  //   .then((data) => {
  //     console.log(data);
  //   })
  //   .catch((error) => {
  //     console.error(error);
  //   });

  const questionData = {
    data: [
      {
        initialTestId: 21,
        initialTestSetNumber: 4,
        initialTestCategory: "Social",
        initialTestQuestion: "파티나 행사 초대에 기대가 되나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 22,
        initialTestSetNumber: 4,
        initialTestCategory: "Learning",
        initialTestQuestion: "미래를 상상하며 배우는 편인가요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 23,
        initialTestSetNumber: 4,
        initialTestCategory: "Planning",
        initialTestQuestion: "정해진 스케줄을 따르는 걸 선호하나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 24,
        initialTestSetNumber: 4,
        initialTestCategory: "Focus",
        initialTestQuestion: "누군가 나보다 잘하면 자극을 받나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 25,
        initialTestSetNumber: 4,
        initialTestCategory: "Creativity",
        initialTestQuestion: "그림을 보면 '나도 그려보고 싶다'고 느끼나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 26,
        initialTestSetNumber: 5,
        initialTestCategory: "Social",
        initialTestQuestion: "단체 프로젝트가 개인 프로젝트보다 즐겁나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 27,
        initialTestSetNumber: 5,
        initialTestCategory: "Learning",
        initialTestQuestion: "큰 그림부터 이해하는 편인가요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 28,
        initialTestSetNumber: 5,
        initialTestCategory: "Planning",
        initialTestQuestion: "계획에 따라 움직이는 걸 선호하나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 29,
        initialTestSetNumber: 5,
        initialTestCategory: "Focus",
        initialTestQuestion: "게임할 때 승패가 중요한가요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
      {
        initialTestId: 30,
        initialTestSetNumber: 5,
        initialTestCategory: "Creativity",
        initialTestQuestion: "글을 읽을 때 '나도 써보고 싶다'는 생각이 드나요?",
        initialTestSocialWeight: -2,
        initialTestLearningWeight: -1,
        initialTestPlanningWeight: 0,
        initialTestFocusWeight: 1,
        initialTestCreativityWeight: 2,
      },
    ],
  };
  const questionList = questionData.data;

  const totalPages = Math.ceil(questionList.length / 2);
  const startIndex = (page - 1) * 2;
  const currentQuestions = questionList.slice(startIndex, startIndex + 2);

  const [answers, setAnswers] = useState<(number | null)[]>(
    Array(questionList.length).fill(null)
  );

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

    // 서버 전송 payload
    const payload = {
      answers,
      tests: questionList.map((q, i) => ({
        testId: q.initialTestId,
        category: q.initialTestCategory,
        selected: answers[i],
      })),
      finalAverage,
    };

    console.log("서버 전송 데이터:", payload);

    try {
      const response = await fetch("", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });
      if (!response.ok) throw new Error("데이터 전송 실패");

      navigate("/test/result", {
        state: { message: "취향테스트 완료!", type: "HobbyTest", finalAverage },
      });
    } catch (error) {
      console.error(error);
      toast.error("서버 통신 오류!");
    }
  };

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
