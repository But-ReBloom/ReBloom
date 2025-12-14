import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Archive from "../../assets/images/Archive.svg";
import React_svg from "../../assets/images/React.svg";
import { ImageOfTier } from "../../components/determine_tier/determine_tier.ts";
import Rebloom from "../../assets/images/Rebloom.png";
import { useEffect, useState } from "react";
import { authApi } from "../../api/auth";
import { achievementApi } from "../../api/achievement";
import type { FindUserInfoResponse } from "../../types/auth";
import type { GetUserAchievementResponse } from "../../types/achievement";

/* ===============================
   유틸 함수
================================ */
const getTierName = (points: number): string => {
  if (points < 1000) return "bronze";
  if (points < 2000) return "silver";
  if (points < 3000) return "gold";
  if (points < 4000) return "diamond";
  if (points < 5000) return "master";
  return "challenger";
};

/* ===============================
   Left Section (완료 업적)
================================ */
interface LeftSectionProps {
  userInfo: FindUserInfoResponse | null;
  achievements: GetUserAchievementResponse[];
}

function LeftSection({ userInfo, achievements }: LeftSectionProps) {
  const completed = achievements.filter((ach) => ach.userAchievementIsSuccess);
  
  const tier = userInfo ? getTierName(userInfo.userTierPoint) : "bronze";
  const tierImage = ImageOfTier(tier);

  return (
    <S.LeftSection>
      <S.UserInfoSection>
        <S.ProfileInfo>
          <S.UserImage src={React_svg} />
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            <S.UserName>{userInfo?.userName || "Guest"}</S.UserName>
            <div
              style={{
                display: "flex",
                alignItems: "center",
                justifyContent: "space-between",
              }}
            >
              <S.UserTier>{tier}</S.UserTier>
              <img src={tierImage} alt="Tier Image" width={32} />
            </div>
          </div>
        </S.ProfileInfo>

        <S.PointArchive>
          <S.PnA>
            <S.addedimage src={Point} />
            {userInfo?.userTierPoint || 0}P
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Rebloom}></S.addedimage>
            {userInfo?.userPoint || 0}P
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Archive} />
            {completed.length}개
          </S.PnA>
        </S.PointArchive>

        <S.ArchiveMent>완료한 업적</S.ArchiveMent>

        <S.ArchiveList style={{ overflowY: "auto" }}>
          {completed.map((ach) => (
            <S.Box key={ach.achievementId}>{ach.userAchievementTitle}</S.Box>
          ))}
        </S.ArchiveList>
      </S.UserInfoSection>
    </S.LeftSection>
  );
}

/* ===============================
   Right Section (전체 업적 + 100% 진행률 총합)
================================ */
interface RightSectionProps {
  achievements: GetUserAchievementResponse[];
}

function RightSection({ achievements }: RightSectionProps) {
  const completed = achievements.filter((ach) => ach.userAchievementIsSuccess);
  const completedPoints = completed.reduce((sum, ach) => sum + ach.userAchievementRewardPoint, 0);
  const completedTierPoints = completed.reduce((sum, ach) => sum + ach.userAchievementTierPoint, 0);

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

      {achievements.map((ach) => {
        const percent = ach.userAchievementIsSuccess ? 100 : 0;

        return (
          <div
            key={ach.achievementId}
            style={{
              padding: "16px 0",
              borderBottom: "1px solid rgba(0,0,0,0.1)",
            }}
          >
            {/* 타이틀 ↔ 진행도 */}
            <S.ProgressTitle>
              <strong>{ach.userAchievementTitle}</strong>
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
              {ach.userAchievementDescription} (현재: {ach.userAchievementProgress})
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
  const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);
  const [achievements, setAchievements] = useState<GetUserAchievementResponse[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userRes = await authApi.findCurrentUser();
        console.log("User Response:", userRes);
        if (userRes.success) {
          setUserInfo(userRes.data);
        } else {
          console.error("Failed to fetch user info:", userRes);
        }

        const achRes = await achievementApi.getUserAchievementsByUserEmail();
        console.log("Achievement Response:", achRes);
        if (achRes.success) {
          setAchievements(achRes.data);
        } else {
          console.error("Failed to fetch achievements:", achRes);
        }
      } catch (error) {
        console.error("Failed to fetch mypage data", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <Header />
      <S.Background>
        <S.Wrapper>
          <S.Container>
            <LeftSection userInfo={userInfo} achievements={achievements} />
            <RightSection achievements={achievements} />
          </S.Container>
        </S.Wrapper>
      </S.Background>
    </>
  );
}
