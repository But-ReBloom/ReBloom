// src/pages/ChannelPage.styles.ts
import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    background: #f8f9fb;
    min-height: 100vh;
    position: relative;
`;

export const Sidebar = styled.div`
    width: 280px;
    background-color: #ffffffc6;
    border-right: 1px solid #ddd;
    padding: 20px;
    display: flex;
    flex-direction: column;
    padding-top: 100px;
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
    margin-top: 10px;
    margin-bottom: 12px;
    padding: 6px 12px;
    background-color: #eee;
    border: none;
    border-radius: 6px;
    cursor: pointer;

    &:hover {
        background-color: #ddd;
    }
`;

export const ChannelTitle = styled.h1`
    margin: 0;
    font-size: 1.8rem;
    font-weight: 700;
    color: #333;
`;

export const ChannelIntro = styled.p`
    margin: 8px 0;
    font-size: 1rem;
    color: #666;
`;

export const ChannelDescription = styled.p`
    margin: 12px 0;
    font-size: 1rem;
    color: #555;
    line-height: 1.6;
`;

export const HobbyTagContainer = styled.div`
    margin-top: 12px;
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
`;

export const HobbyTag = styled.span`
    display: inline-block;
    padding: 6px 12px;
    background-color: #f0f0f0;
    border-radius: 20px;
    font-size: 0.85rem;
    color: #555;
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
            margin-bottom: 5px;
            margin-left: 5px;
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
    margin-top: 20px;
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
export const ChannelContainer = styled.div`
    // background: #ffffff;
    border-radius: 8px;
    padding: 30px 40px;
    margin-bottom: 30px;
    // box-shadow: 0 2px 6px rgba(0,0,0,0.05);
`;
