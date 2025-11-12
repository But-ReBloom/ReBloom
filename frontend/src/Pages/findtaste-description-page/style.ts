import styled from "styled-components"

export const Header = styled.div`
    width: 100%;
    position: fixed;
`

export const Box = styled.div`
    width: 100%;
    height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
`

export const Background = styled.div`
    width: 100%;
    height: 100vh;
    background: linear-gradient(103deg,#BBE4FC 15.07%,#FFFFFF 108.45%);
`

export const MainContainer = styled.div`
    width: 100%;
    height: 100%; /* 화면 전체 채우기 */

    display: flex;
    align-items: center;
    justify-content: center;
`;

export const RowContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;

    gap: 56px;
`

export const Text = styled.p`
    color: rgba(0, 0, 0, 0.48);
    text-align: center;
    font-family: Pretendard;
    font-size: 22px;
    margin: 0px;
    margin-top: 8px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
`;

export const TextBox = styled.div`
`;

export const TopTextBox = styled.div`
    padding-top: 10px;
    padding-bottom: 20px`;

export const BottomTextBox = styled.div`
    gap: -5px;
`;

export const StartConnect_Button = styled.button`
    width: 600px;
    height: 60px;
    padding: 16px 24px;
    border: none;
    border-radius: 20px; 

    background: #000;
    color: #fff;
    font-size: 18px;
    font-family: "Nanum Gothic";
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition: all 0.3s ease;
    &:hover {
    transform: scale(1.1);
  background: linear-gradient(90deg, #41a6ff, #3e55bf);
    }
    &:active {
    transform: translateY(0);
    box-shadow: none;
    }
`;