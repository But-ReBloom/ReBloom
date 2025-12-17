import styled from "styled-components";

export const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  background: linear-gradient(103deg, #bbe4fc 15.07%, #ffffff 108.45%);
`;

export const Container = styled.div`
  width: 100%;
  margin: 112px auto 0 auto;
  display: flex;
  justify-content: center;
`;

export const Menu = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 60px;
  border-radius: 20px;
  border: 2px solid #dedede;
  width: 300px;
  height: 450px;
  background: #fff;
`;

export const MenuContent = styled.div`
  display: flex;
  flex-direction: column;
  gap: 40px;
`;

export const MenuBox = styled.button`
  width: 25vh;
  height: 60px;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  color: #ffffff;
  padding: 8px 16px;
  cursor: pointer;
  font-size: 18px;
  transition: all 0.3s ease;
  border: 1px solid #dedede;
  font-weight: 500;
  border-radius: 999px;

  &:hover {
    color: #3e55bf;
    background: white;
    border: 2px solid #3e55bf;
    font-size: 20px;
    font-weight: bold;
    transform: scale(1.05);
  }
`;

export const IntroCon = styled.div`
  border-radius: 20px;
  border: 2px solid #dedede;
  width: 60%;
  height: 450px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  background: #fff;
  p {
    font-size: 18px;
    margin-bottom: 10px;
    color: #555555;
  }
`;

export const IntroTexting = styled.div`
  display: flex;
  flex-direction: column;
`;

export const IntroduceUs = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  margin-top: 80px;
  width: 100%;
`;

export const Btn = styled.button`
  background: none;
  border: none;
  cursor: pointer;
  margin: 0 20px;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.2);
  }
`;
