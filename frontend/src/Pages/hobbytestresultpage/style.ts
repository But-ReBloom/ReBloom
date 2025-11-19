import styled from "styled-components";

export const Background = styled.div`
  width: 100%;
  height: 100vh;
  background: linear-gradient(103deg, #bbe4fc 15.07%, #ffffff 108.45%);
`;

export const Wrrapper = styled.div`
  width: 100%;
  height: 100%; /* 화면 전체 채우기 */

  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 100px;
`;

export const LeftContainer = styled.div`
  width: 300px;
  height: auto;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  gap: 20px;
  padding: 20px;
  margin-left: none;
  border-radius: 10px;
`;

export const RightContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 60px;
`;

export const SemiContainer1 = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
`;

export const SemiContainer2 = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 40px;
  margin-left: 80px;
`;

export const Title = styled.div`
  font-size: 32px;
  font-weight: 700;
`;

export const ResultBox = styled.div`
  width: 350px;
  height: auto;
  font-size: 24px;
  font-weight: 500;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  padding: 15px 32px;
  border-radius: 10px;
`;

export const ResultLeft = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
`;

export const ResultRight = styled.div`
  height: 364px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 20px;
`;

export const Subtitle = styled.p`
  width: 100%;
  font-size: 18px;
  color:#555;
  font-weight: 400;
  margin-top: 10px;
`;

export const RecommaendBox = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 400px;
  height: 150px;
  border: none;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  font-size: 20px;
  font-weight: 400;
`;

export const ArrowImage = styled.button`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60px;
  height: 60px;
  background-color: #fff;
  border: 2px solidrgb(162, 162, 162);
  border-radius: 50%;
  transition: all 0.3s ease;
  &:hover {
    cursor: pointer;
    transform: scale(1.1);
  }
`;
