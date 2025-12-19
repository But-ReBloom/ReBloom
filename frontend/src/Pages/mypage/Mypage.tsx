import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Achieve from "../../assets/images/Archive.svg";
import React_svg from "../../assets/images/react.svg";
import { ImageOfTier } from "../../components/determine_tier/determine_tier";
import Rebloom from "../../assets/images/ReBloom.png";
import { useEffect, useState } from "react";
import { authApi } from "../../api/auth";
import { achievementApi } from "../../api/achievement";
import { hobbyApi } from "../../api/hobby";
import type { FindUserInfoResponse } from "../../types/auth";
import type { GetUserAchievementResponse } from "../../types/achievement";
import Tree from "../../assets/images/Tree.svg";
import LoadingPage from "../loadingpage/loading";

/* ===============================
   활동 상세 타입
================================ */
interface ActivityDetail {
  activityId: number;
  activityName: string;
  activityStart: string;
  activityRecent: string;
  linkedHobbyId: number;
  linkedHobbyName: string;
}

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

  return (
    <S.LeftSection>
      <S.UserInfoSection>
        <S.ProfileInfo>
          <S.UserImage src={React_svg} style={{ padding: 12 }} />
          <div style={{ display: "flex", flexDirection: "column", gap: 12 }}>
            <S.UserName>{userInfo?.userName || "Guest"}</S.UserName>
            <div style={{ display: "flex", alignItems: "center", gap: 8 }}>
              <S.UserTier>{tier}</S.UserTier>
              {tierImage && <img src={tierImage} width={32} />}
            </div>
          </div>
        </S.ProfileInfo>

        <S.PointArchive>
          <S.PnA>
            <S.addedimage src={Point} />
            {userInfo?.userTierPoint ?? 0}P
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Rebloom} />
            {userInfo?.userPoint ?? 0}P
          </S.PnA>
          <S.PnA>
            <S.addedimage src={Achieve} />
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
type ViewMode = "box" | "tree";

