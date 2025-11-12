import * as S from "./style.ts";
import Select_Box from "../../components/findtaste-selectbox/ftsb.tsx";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast, ToastContainer } from "react-toastify";
import Header from "../../components/normal_header/nh.tsx";

export default function FT_TestPage() {
  const navigate = useNavigate();
  const [page, setPage] = useState(1);

  // 질문 받아오기
  // const QuestionData = fetch("", {
  //   method: "GET",
  //   headers: {
  //     "Content-Type": "application/json",
  //   },
  //   body: JSON.stringify({
  //     id: 0,
  //     setNo: 0,
  //     category: "string",
  //     question: "string",
  //     weight1: 0,
  //     weight2: 0,
  //     weight3: 0,
  //     weight4: 0,
  //     weight5: 0,
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

  const questions = [
    "당신의 이름은 인가요?",
    "당신은 귀엽나요?",
    "당신은 사랑받기 위해 태어났나요?",
    "당신은 새로운 것을 좋아하나요?",
    "당신은 리더십이 있나요?",
    "당신은 감정 표현이 풍부한가요?",
    "당신은 계획적인 편인가요?",
    "당신은 팀 활동을 좋아하나요?",
    "당신은 혼자 있는 걸 좋아하나요?",
    "당신은 도전을 즐기나요?",
  ];

  const totalPages = Math.ceil(questions.length / 2);
  const startIndex = (page - 1) * 2;
  const currentQuestions = questions.slice(startIndex, startIndex + 2);

  const [answers, setAnswers] = useState<(number | null)[]>(
    Array(questions.length).fill(null)
  );

  const handleSelect = (
    questionIndex: number,
    selectedValue: number | null
  ) => {
    const updatedAnswers = [...answers];
    updatedAnswers[questionIndex] = selectedValue;
    setAnswers(updatedAnswers);
  };

  // ✅ 다음 페이지 클릭 시 체크
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

  console.log(answers);

  // ✅ 제출 버튼 클릭 시 체크
  const handleSubmit = async () => {
    const unselected = currentQuestions.find(
      (_, idx) => answers[startIndex + idx] === null
    );

    if (unselected !== undefined) {
      toast.warning("모든 질문을 선택해야 제출할 수 있습니다.");
      return;
    }

    try {
      const response = await fetch("", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: "test@example.com",
          password: "1234",
        }),
      });

      if (!response.ok) {
        throw new Error("데이터 전송 실패");
      }

      const data = await response.json();

      console.log("응답 데이터:", data);
      console.log("최종 응답:", answers);

      navigate("/test/result", {
        state: { message: `감사합니다! <br /> 다음에 또 이용해주세요!` },
      });
    } catch (error) {
      console.error(error);
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
                    key={questionIndex}
                    pro={q}
                    selectedValue={answers[questionIndex]} // 이전 선택값 복원
                    onSelect={(val) => handleSelect(questionIndex, val)} // 선택 시 업데이트
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
