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
  width: 70px;
  height: 2px;
  background-color: #444;
  margin: 12px 0;
`;

export const PositiveArea = styled.div`
  flex: 1;
  width: 70px;
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
  width: 70px;
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

  &:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
`;

/* ===============================
   모달 영역
================================ */
export const ModalOverlay = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
`;

export const ModalContent = styled.div`
  background: #ffffff;
  border-radius: 16px;
  padding: 40px;
  width: 90%;
  max-width: 600px;
  max-height: 80vh;
  overflow-y: auto;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.2);
`;

export const ModalTitle = styled.h2`
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 24px;
  text-align: center;
  color: #333;
`;

export const QuestionSection = styled.div`
  margin-bottom: 24px;
`;

export const QuestionLabel = styled.label`
  display: block;
  font-size: 16px;
  font-weight: 600;
  margin-bottom: 8px;
  color: #444;
`;

export const AnswerInput = styled.textarea`
  width: 100%;
  min-height: 80px;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  resize: vertical;
  
  &:focus {
    outline: none;
    border-color: rgb(60, 99, 255);
    box-shadow: 0 0 0 2px rgba(60, 99, 255, 0.2);
  }
`;

export const ModalButtonRow = styled.div`
  display: flex;
  justify-content: center;
  gap: 16px;
  margin-top: 24px;
`;

export const ModalButton = styled.button<{ $primary?: boolean }>`
  padding: 12px 32px;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.2s ease;
  
  background-color: ${({ $primary }) => ($primary ? "rgb(60, 99, 255)" : "#f0f0f0")};
  color: ${({ $primary }) => ($primary ? "#fff" : "#333")};
  border: none;
  
  &:hover {
    background-color: ${({ $primary }) => ($primary ? "rgb(43, 83, 240)" : "#e0e0e0")};
  }
  
  &:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
`;

export const LoadingSpinner = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  font-size: 18px;
  color: #666;
`;

export const NewHobbiesSection = styled.div`
  margin-top: 48px;
  padding-top: 32px;
  border-top: 2px solid #eee;
`;

export const NewHobbiesTitle = styled.h3`
  font-size: 22px;
  font-weight: 600;
  margin-bottom: 24px;
  color: #333;
`;

export const AddedBadge = styled.span`
  background-color: #4caf50;
  color: white;
  font-size: 12px;
  padding: 4px 8px;
  border-radius: 4px;
  margin-left: 8px;
`;

export const ModalQuestion = styled.p`
  font-size: 16px;
  color: #666;
  margin-bottom: 24px;
  text-align: center;
  line-height: 1.5;
`;

export const ModalForm = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

export const ModalInputGroup = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
  
  label {
    font-size: 14px;
    font-weight: 600;
    color: #444;
  }
`;

export const ModalTextarea = styled.textarea`
  width: 100%;
  min-height: 60px;
  padding: 12px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 14px;
  resize: vertical;
  
  &:focus {
    outline: none;
    border-color: rgb(60, 99, 255);
    box-shadow: 0 0 0 2px rgba(60, 99, 255, 0.2);
  }
`;

export const ModalButtonGroup = styled.div`
  display: flex;
  justify-content: center;
  gap: 16px;
  margin-top: 24px;
`;
