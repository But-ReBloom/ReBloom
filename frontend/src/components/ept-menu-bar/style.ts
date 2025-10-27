import styled from "styled-components";

export const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  background-color: rgb(255, 255, 255);
`;

export const Container = styled.div`
  width: 100%;
  margin: 112px auto 0 auto;
  display: flex;
  justify-content: center;
`;

export const MenuContent = styled.div`
  display: flex;
  flex-direction: row;
  gap: 100px;
`;

export const MenuBox = styled.button`
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  color: white;
  border: 1px solid #3e55bf;
  padding: 15px 30px;
  font-size: 18px;
  border-radius: 999px;
  cursor: pointer;
  transition: all 0.3s ease;
  font-weight: 500;
  width: 200px;

  &:hover {
    border: 1px solid #dedede;
    background: white;
    color: #50b9ff;
    transform: scale(1.1);
  }
`;

export const IntroCon = styled.div`
  border-radius: 20px;
  border: 2px solid #dedede;
  width: 60%;
  height: 350px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  text-align: center;
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
  margin-top: 52px;
  width: 100%;
`;

export const Btn = styled.button`
  background: none;
  border: none;
  cursor: pointer;
  margin: 0 20px;
  transition: all 0.3s ease;

  &:hover{
    transform: scale(1.2);
  }
`;
