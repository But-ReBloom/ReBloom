import * as S from "./style.ts";
import BlackArrowImg from "../../assets/images/blackarrow.svg";
import QuestionBox from "../questionbox/qb.tsx";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

interface MepDetailProps {
  setStep: (step: string) => void;
  exp: any;
}

export default function MepDetail({ setStep, exp }: MepDetailProps) {
  const navigate = useNavigate();
  const [ReviewData, setWrittenData] = useState("");
  const [satisfaction, setSatisfaction] = useState<number | null>(null);

  const handleSubmit = () => {
    if (ReviewData.length < 100) {
      toast.error("리뷰는 100자 이상 작성하셔야 합니다.");
    } else {
      console.log("Satisfaction:", satisfaction);
      // TODO: Implement API call to submit review
      navigate("/thankyou", {
        state: {
          message: `활동리뷰를 마무리한 사람이세요~~~`,
          type: "ExpsReview",
        },
      });
    }
  };

  return (
    <>
      <S.Wrapper>
        <S.Container>
          {/* 이전 버튼 */}
          <S.Arrow onClick={() => setStep("index")}>
            <img src={BlackArrowImg} alt="이전으로 가는 화살표" />
          </S.Arrow>

          <S.QuestionBox>
            <S.Boxing>
              <S.Title>{exp.activityName} 활동에 대해 만족하시나요?</S.Title>
              <QuestionBox onSelect={setSatisfaction} />
            </S.Boxing>

            <S.TextPlace>
              <S.DetailPlace>
                <S.Title style={{ marginLeft: "12px", marginBottom: "12px" }}>
                  이번 활동에 대한 리뷰를 남겨주십시오.
                </S.Title>
                <S.TextingBox
                  placeholder="최소 100자 이상으로 작성해주십시오."
                  value={ReviewData}
                  onChange={(e) => setWrittenData(e.target.value)}
                />
              </S.DetailPlace>

              <S.DetailPlace>
                <S.Title style={{ marginLeft: "12px", marginBottom: "12px" }}>
                  건의 사항
                </S.Title>
                <S.TextingBox placeholder="저희 사이트에 건의 사항이 있으시면 남겨주십시오." />
              </S.DetailPlace>
            </S.TextPlace>
          </S.QuestionBox>
        </S.Container>

        <S.Arrows onClick={handleSubmit}>
          <img src={BlackArrowImg} alt="" />
        </S.Arrows>
      </S.Wrapper>

      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
