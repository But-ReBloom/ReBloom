import styled from "styled-components";

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
  padding: 20px;
`;

export const ProfileInfo = styled.div`
  display: flex;
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
  padding: 4px;
`;

export const UserName = styled.h2`
  font-size: 28px;
  margin: 0;
`;

export const UserTier = styled.h3`
  font-size: 14px;
  color: rgba(51, 51, 51, 0.55);
  margin: 0;
`;

export const PointArchive = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
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

export const Box = styled.div`
  display: flex;
  padding: 12px;
  align-items: center;
  width: 100%;
  height: 40px;
  border-bottom: 1px solid rgba(51, 51, 51, 0.25);
  cursor: pointer;

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
  overflow-y: auto;

  &::-webkit-scrollbar {
    width: 6px;
  }
  &::-webkit-scrollbar-thumb {
    background: rgba(0, 0, 0, 0.2);
    border-radius: 4px;
  }
`;

export const DetailTitle = styled.h2`
  font-size: 32px;
  margin-bottom: 20px;
`;

export const ProgressTitle = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 8px 0;
`;

export const ProgressBar = styled.div`
  width: 350px;
  height: 20px;
  background: #e5e7eb;
  border-radius: 9999px;
  overflow: hidden;
`;

export const ProgressFill = styled.div<{ progress: number }>`
  width: ${({ progress }) => progress}%;
  height: 100%;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  transition: width 0.4s ease;
`;
