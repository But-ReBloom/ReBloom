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
  height: 640px;
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

export const PageNumBox = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: row;
  margin-top: auto;
  margin-bottom: 20px;
`;

export const PageCountBox = styled.button`
  width: 32px;
  height: 32px;
  border-radius: 20%;
  border: none;
  background-color: #ffffff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  margin: 0 4px;
  cursor: pointer;
  transition: all 0.3s ease;
  &:hover {
    transform: scale(1.08);
    color: #ffffff;
    font-weight: bold;
    background: linear-gradient(90deg, #41a6ff, #3e55bf);
  }
`;
