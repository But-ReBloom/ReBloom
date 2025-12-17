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
export const GraphSection = styled.div<{ $graphHeight: number }>`
  background: #ffffff;
  border-radius: 16px;
  padding: 60px 100px;
  box-shadow: 0 6px 10px rgba(0, 0, 0, 0.12);

  display: flex;
  flex-direction: column;
  gap: 48px;

  /* 🔑 핵심 */
  min-height: ${({ $graphHeight }) => $graphHeight + 180}px;
`;

export const GraphTitle = styled.div`
  width: 100%;
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
`;

export const ZeroLine = styled.div`
  position: absolute;
  left: 0;
  right: 0;
  top: 50%;
  height: 2px;
  background-color: #555;
`;

export const RelativeBarItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100px;
  height: 100%;
`;

export const RelativeBarWrapper = styled.div<{ $height: number }>`
  position: relative;
  width: 36px;
  height: ${({ $height }) => $height}px;
`;

export const RelativeBar = styled.div<{
  $value: number;
  $unit: number;
}>`
  position: absolute;
  bottom: 0;
  width: 100%;
  height: ${({ $value, $unit }) =>
    Math.abs($value) * $unit}px;

  background: ${({ $value }) =>
    $value >= 0
      ? "linear-gradient(180deg, #006aff, #73a4e9)"
      : "linear-gradient(180deg, #ff6b6b, #ff9a9a)"};

  border-radius: 8px;
  transition: height 0.6s ease;
`;


export const BarValue = styled.div`
  margin-top: 10px;
  font-size: 15px;
  font-weight: 600;
`;

export const BarLabel = styled.div`
  margin-top: 4px;
  font-size: 14px;
  color: #555;
`;

/* ===============================
   추천 영역
================================ */
export const RecommendSection = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

export const RecommendRow = styled.div`
  display: flex;
  gap: 32px;
`;

export const RecommaendBox = styled.div`
  width: 240px;
  height: 120px;
  background: #ffffff;
  border-radius: 14px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

  display: flex;
  justify-content: center;
  align-items: center;
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
  align-self: center;

  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.15);
  }
`;
