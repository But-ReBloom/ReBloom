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
  width: 1200px;
  height: 640px;
  background-color: rgb(255, 255, 255, 0.58);
  border-radius: 20px;
  box-shadow: 0px 0px 40px rgba(0, 0, 0, 0.12);
`;

export const Arrow = styled.button`
  margin-right: auto;
  margin-left: 16px;
  margin-top: 16px;
  background: none;
  border: none;
  cursor: pointer;
  transition: all 0.3s ease;
  transform: rotate(180deg);
  &:hover {
    transform: rotate(180deg) scale(1.2);
  }
`;

export const QuestionBox = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 48px;
`;

export const Boxing = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  flex-direction: column;
  margin-bottom: 24px;
`;

export const Title = styled.p`
  font-size: 20px;
  text-align: left;
  font-weight: 500;
`;

export const TextPlace = styled.div`
  display: flex;
  justify-content: center;
  width: 90%;
  gap: 20px;
  margin: 20px;
`;

export const TextingBox = styled.textarea`
  margin-top: 0;
  padding: 8px 12px;
  width: 100%;
  height: 200px;
  border-radius: 20px;
  border: 2px solid rgb(195, 195, 195);
  resize: none;
`;

export const DetailPlace = styled.div`
  width: 80%;
`;
