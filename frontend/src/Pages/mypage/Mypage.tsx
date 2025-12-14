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
  system_progress: number;
  user_progress: number;
  ach_reward_point: number;
  ach_reward_tier_point: number;
};

/* ===============================
   데이터
================================ */
const user = {
  name: "오용준",
  tier: "silver",
  points: 1200,
};

const achievements: Achievement[] = [
  {
    title: "첫 설문조사!",
    description: "설문조사 1회 달성",
    system_progress: 1,
    user_progress: 1,
    ach_reward_point: 100,
    ach_reward_tier_point: 100,
  },
  {
    title: "설문 조사의 신!",
    description: "설문조사 10회 달성",
    system_progress: 10,
    user_progress: 6,
    ach_reward_point: 800,
    ach_reward_tier_point: 800,
  },
  {
    title: "시작이 반이다.",
    description: "1회 접속 후 회원가입",
    system_progress: 1,
    user_progress: 1,
    ach_reward_point: 100,
    ach_reward_tier_point: 100,
  },
  {
    title: "계획적으로!",
    description: "2일 연속 접속",
    system_progress: 2,
    user_progress: 1,
    ach_reward_point: 200,
    ach_reward_tier_point: 200,
  },
  {
    title: "5연속 접속!",
    description: "5일 연속 접속",
    system_progress: 5,
    user_progress: 2,
    ach_reward_point: 500,
    ach_reward_tier_point: 500,
  },
  {
    title: "연속 접속의 신",
    description: "365일 연속 접속",
    system_progress: 365,
    user_progress: 3,
    ach_reward_point: 9000,
    ach_reward_tier_point: 9000,
  },

  /* ================= 추가된 부분 ================= */

  {
    title: "첫 댓글",
    description: "1회 댓글 달기",
    system_progress: 1,
    user_progress: 1,
    ach_reward_point: 100,
    ach_reward_tier_point: 100,
  },
  {
    title: "좋은 댓글",
    description: "5회 댓글 달기",
    system_progress: 5,
    user_progress: 3,
    ach_reward_point: 500,
    ach_reward_tier_point: 500,
  },
  {
    title: "첫 리뷰",
    description: "1회 활동 리뷰 달기",
    system_progress: 1,
    user_progress: 1,
    ach_reward_point: 500,
    ach_reward_tier_point: 500,
  },
  {
    title: "리뷰의 달인",
    description: "5회 활동 리뷰 달기",
    system_progress: 5,
    user_progress: 2,
    ach_reward_point: 3500,
    ach_reward_tier_point: 2500,
  },
  {
    title: "커뮤니티 시작!",
    description: "1회 커뮤니티 글 쓰기",
    system_progress: 1,
    user_progress: 0,
    ach_reward_point: 100,
    ach_reward_tier_point: 100,
  },
];

/* ===============================
   유틸
================================ */
const getProgressPercent = (ach: Achievement) =>
  Math.min(Math.round((ach.user_progress / ach.system_progress) * 100), 100);

const isCompleted = (ach: Achievement) =>
  ach.user_progress >= ach.system_progress;

/* ===============================
   Left Section (완료 업적)
================================ */
function LeftSection() {
  const completed = achievements.filter(isCompleted);

  return (
    <S.LeftSection>
      <S.UserInfoSection>
        <S.ProfileInfo>
          <S.UserImage />
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            <S.UserName>{user.name}</S.UserName>
            <S.UserTier>{user.tier}</S.UserTier>
          </div>
        </S.ProfileInfo>

        <S.PointArchive>
          <S.PnA>
            <S.addedimage src={Point} />
            {user.points}
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Archive} />
            {completed.length}
          </S.PnA>
        </S.PointArchive>

        <S.ArchiveMent>완료한 업적</S.ArchiveMent>

        <S.ArchiveList style={{ overflowY: "auto" }}>
          {completed.map((ach) => (
            <S.Box key={ach.title}>{ach.title}</S.Box>
          ))}
        </S.ArchiveList>
      </S.UserInfoSection>
    </S.LeftSection>
  );
}

/* ===============================
   Right Section (전체 업적)
================================ */
function RightSection() {
  return (
    <S.RightSection style={{ overflowY: "auto" }}>
      <S.DetailTitle>전체 업적</S.DetailTitle>

      {achievements.map((ach) => {
        const percent = getProgressPercent(ach);

        return (
          <div
            key={ach.title}
            style={{
              padding: "16px 0",
              borderBottom: "1px solid rgba(0,0,0,0.1)",
            }}
          >
            {/* 타이틀 ↔ 진행도 */}
            <S.ProgressTitle>
              <strong>{ach.title}</strong>
              <div
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "flex-start",
                  gap: 20,
                }}
              >
                <S.ProgressBar>
                  <S.ProgressFill progress={percent} />
                </S.ProgressBar>
                <span>{percent}%</span>
              </div>
            </S.ProgressTitle>

            <p
              style={{
                fontSize: "14px",
                color: "#666",
                marginTop: 6,
              }}
            >
              {ach.description} ({ach.user_progress}/{ach.system_progress})
            </p>
          </div>
        );
      })}
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
