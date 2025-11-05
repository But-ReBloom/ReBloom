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
