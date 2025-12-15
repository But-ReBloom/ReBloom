import * as S from "./style.ts";
import Header from "../../components/normal_header/nh.tsx";
import { useLocation, useNavigate } from "react-router-dom";
import Arrow from "../../assets/images/blackarrow.svg";

/* ===============================
   타입
================================ */
type Hobby = {
  hobbyName: string;
  condition: (score: any) => boolean;
};

/* ===============================
   더미 취미 데이터
================================ */
const dummyHobbies: Hobby[] = [
  { hobbyName: "봉사 활동", condition: (s) => s.social >= 1 },
  { hobbyName: "스터디 모임", condition: (s) => s.learning >= 1 },
  { hobbyName: "플래너 작성", condition: (s) => s.planning >= 1 },
  { hobbyName: "명상 / 요가", condition: (s) => s.focus >= 1 },
  { hobbyName: "그림 그리기", condition: (s) => s.creativity >= 1 },
  { hobbyName: "팀 스포츠", condition: (s) => s.social >= 3 && s.focus >= 1 },
  {
    hobbyName: "코딩 프로젝트",
    condition: (s) => s.learning >= 3 && s.planning >= 1,
  },
];

export default function TestResult() {
  const navigate = useNavigate();
  const location = useLocation();
  const { finalAverage } = location.state || {};

  /* ===============================
     추천 결과 (최대 3개)
  ================================ */
  const generatedRecommendations = finalAverage
    ? dummyHobbies.filter((hobby) => hobby.condition(finalAverage)).slice(0, 3)
    : [];

  /* ===============================
     나무에 추가
  ================================ */
  const handleAddToTree = (hobbyName: string) => {
    const stored = localStorage.getItem("treeActivities");
    const parsed: string[] = stored ? JSON.parse(stored) : [];

    if (!parsed.includes(hobbyName)) {
      parsed.push(hobbyName);
      localStorage.setItem("treeActivities", JSON.stringify(parsed));
      alert("나무에 추가되었습니다.");
    } else {
      alert("이미 나무에 추가된 활동입니다.");
    }
  };

  /* ===============================
     이동 로직 (핵심)
  ================================ */
  const handleMoveNext = () => {
    const stored = localStorage.getItem("treeActivities");
    const parsed: string[] = stored ? JSON.parse(stored) : [];

    // 추천된 활동 중 하나라도 추가 안 된 것이 있으면
    const hasUnadded = generatedRecommendations.some(
      (rec) => !parsed.includes(rec.hobbyName)
    );

    if (hasUnadded) {
      navigate("/"); // 메인화면
    } else {
      navigate("/mypage"); // 마이페이지
    }
  };

  return (
    <S.Background>
      <Header />
      <S.Wrrapper>
        <S.MainColumn>
          <S.Title>알고리즘 테스트 결과</S.Title>

          {/* 점수 영역 */}
          <S.ScoreRow>
            <S.ResultBox>
              <S.Subtitle>사회성</S.Subtitle>
              <S.Scoretitle>{finalAverage?.social?.toFixed(1)}</S.Scoretitle>
            </S.ResultBox>
            <S.ResultBox>
              <S.Subtitle>학습력</S.Subtitle>
              <S.Scoretitle>{finalAverage?.learning?.toFixed(1)}</S.Scoretitle>
            </S.ResultBox>
            <S.ResultBox>
              <S.Subtitle>계획력</S.Subtitle>
              <S.Scoretitle>{finalAverage?.planning?.toFixed(1)}</S.Scoretitle>
            </S.ResultBox>
            <S.ResultBox>
              <S.Subtitle>집중력</S.Subtitle>
              <S.Scoretitle>{finalAverage?.focus?.toFixed(1)}</S.Scoretitle>
            </S.ResultBox>
            <S.ResultBox>
              <S.Subtitle>창의성</S.Subtitle>
              <S.Scoretitle>
                {finalAverage?.creativity?.toFixed(1)}
              </S.Scoretitle>
            </S.ResultBox>
          </S.ScoreRow>

          {/* 추천 영역 */}
          <S.RecommendSection>
            <S.RecommendRow>
              {generatedRecommendations.map((rec, index) => (
                <S.RecommaendBox key={index}>
                  <div style={{ fontSize: 22, fontWeight: 500 }}>
                    {rec.hobbyName}
                  </div>
                  <S.ChoiceBtn onClick={() => handleAddToTree(rec.hobbyName)}>
                    나무에 추가
                  </S.ChoiceBtn>
                </S.RecommaendBox>
              ))}

              {/* 🔽 이동 버튼 */}
              <S.ArrowImage onClick={handleMoveNext}>
                <img src={Arrow} alt="next" />
              </S.ArrowImage>
            </S.RecommendRow>
          </S.RecommendSection>
        </S.MainColumn>
      </S.Wrrapper>
    </S.Background>
  );
}
