import * as S from "./style.ts";
import BlackArrowImg from "../../assets/images/blackarrow.svg";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import Godd from "../../assets/images/HelloHand.svg";
import { hobbyApi } from "../../api/hobby";
import { authApi } from "../../api/auth";

interface MepDetailProps {
  setStep: (step: "index" | "detail") => void;
  exp: any;
}

const QUESTIONS = [
  "1. 이번 활동에서 가장 인상 깊었던 점은 무엇인가요?",
  "2. 활동을 하며 어려웠던 점이 있었다면 무엇인가요?",
  "3. 이 활동을 통해 새롭게 배운 점은 무엇인가요?",
  "4. 다음에 이 활동을 한다면 개선하고 싶은 점은 무엇인가요?",
  "5. 전반적인 소감이나 느낀 점을 자유롭게 작성해 주세요.",
];

export default function MepDetail({ setStep, exp }: MepDetailProps) {
  const navigate = useNavigate();

  /* ===============================
     상태
  ================================ */
  const [answers, setAnswers] = useState<string[]>(["", "", "", "", ""]);
  const [page, setPage] = useState<0 | 1>(0);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const handleChange = (index: number, value: string) => {
    const next = [...answers];
    next[index] = value;
    setAnswers(next);
  };

  const handleNext = async () => {
    // 첫 페이지 검증
    if (page === 0 && answers[0].length < 100) {
      toast.error("첫 번째 질문은 100자 이상 작성하셔야 합니다.");
      return;
    }

    if (page === 0) {
      setPage(1);
    } else {
      // 리뷰 제출
      await submitReview();
    }
  };

  const submitReview = async () => {
    if (isSubmitting) return;
    
    try {
      setIsSubmitting(true);
      
      // 사용자 정보 가져오기
      const userRes = await authApi.findCurrentUser();
      if (!userRes.success) {
        toast.error("로그인이 필요합니다.");
        navigate("/login");
        return;
      }
      
      // 리뷰 질문 생성 (activityReviewId 얻기)
      const reviewQuestionRes = await hobbyApi.createReviewQuestion({
        hobbyId: exp.linkedHobbyId,
      });
      
      if (!reviewQuestionRes.success) {
        navigate("/");
        return;
      }
      
      // 리뷰 답변 제출
      const reviewRes = await hobbyApi.answerReview({
        activityReviewId: exp.linkedHobbyId, // hobbyId를 사용
        userEmail: userRes.data.userEmail,
        hobbyId: exp.linkedHobbyId,
        socialAnswer: answers[0],
        learningAnswer: answers[1],
        planningAnswer: answers[2],
        focusAnswer: answers[3],
        creativityAnswer: answers[4],
      });
      
      if (reviewRes.success) {
        toast.success("리뷰가 제출되었습니다!");
        setTimeout(() => {
          navigate("/test/result", {
            state: {
              type: "ActivityReview",
              finalAverage: {
                socialScore: reviewRes.data.socialScore,
                learningScore: reviewRes.data.learningScore,
                planningScore: reviewRes.data.planningScore,
                focusScore: reviewRes.data.focusScore,
                creativityScore: reviewRes.data.creativityScore,
              },
              result: {
                hobbyScores: [
                  { hobbyName: reviewRes.data.hobby1, distance: 0 },
                  { hobbyName: reviewRes.data.hobby2, distance: 0 },
                  { hobbyName: reviewRes.data.hobby3, distance: 0 },
                ],
                channels: [],
              },
            },
          });
        }, 1500);
      } else {
        toast.error("리뷰 제출에 실패했습니다.");
      }
    } catch (error) {
      console.error(error);
      toast.error("오류가 발생했습니다.");
    } finally {
      setIsSubmitting(false);
    }
  };

  /* ===============================
     페이지별 질문 범위
  ================================ */
  const visibleQuestions =
    page === 0
      ? QUESTIONS.slice(0, 3)
      : QUESTIONS.slice(3, 5);

  const offset = page === 0 ? 0 : 3;

  return (
    <>
      <S.Wrapper>
        <S.Container>
          {/* 이전 버튼 */}
          <S.Arrow onClick={() => setStep("index")}>
            <img src={BlackArrowImg} alt="이전" />
          </S.Arrow>

          <S.QuestionBox>
            <S.Boxing>
              <img src={Godd} style={{width: 80}} alt="" />
              <S.Title>
                {exp.activityName} 활동에 대해 답변해 주세요.
              </S.Title>
            </S.Boxing>

            <S.TextPlace>
              {visibleQuestions.map((question, idx) => {
                const realIndex = idx + offset;

                return (
                  <S.DetailPlace key={realIndex}>
                    <S.Title style={{ fontSize: 16 , fontWeight: 500,marginLeft: 12, marginBottom: 12 }}>
                      {question}
                    </S.Title>
                    <S.TextingBox
                      placeholder={
                        realIndex === 0
                          ? "최소 100자 이상 작성해 주세요."
                          : "자유롭게 작성해 주세요."
                      }
                      value={answers[realIndex]}
                      onChange={(e) =>
                        handleChange(realIndex, e.target.value)
                      }
                    />
                  </S.DetailPlace>
                );
              })}
            </S.TextPlace>
          </S.QuestionBox>
        </S.Container>

        {/* 다음/제출 버튼 */}
        <S.Arrows onClick={handleNext} style={{ opacity: isSubmitting ? 0.5 : 1, cursor: isSubmitting ? "not-allowed" : "pointer" }}>
          {page === 1 ? (
            <img src={BlackArrowImg} alt="제출하기" />
          ) : (
            <img src={BlackArrowImg} alt="다음" />
          )}
        </S.Arrows>
      </S.Wrapper>

      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
