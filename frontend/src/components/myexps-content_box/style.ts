import styled from "styled-components";

export const Wrapper = styled.div`
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 1200px;
  height: 600px;
  background-color: rgb(255, 255, 255, 0.58);
  border-radius: 20px;
`;

export const TitleBox = styled.div`
  display: flex;
  flex-direction: column;
  margin: 28px 20px;
  margin-right: auto;
  margin-left: 52px;
`;

export const Question = styled.p`
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 12px;
`;

export const Line = styled.div`
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
`;
