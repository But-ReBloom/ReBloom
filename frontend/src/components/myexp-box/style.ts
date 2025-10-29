import styled from "styled-components";

export const Wrraper = styled.div`
  background-color: rgb(255, 255, 255, 0.58);
  display: flex;
  align-items: center;
  margin-top: 12px;
  width: 91.5%;
  height: 44px;
  border: 1px solid #000;
  border-radius: 12px;
  cursor: pointer;

  &:hover {
    background-color:rgb(211, 241, 255);
    border: 1px solid #3e55bf;
  }
`;

export const Container = styled.div`
  width: 94%;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  margin: 0 16px 0 16px;
`;

export const Locate = styled.div`
  width: 39%;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

export const ExpInfo = styled.button`
  display: flex;
  align-items: center;
  font-size: 16px;
  font-weight: 500;
  gap: 8px;
  background: none;
  border: none;
`;
