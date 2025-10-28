import styled from "styled-components";

export const Center = styled.div`
    display: flex;
    flex-direction: column;

    gap: 20px;
    justify-content: center;
    align-items: center;       
    text-align: center;
;`

export const Blue_Line = styled.div`
    width: 429.019px;
    height: 5px;

    background: #072590;
    box-shadow: 0 4px 4px 0 rgba(0, 0, 0, 0.25);

    margin: 0 auto;
`;

export const Connect_Button = styled.button`
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
    }
`;

export const Title = styled.p`
    color: #00176F;
    font-weight: 800;
    margin: 0px;
    text-align: center;

    font-family: "Nanum Gothic", sans-serif;
    font-size: 40px;
    font-style: normal;
`;

export const subTitle = styled.p`
    color: #A3A3A3;
    font-weight: 800;
    margin: 0px;
    text-align: center;

    font-family: "Nanum Gothic"; 
    font-size: 20px;
    font-weight: 600px;  
`;

export const Title_box = styled.div`
    display: flex;
    flex-direction: column;
    gap: 20px;
`


export const subTitle_box = styled.div`
    display: flex;
    flex-direction: column;
    gap: 100px;
`;

