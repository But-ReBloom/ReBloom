import styled from "styled-components";
import { Link } from "react-router-dom";

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
`;

export const LoginTextBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  margin-top: 50px;
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
`;

export const InputLabel = styled.p`
  color: black;
  margin: 8px 4px;
`;

export const Input = styled.input`
  width: 60vh;
  height: 50px;
  margin: 4px 0;
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

export const ButtonBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: end;
  gap: 60px;
  margin-top: 4px;
`;

export const Forgots = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  width: 100%;
`;

export const Forgot_a = styled(Link)`
  margin: 0px 8px;
  font-size: 14px;
  cursor: pointer;
  color: #000;
  text-decoration: none;

  &:hover {
    text-decoration: underline;
    color:rgb(79, 38, 202);
  }
`; 

export const LoginButton = styled.button`
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

  &:hover {
    color: #3e55bf;
    background: white;
    border: 2px solid #3e55bf;
    font-size: 20px;
    font-weight: bold;
    transform: scale(1.05);
  }
`;

export const OAuthFamily = styled.div`
  display: flex;
  flex-direction: row;
  gap: 20px;
  border: none;
  margin-top: 40px;
`;

export const OAuthButton = styled.button`
  border: none;
  cursor: pointer;
  background: none;

  img {
    width: 50px;
    height: 50px;
    border-radius: 25px;
    background: #ebebeb;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: all 0.3s ease;

    &:hover {
      transform: scale(1.1);
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
    }
  }
`;

export const SignUpTag = styled.a`
  cursor: pointer;
  margin-top: 20px;
  font-size: 14px;
  color: black;
  text-decoration: none;
  font-weight: 600;

  &:hover {
    text-decoration: underline;
    color: #3e55bf;
  }
`;