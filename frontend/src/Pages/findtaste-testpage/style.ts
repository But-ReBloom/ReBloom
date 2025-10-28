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
    justify-content: center;

    gap: 20px;
`

export const Text = styled.p`
    color: rgba(0, 0, 0, 0.80);
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
    padding-bottom: 20px;
`;

export const BottomTextBox = styled.div`
    gap: -5px;
`;

export const StartConnect_Button = styled.button`
    width: 600px;
    height: 60px;
    padding: 16px 24px;
    border: none;
    border-radius: 20px; 

    background: linear-gradient(103deg, #287dfc 0%, #2b57d9 100%);
    color: #fff;
    font-size: 18px;
    font-family: "Nanum Gothic";
    font-weight: bold;
    cursor: pointer;
    text-align: center;
    transition: transform 0.2s ease, box-shadow 0.2s ease;
    &:hover {
    transform: translateY(-2px);
    }
    &:active {
    transform: translateY(0);
    box-shadow: none;
}`;

export const Select_Box = styled.div`
    width: 900px;
    height: 650px;

    border-radius: 20px;

    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;

    background-color: #ffffff87;

    padding-bottom: 100px;

    background: rgba(255, 255, 255, 0.30);
    box-shadow: 0 4px 100px 0 rgba(0, 0, 0, 0.25);
`

export const Page_num = styled.div`
    width: 800px;
    padding-top: 100px;
    text-align: right;
`

export const Button_Box = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
`

export const Before_button = styled.button`
    background-color: #ffffff00;
    border: #ffffff00;

    font-size: 20px;

    cursor: pointer;
`

export const After_button = styled.button`
    background-color: #ffffff00;
    border: #ffffff00;

    font-size: 20px;

    cursor: pointer;
`

