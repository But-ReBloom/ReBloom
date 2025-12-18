import styled from "styled-components";

/* ===============================
   배경
================================ */
export const Background = styled.div`
  width: 100%;
  min-height: 100vh;
  background: linear-gradient(103deg, #bbe4fc 15.07%, #ffffff 108.45%);
`;

export const Wrrapper = styled.div`
  width: 100%;
  min-height: calc(100vh - 80px);
  display: flex;
  justify-content: center;
  padding: 60px 100px;
`;

export const MainColumn = styled.div`
  width: 100%;
  max-width: 1200px;
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  gap: 48px;
`;

export const Title = styled.div`
  font-size: 32px;
  font-weight: 700;
`;

/* ===============================
   그래프 영역
================================ */
export const GraphSection = styled.div`
  background: #ffffff;
  border-radius: 16px;
  padding: 60px 100px;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.12);
  height: 100%;
  display: flex;
  flex-direction: column;
  gap: 48px;
`;

export const GraphTitle = styled.div`
  font-size: 24px;
  font-weight: 600;
`;

/* ===============================
   상대 막대그래프
================================ */
export const RelativeChart = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: flex-end;
  padding: 0 40px;
  height: 100%;
`;

export const RelativeBarItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100px;
  height: 100%;
`;

export const RelativeBarWrapper = styled.div<{ $height: number }>`
  width: 36px;
  height: ${({ $height }) => $height * 2}px;

  display: flex;
  flex-direction: column;
`;

/* ===== 기준선 ===== */
export const ZeroLine = styled.div`
  width: 100%;
  height: 2px;
  background-color: #444;
  margin: 12px 0;
`;

export const PositiveArea = styled.div`
  flex: 1;
  width: 100%;
  display: flex;
  align-items: flex-end;
  justify-content: center;
`;

export const PositiveBar = styled.div<{ $value: number; $unit: number }>`
  width: 100%;
  height: ${({ $value, $unit }) => $value * $unit}px;
  background: linear-gradient(180deg, #006aff, #73a4e9);
  border-radius: 8px;
  transition: height 0.6s ease;
`;

export const NegativeArea = styled.div`
  flex: 1;
  width: 100%;
  display: flex;
  align-items: flex-start;
  justify-content: center;
`;

export const NegativeBar = styled.div<{ $value: number; $unit: number }>`
  width: 100%;
  height: ${({ $value, $unit }) => Math.abs($value) * $unit}px;
  background: linear-gradient(180deg, #ff9a9a, #ff6b6b);
  border-radius: 8px;
  transition: height 0.6s ease;
`;

export const ScoreArea = styled.div`
  margin-top: 16px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 6px;
`;

export const BarValue = styled.div`
  font-size: 15px;
  font-weight: 600;
`;

export const BarLabel = styled.div`
  font-size: 14px;
  color: #555;
`;

/* ===============================
   추천 영역
================================ */
export const RecommendSection = styled.div`
  display: flex;
  justify-content: space-between;
`;

export const RecommendRow = styled.div`
  display: flex;
  align-items: center;
  gap: 32px;
`;

export const RecommaendBox = styled.div`
  width: 240px;
  height: 120px;
  background: #ffffff;
  border-radius: 14px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  gap: 12px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  font-size: 20px;
  font-weight: 600;
`;

/* ===============================
   이동 버튼
================================ */
export const ArrowImage = styled.button`
  width: 60px;
  height: 60px;
  border-radius: 50%;
  border: 2px solid #ccc;
  background: #ffffff;
  cursor: pointer;

  display: flex;
  justify-content: center;
  align-items: center;
  transition: transform 0.3s ease;

  &:hover {
    transform: scale(1.15);
  }
`;

export const BarContainer = styled.div<{ $height: number }>`
  width: 36px;
  height: ${({ $height }) => $height * 2 + 40}px;
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const addTree = styled.button`
  height: 30px;
  background-color: rgb(60, 99, 255);
  color: white;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
  padding: 12px;

  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    background-color: rgb(43, 83, 240);
  }
`;
