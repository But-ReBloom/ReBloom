import styled from "styled-components";

export const BodyContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 168px;
  background: linear-gradient(
    to bottom,
    #ffffff 12%,
    #bbe4fc 50%,
    #ffffff 100%
  );
  width: 100%;
  box-sizing: border-box;
  height: 100vh;
`;

export const IntroTextBox = styled.div`
  margin-top: 120px;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 60px;
  width: 100%;
`;

export const IntroBig = styled.p`
  font-size: 72px;
  font-weight: 700;
  color: #333;
  text-align: center;
  margin-bottom: 0;
  width: 80%;
`;

export const IntroSmall = styled.p`
  font-size: 24px;
  color: #333;
  text-align: center;
  margin-bottom: 40px;
  width: 60%;
  margin-top: 12px;
`;

export const BodyButtons = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
`;

export const GoFindTasteButton = styled.button`
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
  color: white;
  border: none;
  padding: 15px 30px;
  font-size: 18px;
  border-radius: 999px;
  cursor: pointer;
  transition: all 0.3s ease;
  width: 200px;

  &:hover {
    border: 1px solid #dedede;
    background: white;
    color: #50b9ff;
    transform: scale(1.1);
  }
`;

export const GoFindTasteHobbytest = styled.div`
  font-size: 16px;
  color: #000;
  text-decoration: none;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.1);
    text-decoration: underline;
  }
`;

// export const IntroButtons = styled.button`
//     border: none;
//     display: flex;
//     flex-direction: row;
//     align-items: center;
//     gap: 4px;
//     color: #000;
// `;

export const IntroButtons = styled.div`
cursor: pointer;
  border: none;
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 4px;
  transition: all 0.3s ease;

  &:hover {
    scale: 1.1;
  }
`;

export const DYT =styled.p`
    color: #000;
    text-decoration: none;

    &:hover {
        text-decoration: underline;
    }
`;
