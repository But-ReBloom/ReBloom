import styled from "styled-components";

/* ===============================
   Layout
================================ */
export const Background = styled.div`
  padding-top: 100px;
  width: 100%;
  min-height: 100vh;
  background: linear-gradient(103deg, #bbe4fc 15.07%, #ffffff 108.45%);
`;

export const Wrapper = styled.div`
  width: 100%;
  min-height: 650px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Container = styled.div`
  display: flex;
  width: 1200px;
  height: 660px;
  background-color: white;
  border-radius: 20px;
  box-shadow: 0 0 16px rgba(0, 0, 0, 0.25);
`;

/* ===============================
   Left Section
================================ */
export const LeftSection = styled.div`
  width: 360px;
  padding: 60px 40px;
  overflow-y: auto;

  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background: rgba(0, 0, 0, 0.2);
    border-radius: 4px;
  }
`;

export const UserInfoSection = styled.div`
  display: flex;
  flex-direction: column;
`;

export const ProfileInfo = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 16px;
`;

export const UserImage = styled.img`
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin-right: 16px;
  border: 2px solid rgba(0, 0, 0, 0.25);
`;

export const UserName = styled.h2`
  font-size: 24px;
  margin: 0;
`;

export const UserTier = styled.div`
  font-size: 14px;
  color: rgba(0, 0, 0, 0.55);
`;

export const PointArchive = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 20px 0;
`;

export const addedimage = styled.img`
  width: 20px;
  height: 20px;
  margin-right: 6px;
`;

export const PnA = styled.div`
  display: flex;
  align-items: center;
  font-size: 14px;
`;

export const ArchiveMent = styled.div`
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  color: #fff;
  padding: 10px 12px;
  border-radius: 8px;
  font-weight: 600;
  margin-bottom: 8px;
`;

export const ArchiveList = styled.div`
  max-height: 360px;
  overflow-y: auto;
`;

export const Box = styled.div`
  padding: 10px 6px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.15);
  cursor: pointer;

  &:hover {
    background-color: rgba(65, 166, 255, 0.1);
  }
`;

/* ===============================
   Right Section
================================ */
export const RightSection = styled.div`
  flex: 1;
  padding: 80px 60px;
  border-left: 1px solid rgba(0, 0, 0, 0.15);
  overflow-y: auto;
`;

/* ===============================
   Box View
================================ */
export const DetailTitle = styled.h2`
  font-size: 32px;
  margin-bottom: 12px;
`;

export const PointSummary = styled.div`
  font-size: 16px;
  font-weight: 500;
  margin-bottom: 24px;
  padding: 12px 16px;
  border-radius: 10px;
  background: #f3f6fa;
`;

export const ProgressTitle = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const ProgressBar = styled.div`
  width: 350px;
  height: 18px;
  background: #e5e7eb;
  border-radius: 999px;
  overflow: hidden;
`;

export const ProgressFill = styled.div<{ $progress: number }>`
  width: ${({ $progress }) => $progress}%;
  height: 100%;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  transition: width 0.3s ease;
`;

/* ===============================
   Tree View
================================ */
export const TreeWrapper = styled.div`
  position: relative;
  margin-top: 40px;
  width: 100%;
  height: 420px;
`;

export const TreeImage = styled.img`
  width: 280px;
  display: block;
  margin: 0 auto;
`;

export const TreeActivity = styled.div`
  position: absolute;
  padding: 6px 12px;
  background: rgba(65, 166, 255, 0.9);
  color: #fff;
  border-radius: 16px;
  font-size: 14px;
  white-space: nowrap;
`;

/* ===============================
   Buttons
================================ */
export const ChoiceBtn = styled.button`
  width: 140px;
  height: 34px;
  border-radius: 20px;
  background: rgb(0, 106, 255);
  color: #fff;
  border: none;
  font-size: 14px;
  cursor: pointer;
  transition: 0.25s;

  &:hover {
    background: rgb(115, 164, 233);
  }
`;
