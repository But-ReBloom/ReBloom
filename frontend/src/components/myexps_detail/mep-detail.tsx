import * as S from "./style.ts";
import BlackArrowImg from "../../assets/images/blackarrow.svg";
import QuestionBox from "../questionbox/qb.tsx";
import { useNavigate } from "react-router-dom";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";

export default function MyexpsDetail() {
  const navigate = useNavigate();

  const [ReviewData, setWrittenData] = useState("");

  const handleSubmit = () => {
    if (ReviewData.length < 100) {
      toast.error("리뷰는 100자 이상 작성하셔야 합니다.");
    } else {
      navigate("/", {
        state: { message: `감사합니다! <br /> 다음에 또 이용해주세요!` },
      });
    }
  };

  return (
    <>
      <S.Wrapper>
        <S.Container>
          <S.Arrow>
            <img src={BlackArrowImg} alt="이전으로 가는 화살표" />
          </S.Arrow>
          <S.QuestionBox>
            <S.Boxing>
              <S.Title>
                당신은 활동 리뷰에 거짓없고, 사실만 작성할 것입니까?
              </S.Title>
              <QuestionBox />
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
                ></S.TextingBox>
              </S.DetailPlace>
              <S.DetailPlace>
                <S.Title style={{ marginLeft: "12px", marginBottom: "12px" }}>
                  건의 사항
                </S.Title>
                <S.TextingBox placeholder="저희 사이트에 건의 사항이 있으시면 남겨주십시오."></S.TextingBox>
              </S.DetailPlace>
            </S.TextPlace>
          </S.QuestionBox>
        </S.Container>
        <S.Arrows onClick={handleSubmit}>
          <img src={BlackArrowImg} alt="이전으로 가는 화살표" />
        </S.Arrows>
      </S.Wrapper>
      <ToastContainer position="top-right" autoClose={2000} />
    </>
  );
}
