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
import type { FindUserInfoResponse } from "../../types/auth";
import type { GetUserAchievementResponse } from "../../types/achievement";
import Tree from "../../assets/images/Tree.svg";

/* ===============================
   활동 상세 더미 (API 명세 동일)
================================ */
interface ActivityDetail {
  activityId: number;
  activityName: string;
  activityStart: string;
  activityRecent: string;
  linkedHobbyId: number;
  linkedHobbyName: string;
}

const activityDummyList: ActivityDetail[] = [
  {
    activityId: 1,
    activityName: "텀블러 사용 챌린지",
    activityStart: "2025-12-01",
    activityRecent: "2025-12-03",
    linkedHobbyId: 1,
    linkedHobbyName: "환경 보호 활동",
  },
  {
    activityId: 2,
    activityName: "주 3회 독서 기록",
    activityStart: "2025-11-20",
    activityRecent: "2025-12-02",
    linkedHobbyId: 2,
    linkedHobbyName: "자기계발",
  },
  {
    activityId: 3,
    activityName: "아침 스트레칭 루틴",
    activityStart: "2025-11-15",
    activityRecent: "2025-12-01",
    linkedHobbyId: 3,
    linkedHobbyName: "건강 관리",
  },
  {
    activityId: 4,
    activityName: "주말 사진 산책",
    activityStart: "2025-11-10",
    activityRecent: "2025-11-30",
    linkedHobbyId: 4,
    linkedHobbyName: "사진 촬영",
  },
];

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
            <S.Box key={ach.achievementId}>
              {ach.userAchievementTitle}
            </S.Box>
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
}: {
  achievements: GetUserAchievementResponse[];
}) {
  const [viewMode, setViewMode] = useState<ViewMode>("box");
  const [selectedActivity, setSelectedActivity] =
    useState<ActivityDetail | null>(null);
  const [isPopupOpen, setIsPopupOpen] = useState(false);

  return (
    <S.RightSection>
      <div style={{ display: "flex", gap: 20, position: "fixed", top: 120, left: 1030 }}>
        <S.ChoiceBtn onClick={() => setViewMode("box")}>업적 보기</S.ChoiceBtn>
        <S.ChoiceBtn onClick={() => setViewMode("tree")}>나무 보기</S.ChoiceBtn>
      </div>

      {viewMode === "box" && (
        <>
          <S.DetailTitle>업적 보기</S.DetailTitle>
          <S.ArchiveList>
            {achievements.map((ach) => (
              <S.Box key={ach.achievementId} style={{ opacity: ach.userAchievementIsSuccess ? 1 : 0.5 }}>
                <div style={{ fontWeight: 'bold' }}>{ach.userAchievementTitle}</div>
                <div style={{ fontSize: '12px', marginTop: '4px' }}>
                  진행률: {Math.round(ach.userAchievementProgress * 100)}%
                </div>
                <div style={{ fontSize: '12px' }}>
                  보상: {ach.userAchievementRewardPoint}P / 티어: {ach.userAchievementTierPoint}P
                </div>
              </S.Box>
            ))}
            {achievements.length === 0 && (
              <div style={{ color: '#888' }}>업적이 없습니다.</div>
            )}
          </S.ArchiveList>
        </>
      )}

      {viewMode === "tree" && (
        <>
          <S.DetailTitle>나무 보기</S.DetailTitle>
          <S.TreeWrapper>
            <S.TreeImage src={Tree} style={{ width: "700px" }} />

            {activityDummyList.map((act, idx) => (
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
            ))}
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
  const [achievements, setAchievements] = useState<GetUserAchievementResponse[]>(
    []
  );
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userRes = await authApi.findCurrentUser();
        if (userRes.success) {
          setUserInfo(userRes.data);
          // Fetch achievements using userEmail
          try {
            const achRes = await achievementApi.getUserAchievementsByUserEmail();
            if (achRes.success) setAchievements(achRes.data);
          } catch (error) {
            console.error("Failed to fetch achievements", error);
          }
        }
      } catch (error) {
        console.error("Failed to fetch user info", error);
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
