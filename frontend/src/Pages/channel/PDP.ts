// src/pages/PostDetailPage.styles.ts
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
    margin: 20px 0 0 0;
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

export const SearchBox = styled.div`
    margin-top: 15px;
    input {
        width: 100%;
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #ccc;
    }
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

export const JoinChannelButton = styled.button<{ joined?: boolean }>`
    padding: 10px 15px;
    margin-top: auto;
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

export const ContentArea = styled.main`
    flex: 1;
    padding: 60px 70px 50px 70px;
    background: #f8f9fb;
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
export const Header = styled.div`
    display: flex;
    align-items: baseline;
    gap: 20px;
    margin-bottom: 40px;

    h1 {
        margin: 0;
        font-size: 1.7rem;
    }
`;
export const PostList = styled.div`
    display: flex;
    flex-direction: column;
    gap: 20px;
`;

export const PostItem = styled.div<{ $notice?: boolean }>`
    background: ${({ $notice }) => ($notice ? '#fffbea' : '#ffffff')};
    border-radius: 8px;
    padding: 20px;
    box-sizing: border-box;
    margin-top: -10px;
    h2 {
        margin: 0 0 10px 0;
        font-size: 1.4rem;
        font-weight: 600;
        color: #333;
    }

    p {
        margin: 0;
        font-size: 1rem;
        color: #555;
        line-height: 1.6;
    }
`;
export const Tag = styled.span`
    display: inline-block;
    font-weight: bold;
    color: #2b90d9;
    font-size: 0.85rem;
    margin-bottom: 8px;
`;

export const Divider = styled.div`
    width: 100%;
    height: 1px;
    background-color: #ddd;
    margin: -5px 0;
`;

export const SubMenu = styled.ul`
    list-style: none;
    margin-left: 7px;
    margin-top: 1px;
    margin-bottom: 3px;

    li {
        padding: 4px 0;
        font-size: 0.9rem;
        color: #555;
        cursor: pointer;

        &:hover {
            color: #2b90d9;
        }
    }
`;
export const CommentItem = styled.div`
    background: #f4f6f8;
    border-radius: 8px;
    padding: 10px 14px;
    margin-bottom: 8px;

    strong {
        display: block;
        margin-bottom: 4px;
        color: #2b90d9;
    }

    p {
        margin: 0;
        color: #555;
        font-size: 0.95rem;
        line-height: 1.4;
    }
`;
export const LikeButton = styled.button<{ liked?: boolean }>`
    display: flex;
    align-items: center;
    gap: 6px;
    padding: 6px 12px;
    background-color: ${({ liked }) => (liked ? '#36acecc7' : '#f0f0f0')};
    color: ${({ liked }) => (liked ? '#fff' : '#555')};
    border: none;
    border-radius: 20px;
    cursor: pointer;
    font-weight: bold;
    font-size: 14px;
    transition: all 0.2s;

    &:hover {
        background-color: ${({ liked }) => (liked ? '#2678b5' : '#e0e0e0')};
    }
`;

export const CommentFormContainer = styled.div`
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    gap: 10px;

    input, textarea {
    background: #f4f6f8;
    width: 100%;
    padding: 10px;
    border-radius: 6px;
    border: 1px solid #ffff;
    font-size: 1rem;
    font-family: inherit;

    &:focus {
        outline: none;
        border-color: #2b90d9; 
        box-shadow: 0 0 3px #2b90d9; 
    }
}


    button {
        width: 120px;
        align-self: flex-end;
        padding: 10px;
        background-color: #5db9ee;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
        transition: background-color 0.2s ease;

        &:hover {
        background-color: #2b90d9;
        }
    }
    `;
export const PostDivider = styled.hr`
    border: none;
    border-top: 1px solid #ddd;
    margin: 20px 0;
`;
// export const CommentSection = styled.div`
//     background: #f4f6f8;
//     border-radius: 8px;
//     padding: 15px;
//     margin-top: 20px;
// `;
export const CommentSection = styled.div`
    background: #ffffff;
    border-radius: 8px;
    padding: 15px;
    margin-top: 20px;
    max-height: 314px; 
    overflow-y: auto;
`;


export const SectionTitle = styled.h3`
    margin: 0 0 10px 0;
    font-size: 1.1rem;
    color: #333;
`;

export const PostAuthor = styled.h2`
    display: flex;
    align-items: center;
    font-size: 1.25rem;
    margin: 0;

    span {
        font-weight: normal;
        color: #888;
        font-size: 0.85rem;
        margin-left: 10px;
        margin-bottom: 1px;
    }
    `;
