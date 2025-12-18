import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Achieve from "../../assets/images/Archive.svg";
import React_svg from "../../assets/images/react.svg";
import { ImageOfTier } from "../../components/determine_tier/determine_tier";
import Rebloom from "../../assets/images/ReBloom.png";
import { useState } from "react";
import type { FindUserInfoResponse } from "../../types/auth";
import type { GetUserAchievementResponse } from "../../types/achievement";
import Tree from "../../assets/images/Tree.svg";

/* ===============================
   활동 상세 더미
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
   업적 더미
================================ */
const achievementDummyList: GetUserAchievementResponse[] = [
  {
    achievementId: 1,
    userAchievementTitle: "첫 활동 시작",
    userAchievementDescription: "처음으로 활동을 시작했어요",
    userAchievementRewardPoint: 100,
    userAchievementTierPoint: 50,
    userAchievementIsSuccess: true,
  },
  {
    achievementId: 2,
    userAchievementTitle: "연속 3일 활동",
    userAchievementDescription: "3일 연속 활동을 기록했어요",
    userAchievementRewardPoint: 200,
    userAchievementTierPoint: 100,
    userAchievementIsSuccess: true,
  },
  {
    achievementId: 3,
    userAchievementTitle: "연속 7일 활동",
    userAchievementDescription: "7일 연속 활동 달성",
    userAchievementRewardPoint: 300,
    userAchievementTierPoint: 150,
    userAchievementIsSuccess: false,
  },
  {
    achievementId: 4,
    userAchievementTitle: "환경 보호 실천가",
    userAchievementDescription: "환경 관련 활동 5회 달성",
    userAchievementRewardPoint: 250,
    userAchievementTierPoint: 120,
    userAchievementIsSuccess: true,
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
function LeftSection({
  userInfo,
  achievements,
}: {
  userInfo: FindUserInfoResponse;
  achievements: GetUserAchievementResponse[];
}) {
  const completed = achievements.filter((a) => a.userAchievementIsSuccess);
  const tier = getTierName(userInfo.userTierPoint);
  const tierImage = ImageOfTier(tier);

  return (
    <S.LeftSection>
      <S.ProfileInfo>
        <S.UserImage src={React_svg} style={{ padding: 12 }} />
        <div>
          <S.UserName>{userInfo.userName}</S.UserName>
          <div style={{ display: "flex", gap: 6, alignItems: "center" }}>
            {tierImage && <img src={tierImage} width={28} />}
            <S.UserTier>{tier}</S.UserTier>
          </div>
        </div>
      </S.ProfileInfo>

      <S.PointArchive>
        <S.PnA>
          <S.addedimage src={Point} />
          {userInfo.userTierPoint}P
        </S.PnA>
        <S.PnA>
          <S.addedimage src={Rebloom} />
          {userInfo.userPoint}P
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
  const [isChangePopupOpen, setIsChangePopupOpen] = useState(false);

  return (
    <S.RightSection>
      <div style={{ display: "flex", justifyContent: "flex-end", gap: 20 }}>
        <S.ChoiceBtn onClick={() => setViewMode("box")}>업적 보기</S.ChoiceBtn>
        <S.ChoiceBtn onClick={() => setViewMode("tree")}>나무 보기</S.ChoiceBtn>
      </div>

      {viewMode === "tree" && (
        <>
          <S.DetailTitle>나무 보기</S.DetailTitle>
          <S.TreeWrapper>
            <S.TreeImage src={Tree} />
            {activityDummyList.map((act, idx) => (
              <S.TreeActivity
                key={act.activityId}
                style={{
                  top: `${60 + idx * 70}px`,
                  left: idx % 2 === 0 ? "30%" : "65%",
                }}
                onClick={() => {
                  setSelectedActivity(act);
                  setIsPopupOpen(true);
                }}
              >
                {act.activityName}
              </S.TreeActivity>
            ))}
          </S.TreeWrapper>
        </>
      )}

      {/* 활동 상세 팝업 */}
      {isPopupOpen && selectedActivity && (
        <S.PopupOverlay onClick={() => setIsPopupOpen(false)}>
          <S.PopupBox onClick={(e) => e.stopPropagation()}>
            <S.PopupTitle>{selectedActivity.activityName}</S.PopupTitle>
            <p>취미: {selectedActivity.linkedHobbyName}</p>
            <p>시작일: {selectedActivity.activityStart}</p>
            <p>최근 활동일: {selectedActivity.activityRecent}</p>

            <S.PopupClose
              onClick={() => {
                setIsPopupOpen(false);
                setIsChangePopupOpen(true);
              }}
            >
              활동 변경
            </S.PopupClose>
          </S.PopupBox>
        </S.PopupOverlay>
      )}

      {/* 활동 변경 팝업 */}
      {isChangePopupOpen && (
        <S.PopupOverlay onClick={() => setIsChangePopupOpen(false)}>
          <S.PopupBox onClick={(e) => e.stopPropagation()}>
            <S.PopupTitle>활동 변경</S.PopupTitle>

            {activityDummyList.map((act) => (
              <button
                key={act.activityId}
                style={{
                  width: "100%",
                  padding: 10,
                  marginBottom: 8,
                  borderRadius: 8,
                  border: "1px solid #ddd",
                  cursor: "pointer",
                }}
                onClick={() => {
                  if (
                    selectedActivity &&
                    selectedActivity.activityId === act.activityId
                  )
                    return;

                  setSelectedActivity(act);
                  setIsChangePopupOpen(false);
                  setIsPopupOpen(true);
                }}
              >
                {act.activityName}
              </button>
            ))}

            <S.PopupClose onClick={() => setIsChangePopupOpen(false)}>
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
  const userDummy: FindUserInfoResponse = {
    userName: "ReBloom User",
    userPoint: 1200,
    userTierPoint: 1800,
  };

  return (
    <>
      <Header />
      <S.Background>
        <S.Wrapper>
          <S.Container>
            <LeftSection
              userInfo={userDummy}
              achievements={achievementDummyList}
            />
            <RightSection achievements={achievementDummyList} />
          </S.Container>
        </S.Wrapper>
      </S.Background>
    </>
  );
}
