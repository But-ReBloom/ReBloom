// src/pages/ChannelPage.styles.ts
import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    height: 100vh;
`;

export const LogoImage = styled.img`
    position: absolute;
    top: 17px;
    left: 36px;
    width: 160px;
    height: auto;
    cursor: pointer;
    transform: scale(1);
    transition: transform 0.3s ease; 

    &:hover {
        transform: scale(1.1);
        transition-duration: 0.15s; 
    }
    `;

export const Sidebar = styled.div`
    width: 280px;
    background-color: #f8f9fa;
    padding: 20px;
    display: flex;
    flex-direction: column;
    padding-top: 100px;
`;


export const CloseButton = styled.button`
    position: absolute;
    top: 25px;
    right: 35px;
    background: none;
    border: none;
    cursor: pointer;
    padding: 0;
`;

    export const CloseIconImg = styled.img`
    width: 34px;
    height: 34px;
`;

export const ContentArea = styled.div`
    flex: 1;
    padding: 24px;
    overflow-y: auto;
`;

export const BackButton = styled.button`
    margin-bottom: 12px;
    padding: 6px 12px;
    background-color: #eee;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    margin-top: 10px;
    &:hover {
        background-color: #ddd;
    }
`;

export const ChannelTitle = styled.h1`
    margin: 8px 0;
`;

export const ChannelIntro = styled.p`
    margin: 4px 0;
    color: #666;
`;

export const ChannelDescription = styled.p`
    margin: 8px 0;
`;

export const HobbyTag = styled.span`
    display: inline-block;
    margin-right: 8px;
    padding: 4px 8px;
    background: #eee;
    border-radius: 8px;
    font-size: 12px;
`;

export const JoinChannelButton = styled.button<{ joined?: boolean }>`
    padding: 10px 15px;
    margin-top: 10px;
    background-color: ${props => (props.joined ? '#aaa' : '#5db9eeff')};
    color: #fff;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-weight: bold;
    &:hover {
        background-color: ${props => (props.joined ? '#888' : '#2078abac')};
    }
`;
export const SearchBox = styled.div`
    margin-top: 15px;
    input {
        width: 100%;
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #ccc;
    }
`;
export const ProfileSection = styled.div`
    display: flex;
    align-items: center;
    gap: 10px;
    margin-top: 20px;

    img {
        border-radius: 50%;
        width: 60px;
        height: 60px;
        border: 0.5px solid #000000ff;
    }

    div {
        strong {
            display: block;
            font-size: 1rem;
            margin-top: 13px;
        }
        p {
            font-size: 0.8rem;
            color: gray;
            margin-top: 10px;
        }
    }
`;
export const WritePostButton = styled.button`
    width: 100%;
    padding: 10px;
    margin: 12px 0;
    margin-top: 20px;
    margin-bottom: 0px;
    background-color: #5db9eeff;
    color: white;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-weight: bold;
    &:hover {
        background-color: #5db9eeac;
    }
`;