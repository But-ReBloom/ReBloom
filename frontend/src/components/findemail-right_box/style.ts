import styled from "styled-components";

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
  margin-bottom: 30px;
  margin-top: -20px;
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
  margin-top: 0px;
  width: 100%;
  gap: 12px;
`;

export const InputID = styled.div`
  display: flex;
  flex-direction: column;
`;

export const InputLabel = styled.label`
  color: black;
  margin: 8px 4px;
`;

export const Input = styled.input`
  width: 60vh;
  height: 50px;
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

export const PasswordWrapper = styled.div`
  position: relative;
  width: 60vh;
`;

export const PasswordInput = styled(Input)`
  width: 100%;
  padding-right: 120px; /* Send 버튼 공간 */
`;

export const Send = styled.button`
  position: absolute;
  right: 8px;
  top: 50%;
  transform: translateY(-50%);

  width: 100px;
  height: 36px;
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  color: #ffffff;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
  border: 1px solid #dedede;
  font-weight: 500;
  border-radius: 12px;

  &:hover {
    color: #3e55bf;
    background: white;
    border: 2px solid #3e55bf;
    font-weight: bold;
    transform: translateY(-50%) scale(1.05);
  }
`;

export const gotoLogin = styled.button`
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
  margin-top: 40px;

  &:hover {
    color: #3e55bf;
    background: white;
    border: 2px solid #3e55bf;
    font-size: 20px;
    font-weight: bold;
    transform: scale(1.05);
  }
`;
