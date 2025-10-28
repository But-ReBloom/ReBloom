import styled from "styled-components";


export const MainContainer = styled.div`
    padding: 16px 36px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    width: 100%;
    height: 80px;
    opacity: 1;
`;

export const Logo_svg = styled.img`
    width: 160px;
    margin: 0;
`;

export const CloseButton = styled.a`
    width: 35px;
    height: 35px;
    border: none;
    border-radius: 10px; 
    background: #f0f4f8; 
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.08);
    cursor: pointer;

    display: flex;
    align-items: center;
    justify-content: center;

    font-size: 20px; /* X 크기 */
    font-weight: 500;
    color: #000;
    text-decoration: none;

    transition: background 0.2s, transform 0.1s;

    &:hover {
        background: #e3e9ef;
    }

    &:active {
        transform: scale(0.95);
    }
`;