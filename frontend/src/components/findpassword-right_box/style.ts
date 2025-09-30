import styled from "styled-components";
// import { Link } from "react-router-dom";

export const LoginContainer = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 85vh;
  height: 90vh;
  background: #ffffff;
  border-radius: 20px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
  // position: fixed;
`;

export const LoginTextBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-top: 20px;
`;

export const Title = styled.p`
  font-size: 48px;
  color: black;
  margin: 0;
`;

export const Subtitle = styled.p`
  font-size: 20px;
  color: black;
  margin-top: 10px;
`;

export const InputBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  gap: 12px;
`;

export const Input_title = styled.button`
    display: flex;
    flex-direction: column;
    align-items: start;
    background: none;
    margin: 0;
    padding: 0;
    border: none;
`;

export const InputID = styled.div`
    display:flex;
    flex-direction: column;
`;

export const InputLabel = styled.label`
  color: black;
  margin: 8px 4px;
`;

export const Input = styled.input`
  width: 60vh;
  height: 50px;
  margin: 0;
  padding: 0 16px;
  border-radius: 16px;
  border: none;
  font-size: 16px;
  background: #ebebeb;
  transition: border 0.05s ease, transform 0.3s ease;

  &:hover {
    border: 2px solid #3e55bf;
    transform: scale(1.01);
  }
`;

export const Send = styled.button`
    width: 60vh;
    height: 50px;
    background: linear-gradient(90deg, #41a6ff, #3e55bf);
    color: #ffffff;
    padding: 8px 16px;
    cursor: pointer;
    font-size: 18px;
    transition: all 0.3s ease;
    border: 1px solid #dedede;
    font-weight: 500;
    border-radius: 16px;
    margin-top: 32px;
    margin-bottom: 20px;

    &:hover {
        color: #3e55bf;
        background: white;
        border: 2px solid #3e55bf;
        font-size: 20px;
        font-weight: bold;
        transform: scale(1.05);
    }
`;

export const gotoLogin = styled.button`
    width: 20vh;
    height: 50px;
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