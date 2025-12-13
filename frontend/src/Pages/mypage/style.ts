import styled, { keyframes } from "styled-components";

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
  flex-direction: row;
  width: 1200px;
  height: 660px;
  background-color: white;
  border-radius: 20px;
  box-shadow: 0px 0px 16px rgba(0, 0, 0, 0.25);
`;

export const LeftSection = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  padding: 60px 40px;
  overflow-y: auto;
  
  /* 스크롤바 커스텀 (선택) */
  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background: rgba(0, 0, 0, 0.2);
    border-radius: 4px;
  }
`;

export const UserInfoSection = styled.div`
  width: 300px;
  height: 540px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 20px;
`;

export const ProfileInfo = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  margin-bottom: 8px;
`;

export const UserImage = styled.img`
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin-right: 20px;
  border: 2px solid rgba(51, 51, 51, 0.25);
`;

export const UserName = styled.h2`
  font-size: 28px;
  margin: 0;
`;

export const UserTier = styled.h3`
  font-size: 12px;
  color: rgba(51, 51, 51, 0.55);
  margin: 0;
  text-align: right;
`;

export const PointArchive = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 50px;
  margin-bottom: 20px;
`;

export const addedimage = styled.img`
  width: 24px;
  height: 24px;
  margin-right: 8px;
`;

export const PnA = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;

export const ArchiveMent = styled.div`
  padding-left: 12px;
  width: 100%;
  height: 40px;
  border-radius: 8px;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  display: flex;
  align-items: center;
  font-size: 16px;
  font-weight: 500;
  color: #fff;
  margin-bottom: 12px;
`;

export const ArchiveList = styled.div`
  width: 100%;
  height: 400px;
`;

/* ===============================
   업적 박스 (hover 효과 추가)
================================ */
export const Box = styled.div`
  display: flex;
  padding: 12px;
  align-items: center;
  width: 100%;
  height: 40px;
  border-bottom: 1px solid rgba(51, 51, 51, 0.25);
  cursor: pointer;
  transition: background-color 0.2s;

  &:hover {
    background-color: rgba(65, 166, 255, 0.1);
  }
`;

export const RightSection = styled.div`
  flex: 1;
  padding: 80px 60px;
  border-left: 1px solid rgba(51, 51, 51, 0.15);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  position: relative;
  flex: 1;
  overflow-y: auto;

  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background: rgba(0, 0, 0, 0.2);
    border-radius: 4px;
    padding: 12px;
  }
`;

export const DetailTitle = styled.h2`
  font-size: 32px;
  margin-bottom: 20px;
`;

export const DetailDescription = styled.p`
  font-size: 16px;
  color: rgba(51, 51, 51, 0.75);
  line-height: 1.6;
`;

export const EmptyText = styled.div`
  font-size: 16px;
  color: rgba(51, 51, 51, 0.4);
`;

const float = keyframes`
  0% { transform: translateY(0px); }
  50% { transform: translateY(-10px); }
  100% { transform: translateY(0px); }
`;

export const Bubble = styled.div`
  position: absolute;
  width: 120px;
  height: 120px;
  border-radius: 50%;
  background: linear-gradient(135deg, #41a6ff, #3e55bf);
  color: white;

  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 12px;

  font-size: 14px;
  font-weight: 500;
  cursor: pointer;

  /* 둥둥 */
  animation: ${float} 3s ease-in-out infinite;

  /* 🔥 핵심 */
  will-change: left, top, transform;

  /* scale만 부드럽게 */
  transition: box-shadow 0.25s ease;

  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.25);

  &:hover {
    transform: scale(1.08);
    z-index: 10;
    box-shadow: 0 12px 28px rgba(0, 0, 0, 0.35);
  }
`;

export const ProgressBar = styled.div`
  width: 350px;
  height: 20px;
  background: #e5e7eb;
  border-radius: 9999px;
  overflow: hidden;
  margin-left: 20px;
`;

export const ProgressFill = styled.div<{ progress: number }>`
  width: ${({ progress }) => progress}%;
  height: 100%;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  transition: width 0.4s ease;
`;

export const ProgressTitle = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin: 8px 0;
`;
