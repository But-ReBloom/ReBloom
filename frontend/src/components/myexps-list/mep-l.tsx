import * as S from "./style.ts";
import Page from "../../assets/images/page.svg";
import Clock from "../../assets/images/mi_clock.svg";
import Tag from "../../assets/images/Tag.svg";
import Submitimg from "../../assets/images/submitinfo.svg";

/* ===============================
   타입
================================ */

interface Activity {
  activityName: string;
  activityDate: string;
  tags: string[];
  isReviewed: boolean;
}

interface MepLProps {
  myexps: Activity[];
  setStep: (step: "index" | "detail") => void;
  setSelectedExp: (exp: Activity) => void;
}

/* ===============================
   리스트 컴포넌트
================================ */

export default function MepL({ myexps, setStep, setSelectedExp }: MepLProps) {
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

/* ===============================
   개별 활동 행
================================ */

interface OneExpInfoProps {
  exp: Activity;
  setStep: (step: "index" | "detail") => void;
  setSelectedExp: (exp: Activity) => void;
}

function OneExpInfo({ exp, setStep, setSelectedExp }: OneExpInfoProps) {
  return (
    <S.Wrraper
      onClick={() => {
        setSelectedExp(exp);
        setStep("detail");
      }}
    >
      <S.Container>
        {/* ===== 왼쪽 ===== */}
        <S.Locates>
          <S.ExpInfo>
            <img src={Page} alt="활동명" width={20} height={20} />
            {exp.activityName}
          </S.ExpInfo>

          <S.ExpInfo>
            <img src={Clock} alt="활동 날짜" width={20} height={20} />
            {exp.activityDate}
          </S.ExpInfo>
        </S.Locates>

        {/* ===== 오른쪽 ===== */}
        <S.Locate>
          <S.ExpInfo>
            <img src={Tag} alt="태그" width={20} height={20} />
            {exp.tags.join(", ")}
          </S.ExpInfo>

          <S.ExpInfo>
            <img src={Submitimg} alt="리뷰 여부" width={22} height={22} />
            {exp.isReviewed ? "제출 완료" : "미제출"}
          </S.ExpInfo>
        </S.Locate>
      </S.Container>
    </S.Wrraper>
  );
}