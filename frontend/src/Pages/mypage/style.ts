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
export const ChoiceBtn = styled.button<{ $active?: boolean }>`
  width: 140px;
  height: 34px;
  border-radius: 20px;
  background: ${({ $active }) => ($active ? '#3e55bf' : '#333')};
  color: ${({ $active }) => ($active ? '#fff' : '#fff')};
  border: ${({ $active }) => ($active ? '2px solid #3e55bf' : 'none')};
  font-size: 14px;
  cursor: pointer;
  transition: all 0.2s ease;

  &:hover {
    font-size: 16px;
    font-weight: bold;
    color: #3e55bf;
    background: white;
    border: 2px solid #3e55bf;
  }
`;

export const PopupOverlay = styled.div`
  position: fixed;
  inset: 0;
  background: rgba(0, 0, 0, 0.45);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

export const PopupBox = styled.div`
  width: 360px;
  background: #fff;
  border-radius: 12px;
  padding: 24px;
  animation: popup 0.25s ease-out;

  @keyframes popup {
    from {
      opacity: 0;
      transform: scale(0.9);
    }
    to {
      opacity: 1;
      transform: scale(1);
    }
  }
`;

export const PopupTitle = styled.h3`
  margin-bottom: 12px;
`;

export const PopupContent = styled.p`
  font-size: 14px;
  line-height: 1.6;
  color: #444;
`;

export const PopupClose = styled.button`
  margin-top: 20px;
  width: 100%;
  padding: 10px;
  border-radius: 8px;
  border: none;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  color: white;
  cursor: pointer;
`;

export const ProgressWrapper = styled.div`
  width: 100%;
  height: 8px;
  background-color: #e0e0e0;
  border-radius: 4px;
  overflow: hidden;
  margin-top: 4px;
`;

export const ProgressBar = styled.div<{ progress: number }>`
  width: ${({ progress }) => progress}%;
  height: 100%;
  background-color: #4caf50;
  transition: width 0.3s ease;
`;