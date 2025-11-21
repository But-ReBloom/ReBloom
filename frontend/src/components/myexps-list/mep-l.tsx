import * as S from "./style.ts";
import Page from "../../assets/images/page.svg";
import Clock from "../../assets/images/mi_clock.svg";
import Tag from "../../assets/images/Tag.svg";
import Submitimg from "../../assets/images/submitinfo.svg";

export default function MepL({ myexps, setStep, setSelectedExp }) {
  return (
    <>
      <S.Head>
        <S.Locate>
          <S.Tags>활동명</S.Tags>
          <S.Tags>활동 날짜</S.Tags>
        </S.Locate>
        <S.Locate>
          <S.Tags>관련 태그</S.Tags>
          <S.Tags>리뷰 여부</S.Tags>
        </S.Locate>
      </S.Head>

      <S.Info_Body>
        {myexps.map((exp, idx) => (
          <OneExpInfo
            key={idx}
            exp={exp}
            setStep={setStep}
            setSelectedExp={setSelectedExp}
          />
        ))}
      </S.Info_Body>
    </>
  );
}

function OneExpInfo({ exp, setStep, setSelectedExp }) {
  return (
    <S.Wrraper
      onClick={() => {
        setSelectedExp(exp); // 선택한 활동 저장
        setStep("detail"); // detail 화면으로 이동
      }}
    >
      <S.Container>
        <S.Locates style={{ textDecoration: "none" }}>
          <S.ExpInfo>
            <img
              src={Page}
              alt="페이지 아이콘"
              style={{ width: "20px", height: "20px" }}
            />
            {exp.data.activityName}
          </S.ExpInfo>

          <S.ExpInfo>
            <img
              src={Clock}
              alt="활동 날짜"
              style={{ width: "20px", height: "20px" }}
            />
            {exp.data.activityStart}
          </S.ExpInfo>
        </S.Locates>

        <S.Locate>
          <S.ExpInfo>
            <img
              src={Tag}
              alt="태그"
              style={{ width: "20px", height: "20px" }}
            />
            관련 태그
          </S.ExpInfo>

          <S.ExpInfo>
            <img
              src={Submitimg}
              alt="제출 여부"
              style={{ width: "24px", height: "24px" }}
            />
            제출
          </S.ExpInfo>
        </S.Locate>
      </S.Container>
    </S.Wrraper>
  );
}
