import styled from "styled-components";

/* ===============================
   배경
================================ */
export const Background = styled.div`
  width: 100%;
  min-height: 100vh;
  background: linear-gradient(103deg, #bbe4fc 15.07%, #ffffff 108.45%);
`;

/* ===============================
   전체 래퍼
================================ */
export const Wrrapper = styled.div`
  width: 100%;
  min-height: calc(100vh - 80px);

  display: flex;
  justify-content: center;
  padding: 60px 100px;
`;

/* ===============================
   메인 컬럼
================================ */
export const MainColumn = styled.div`
  width: 100%;
  max-width: 1200px;
  margin-top: 160px;
  display: flex;
  flex-direction: column;
  gap: 48px;
`;

/* ===============================
   타이틀
================================ */
export const Title = styled.div`
  font-size: 32px;
  font-weight: 700;
`;

/* ===============================
   점수 영역 (1행 5열)
================================ */
export const ScoreRow = styled.div`
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 24px;
`;

/* ===============================
   점수 박스
================================ */
export const ResultBox = styled.div`
  background-color: #ffffff;
  border-radius: 12px;
  padding: 20px 24px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

  display: flex;
  flex-direction: column;
  justify-content: space-between;

  transition: all 0.3s ease;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 12px rgba(0, 0, 0, 0.2);
  }
`;

export const Subtitle = styled.p`
  font-size: 18px;
  color: #555;
  font-weight: 400;
  margin: 0 0 12px 0;
`;

export const Scoretitle = styled.p`
  font-size: 28px;
  font-weight: 600;
  color: rgb(0, 106, 255);
  text-align: right;
  margin: 0;
`;

/* ===============================
   추천 영역
================================ */
export const RecommendSection = styled.div`
  display: flex;
  flex-direction: column;
  gap: 24px;
  margin-top: 40px;
  margin-left: 40px;
`;

export const RecommendRow = styled.div`
  display: flex;
  align-items: center;
  gap: 32px;
`;

/* ===============================
   추천 카드
================================ */
export const RecommaendBox = styled.div`
  width: 300px;
  height: 150px;

  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-size: 20px;
  font-weight: 400;
`;

/* ===============================
   나무에 추가 버튼
================================ */
export const ChoiceBtn = styled.button`
  width: 180px;
  height: 36px;
  margin-top: 14px;

  background-color: rgb(0, 106, 255);
  color: #ffffff;
  font-size: 18px;
  font-weight: 500;

  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    background-color: rgb(115, 164, 233);
    transform: translateY(-3px);
  }
`;

/* ===============================
   이동 화살표
================================ */
export const ArrowImage = styled.button`
  width: 60px;
  height: 60px;

  background: #ffffff;
  border: 2px solid rgb(210, 210, 210);
  border-radius: 50%;

  display: flex;
  justify-content: center;
  align-items: center;

  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.2);
  }
`;
