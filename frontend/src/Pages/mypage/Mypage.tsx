import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Archive from "../../assets/images/Archive.svg";
import React_svg from "../../assets/images/React.svg";
import { ImageOfTier } from "../../components/determine_tier/determine_tier.ts";
import Rebloom from "../../assets/images/Rebloom.png";

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
   유틸 함수
================================ */
const getProgressPercent = (ach: Achievement) =>
  Math.min(Math.round((ach.user_progress / ach.system_progress) * 100), 100);

const isCompleted = (ach: Achievement) =>
  ach.user_progress >= ach.system_progress;

/* ===============================
   데이터
================================ */
const data = {
  name: "오용준",
  tier: "silver",
  achievements: [
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
  ],
};

// 진행률 100% 업적 총합
const completedAchievements = data.achievements.filter(
  (ach) => getProgressPercent(ach) === 100
);

const completedPoints = completedAchievements.reduce(
  (sum, ach) => sum + ach.ach_reward_point,
  0
);

const completedTierPoints = completedAchievements.reduce(
  (sum, ach) => sum + ach.ach_reward_tier_point,
  0
);

const tierImage = ImageOfTier(data.tier);

/* ===============================
   Left Section (완료 업적)
================================ */
function LeftSection() {
  const completed = data.achievements.filter(isCompleted);

  return (
    <S.LeftSection>
      <S.UserInfoSection>
        <S.ProfileInfo>
          <S.UserImage src={React_svg} />
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            <S.UserName>{data.name}</S.UserName>
            <div
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
              }}
            >
              <S.UserTier>{data.tier}</S.UserTier>
              <img src={tierImage} alt="Tier Image" width={32} />
            </div>
          </div>
        </S.ProfileInfo>

        <S.PointArchive>
          <S.PnA>
            <S.addedimage src={Point} />
            {completedTierPoints}P
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Rebloom}></S.addedimage>
            {completedPoints}P
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Archive} />
            {completed.length}개
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
   Right Section (전체 업적 + 100% 진행률 총합)
================================ */
function RightSection() {
  return (
    <S.RightSection style={{ overflowY: "auto" }}>
      <S.DetailTitle>전체 업적</S.DetailTitle>

      {/* 100% 업적 총합 표시 */}
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          width: "300px",
          marginBottom: 16,
          fontWeight: "bold",
          fontSize: 16,
          borderRight: "2px solid #777777",
          borderLeft: "2px solid #777777"
        }}
      >
        <p style={{margin: "0", color: "#777777"}}>완료 업적 포인트: {completedPoints} | 티어 포인트: {completedTierPoints}</p>
      </div>

      {data.achievements.map((ach) => {
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
