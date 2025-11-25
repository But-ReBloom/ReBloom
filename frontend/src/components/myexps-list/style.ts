import styled from "styled-components";

export const Head = styled.div`
  display: flex;
  align-items: center;
  flex-direction: row;
  width: 91.5%;
  height: 36px;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  justify-content: space-between;
  border-radius: 12px;
  padding: 0 60px;
`;

export const Tags = styled.p`
  font-size: 14px;
  color: rgb(255, 255, 255);
`;

export const Locate = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 300px;
  margin-right: 36px;
`;

export const Wrraper = styled.div`
  background-color: rgb(255, 255, 255, 0.58);
  display: flex;
  align-items: center;
  margin-top: 12px;
  width: 91.5%;
  height: 44px;
  // border: 1px solid #000;
  border-radius: 12px;
  cursor: pointer;
  box-shadow: 0px 0px 4px rgba(0, 0, 0, 0.26);

  &:hover {
    background-color: rgb(211, 241, 255);
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

export const Locates = styled.div`
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

export const Info_Body = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 12px;
  width: 100%;
  overflow-y: auto;
`;
