import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Archive from "../../assets/images/Archive.svg";

/* ===============================
   타입
================================ */
type Achievement = {
  title: string;
  description: string;
  progress: number;
};

/* ===============================
   데이터 (전부 오른쪽에 표시)
================================ */
const achievements: Achievement[] = [
  { title: "첫 설문조사!", description: "설문조사 1회 달성", progress: 100 },
  { title: "설문 조사의 신!", description: "설문조사 10회 달성", progress: 30 },
  {
    title: "시작이 반이다.",
    description: "1회 접속 후 회원가입",
    progress: 100,
  },
  { title: "계획적으로!", description: "2일 연속 접속", progress: 50 },
  { title: "5연속 접속!", description: "5일 연속 접속", progress: 20 },
  { title: "연속 접속의 신", description: "365일 연속 접속", progress: 3 },
  { title: "첫 댓글", description: "1회 댓글 달기", progress: 100 },
  { title: "좋은 댓글", description: "5회 댓글 달기", progress: 60 },
  { title: "첫 리뷰", description: "1회 활동 리뷰 달기", progress: 100 },
  { title: "리뷰의 달인", description: "5회 활동 리뷰 달기", progress: 40 },
  {
    title: "커뮤니티 시작!",
    description: "1회 커뮤니티 글 쓰기",
    progress: 10,
  },
];

/* ===============================
   Left Section (완료 업적만)
================================ */
function LeftSection() {
  const completed = achievements.filter((a) => a.progress === 100);

  return (
    <S.LeftSection>
      <S.UserInfoSection>
        <S.ProfileInfo>
          <S.UserImage />
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            <S.UserName>오용준</S.UserName>
            <S.UserTier>silver</S.UserTier>
          </div>
        </S.ProfileInfo>

        <S.PointArchive>
          <S.PnA>
            <S.addedimage src={Point} />
            1200
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Archive} />
            {completed.length}
          </S.PnA>
        </S.PointArchive>

        <S.ArchiveMent>완료한 업적</S.ArchiveMent>

        {completed.map((ach) => (
          <S.Box key={ach.title}>{ach.title}</S.Box>
        ))}
      </S.UserInfoSection>
    </S.LeftSection>
  );
}

/* ===============================
   Right Section (모든 업적)
================================ */
function RightSection() {
  return (
    <S.RightSection>
      <S.DetailTitle>전체 업적</S.DetailTitle>

      {achievements.map((ach) => (
        <div
          key={ach.title}
          style={{
            padding: "14px 0",
            borderBottom: "1px solid rgba(0,0,0,0.1)",
          }}
        >
          {/* 타이틀 ↔ 진행률 */}
          <S.ProgressTitle>
            <span style={{ fontSize: "16px", fontWeight: "600" }}>
              {ach.title}
            </span>
            <div
              style={{
                display: "flex",
                flexDirection: "row",
                alignContent: "center",
              }}
            >
              <span style={{ color: "#888888" }}>{ach.description}</span>
              <S.ProgressBar>
                <S.ProgressFill progress={ach.progress} />
              </S.ProgressBar>
            </div>
          </S.ProgressTitle>
        </div>
      ))}
    </S.RightSection>
  );
}

/* ===============================
   Main
================================ */
export default function Mypage() {
  return (
    <>
      <Header />

      <S.Background>
        <S.Wrapper>
          <S.Container>
            <LeftSection />
            <RightSection />
          </S.Container>
        </S.Wrapper>
      </S.Background>
    </>
  );
}