function RightSection({
  achievements,
  activities,
}: {
  achievements: GetUserAchievementResponse[];
  activities: ActivityDetail[];
}) {
  const [viewMode, setViewMode] = useState<ViewMode>("box");
  const [selectedActivity, setSelectedActivity] =
    useState<ActivityDetail | null>(null);
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  const completed = achievements.filter((a) => a.userAchievementIsSuccess);

  const completedPoints = completed.reduce(
    (sum, a) => sum + a.userAchievementRewardPoint,
    0
  );
  void completedPoints;

  const completedTierPoints = completed.reduce(
    (sum, a) => sum + a.userAchievementTierPoint,
    0
  );
  void completedTierPoints;

  return (
    <S.RightSection>
      <div
        style={{
          display: "flex",
          gap: 20,
          position: "fixed",
          top: 120,
          left: 1030,
        }}
      >
        <S.ChoiceBtn
          $active={viewMode === "box"}
          onClick={() => setViewMode("box")}
        >
          업적 보기
        </S.ChoiceBtn>
        <S.ChoiceBtn
          $active={viewMode === "tree"}
          onClick={() => setViewMode("tree")}
        >
          나무 보기
        </S.ChoiceBtn>
      </div>

      {viewMode === "box" && (
        <>
          <S.DetailTitle>업적 보기</S.DetailTitle>
          <S.ArchiveList>
            {achievements.map((ach) => {
              const progress = Math.min(
                100,
                Math.max(0, Math.round(ach.userAchievementProgress))
              );

              return (
                <S.Box
                  key={ach.achievementId}
                  style={{
                    opacity: ach.userAchievementIsSuccess ? 1 : 0.5,
                  }}
                >
                  <div style={{ fontWeight: "bold" }}>
                    {ach.userAchievementTitle}
                  </div>

                  <div style={{ fontSize: "12px", marginTop: "4px" }}>
                    진행률: {progress}%
                  </div>

                  {/* Progress Bar */}
                  <div
                    style={{
                      width: "100%",
                      height: "12px",
                      backgroundColor: "#e0e0e0",
                      borderRadius: "4px",
                      margin: "4px 0",
                      overflow: "hidden",
                    }}
                  >
                    <div
                      style={{
                        width: `${progress}%`,
                        height: "100%",
                        backgroundColor: ach.userAchievementIsSuccess
                          ? "rgb(0, 198, 89)"
                          : "rgb(0, 68, 255)",
                        transition: "width 0.3s ease",
                      }}
                    />
                  </div>

                  <div style={{ fontSize: "12px" }}>
                    보상: {ach.userAchievementRewardPoint}P / 티어:{" "}
                    {ach.userAchievementTierPoint}P
                  </div>
                </S.Box>
              );
            })}

            {achievements.length === 0 && (
              <div style={{ color: "#888" }}>업적이 없습니다.</div>
            )}
          </S.ArchiveList>
        </>
      )}

      {viewMode === "tree" && (
        <>
          <S.DetailTitle>나무 보기</S.DetailTitle>
          <S.TreeWrapper>
            <S.TreeImage src={Tree} style={{ width: "700px" }} />

            {activities.length === 0 ? (
              <div
                style={{
                  position: "absolute",
                  top: "50%",
                  left: "50%",
                  transform: "translate(-50%, -50%)",
                  color: "#888",
                }}
              >
                아직 추가된 활동이 없습니다.
              </div>
            ) : (
              activities.map((act, idx) => (
                <S.TreeActivity
                  key={act.activityId}
                  onClick={() => {
                    setSelectedActivity(act);
                    setIsPopupOpen(true);
                  }}
                  style={{
                    top: `${60 + idx * 70}px`,
                    left: idx % 2 === 0 ? "30%" : "65%",
                    cursor: "pointer",
                  }}
                >
                  {act.activityName}
                </S.TreeActivity>
              ))
            )}
          </S.TreeWrapper>
        </>
      )}

      {isPopupOpen && selectedActivity && (
        <S.PopupOverlay onClick={() => setIsPopupOpen(false)}>
          <S.PopupBox onClick={(e) => e.stopPropagation()}>
            <S.PopupTitle>{selectedActivity.activityName}</S.PopupTitle>
            <S.PopupContent>
              <p>취미: {selectedActivity.linkedHobbyName}</p>
              <p>시작일: {selectedActivity.activityStart}</p>
              <p>최근 활동일: {selectedActivity.activityRecent}</p>
            </S.PopupContent>
            <S.PopupClose onClick={() => setIsPopupOpen(false)}>
              닫기
            </S.PopupClose>
          </S.PopupBox>
        </S.PopupOverlay>
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
  const [activities, setActivities] = useState<ActivityDetail[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userRes = await authApi.findCurrentUser();
        if (userRes.success) {
          setUserInfo(userRes.data);

          const achRes =
            await achievementApi.getUserAchievementsByUserEmail();
          if (achRes.success) setAchievements(achRes.data);

          const actRes = await hobbyApi.findAllActivities();
          if (actRes.success) {
            setActivities(
              actRes.data.map((item) => ({
                activityId: item.activityId,
                activityName: item.activityName,
                activityStart: item.activityStart,
                activityRecent: item.activityRecent,
                linkedHobbyId: item.linkedHobbyId,
                linkedHobbyName: item.linkedHobbyName,
              }))
            );
          }
        }
      } catch (error) {
        console.error("Failed to fetch mypage data", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <LoadingPage />;

  return (
    <>
      <Header />
      <S.Background>
        <S.Wrapper>
          <S.Container>
            <LeftSection userInfo={userInfo} achievements={achievements} />
            <RightSection achievements={achievements} activities={activities} />
          </S.Container>
        </S.Wrapper>
      </S.Background>
    </>
  );
}
