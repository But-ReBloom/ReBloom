import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Archive from "../../assets/images/Archive.svg";
import React_svg from "../../assets/images/react.svg";
import { ImageOfTier } from "../../components/determine_tier/determine_tier.ts";
import Rebloom from "../../assets/images/ReBloom.png";
import { useEffect, useState } from "react";
import { authApi } from "../../api/auth";
import { achievementApi } from "../../api/achievement";
import type { FindUserInfoResponse } from "../../types/auth";
import type { GetUserAchievementResponse } from "../../types/achievement";
import Tree from "../../assets/images/Tree.svg";

/* ===============================
   유틸
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
   Left Section
================================ */
interface LeftSectionProps {
  userInfo: FindUserInfoResponse | null;
  achievements: GetUserAchievementResponse[];
}

function LeftSection({ userInfo, achievements }: LeftSectionProps) {
  const completed = achievements.filter((a) => a.userAchievementIsSuccess);
  const tier = userInfo ? getTierName(userInfo.userTierPoint) : "bronze";
  const tierImage = ImageOfTier(tier);

  // Debugging images
  console.log("Images:", { React_svg, Point, Rebloom, Archive, tierImage });

  return (
    <S.LeftSection>
      <S.UserInfoSection>
        <S.ProfileInfo>
          <S.UserImage src={React_svg || ""} />
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            <S.UserName>{userInfo?.userName || "Guest"}</S.UserName>
            <div
              style={{
                display: "flex",
                justifyContent: "space-between",
                alignItems: "center",
                gap: 8,
              }}
            >
              <S.UserTier>{tier}</S.UserTier>
              {tierImage && <img src={tierImage} alt="Tier Image" width={32} />}
            </div>
          </div>
        </S.ProfileInfo>

        <S.PointArchive>
          <S.PnA>
            {Point && <S.addedimage src={Point} />}
            {userInfo?.userTierPoint || 0}P
          </S.PnA>
          <S.PnA>
            {Rebloom && <S.addedimage src={Rebloom} />}
            {userInfo?.userPoint || 0}P
          </S.PnA>
          <S.PnA>
            {Archive && <S.addedimage src={Archive} />}
            {completed.length}개
          </S.PnA>
        </S.PointArchive>

        <S.ArchiveMent>완료한 업적</S.ArchiveMent>
        <S.ArchiveList>
          {completed.map((ach) => (
            <S.Box key={ach.achievementId}>{ach.userAchievementTitle}</S.Box>
          ))}
        </S.ArchiveList>
      </S.UserInfoSection>
    </S.LeftSection>
  );
}

/* ===============================
   Right Section
================================ */
interface RightSectionProps {
  achievements: GetUserAchievementResponse[];
}

type ViewMode = "box" | "tree";

function RightSection({ achievements }: RightSectionProps) {
  const [viewMode, setViewMode] = useState<ViewMode>("box");
  const [treeActivities, setTreeActivities] = useState<string[]>([]);

  /* ===============================
     업적 계산
  ================================ */
  const completed = achievements.filter((a) => a.userAchievementIsSuccess);

  const completedPoints = completed.reduce(
    (sum, a) => sum + a.userAchievementRewardPoint,
    0
  );

  const completedTierPoints = completed.reduce(
    (sum, a) => sum + a.userAchievementTierPoint,
    0
  );

  /* ===============================
     나무 데이터 로드
  ================================ */
  useEffect(() => {
    if (viewMode === "tree") {
      const stored = localStorage.getItem("treeActivities");
      setTreeActivities(stored ? JSON.parse(stored) : []);
    }
  }, [viewMode]);

  return (
    <S.RightSection>
      {/* 보기 전환 버튼 */}
      <div
        style={{
          display: "flex",
          gap: 20,
          position: "fixed",
          top: 120,
          left: 1030,
          zIndex: 10,
        }}
      >
        <S.ChoiceBtn onClick={() => setViewMode("box")}>업적 보기</S.ChoiceBtn>
        <S.ChoiceBtn onClick={() => setViewMode("tree")}>나무 보기</S.ChoiceBtn>
      </div>

      {/* ===============================
          박스 보기
      ================================ */}
      {viewMode === "box" && (
        <>
          <S.DetailTitle>전체 업적</S.DetailTitle>

          <S.PointSummary>
            완료 업적 포인트: {completedPoints} | 티어 포인트:{" "}
            {completedTierPoints}
          </S.PointSummary>

          {achievements.map((ach) => {
            const percent = ach.userAchievementIsSuccess ? 100 : 0;

            return (
              <>
                <S.ProgressTitle>
                  <div
                    key={ach.achievementId}
                    style={{
                      padding: "16px 0",
                      borderBottom: "1px solid rgba(0,0,0,0.1)",
                    }}
                  >
                    <S.ProgressBar>
                      <S.ProgressFill $progress={percent} />
                    </S.ProgressBar>
                    <span>{percent}%</span>
                  </div>
                </S.ProgressTitle>
                <p style={{ fontSize: 14, color: "#666" }}>
                  {ach.userAchievementDescription} (현재:{" "}
                  {ach.userAchievementProgress})
                </p>
              </>
            );
          })}
        </>
      )}

      {/* ===============================
          🌳 나무 보기
      ================================ */}
      {viewMode === "tree" && (
        <>
          <S.DetailTitle>나무 보기</S.DetailTitle>

          <S.TreeWrapper>
            <S.TreeImage src={Tree} style={{ width: "700px" }} />

            {treeActivities.map((act, idx) => (
              <S.TreeActivity
                key={idx}
                style={{
                  top: `${60 + (idx % 5) * 70}px`,
                  left: idx % 2 === 0 ? "30%" : "65%",
                }}
              >
                {act}
              </S.TreeActivity>
            ))}
          </S.TreeWrapper>

          {treeActivities.length === 0 && (
            <p style={{ marginTop: 16 }}>아직 추가된 활동이 없습니다.</p>
          )}
        </>
      )}
    </S.RightSection>
  );
}

/* ===============================
   Main
================================ */
export default function Mypage() {
  const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);
  const [achievements, setAchievements] = useState<
    GetUserAchievementResponse[]
  >([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userRes = await authApi.findCurrentUser();
        if (userRes.success) setUserInfo(userRes.data);

        const achRes = await achievementApi.getUserAchievementsByUserEmail();
        if (achRes.success) setAchievements(achRes.data);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  if (loading) return <div>Loading...</div>;

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
