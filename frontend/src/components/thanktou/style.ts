import styled from "styled-components";

export const Wrapper = styled.div`
  height: 100vh;
  background: linear-gradient(135deg, #bbe4fc 0%, #fff 100%);
`;

export const Container = styled.div`
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

export const Title = styled.p`
  font-size: 40px;
  font-weight: bolder;
`;

export const SubTitle = styled.p`
  margin-top: 0;
  font-size: 20px;
  font-weight: 400;
`;

export const HomeButton = styled.button`
  width: 600px;
  height: 60px;
  padding: 16px 24px;
  border: none;
  border-radius: 20px;
  margin-top: 120px;
  background: #000;
  color: #fff;
  font-size: 18px;
  font-family: "Nanum Gothic";
  font-weight: bold;
  cursor: pointer;
  text-align: center;
  transition: all 0.3s ease;
  &:hover {
    transform: scale(1.1);
    background: linear-gradient(90deg, #41a6ff, #3e55bf);
  }
`;
