import styled from "styled-components";

export const Content = styled.div`
  margin-top: 13%;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  height: 100%;
`;

export const Hihand = styled.img`
  width: 80px;
`;

export const Arrow = styled.img`
    width: 28px;
  color: white;
`;

export const Thankyou = styled.h1`
  margin-top: 5%;
  font-weight: 700;
`;

export const Announce = styled.h3`
  font-weight: 700;
  color: #a3a3a3;
`;

export const StartBtn = styled.button`
  margin-top: 5%;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 60vh;
  height: 50px;
  background: #333;
  color: #ffffff;
  padding: 8px 16px;
  cursor: pointer;
  font-size: 18px;
  transition: all 0.3s ease;
  border: 1px solid #dedede;
  font-weight: 500;
  border-radius: 16px;

  &:hover {
    background: linear-gradient(90deg, #41a6ff, #3e55bf);
    border: 2px solid #3e55bf;
    font-size: 20px;
    font-weight: bold;
    transform: scale(1.05);
  }
`;
