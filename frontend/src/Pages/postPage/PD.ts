import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    background: #f8f9fb;
    min-height: 100vh;
    position: relative;
`;

export const Sidebar = styled.aside`
    width: 280px;
    background: #ffffffc6;
    border-right: 1px solid #ddd;
    padding: 20px 20px 30px 20px;
    display: flex;
    flex-direction: column;
    gap: 30px;
`;

export const CafeInfo = styled.div`
    p {
        color: #666;
        font-size: 0.9rem;
    }
`;

export const ProfileSection = styled.div`
    display: flex;
    align-items: center;
    gap: 10px;

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

export const SearchBox = styled.div`
    margin-top: -10px;
    input {
        width: 100%;
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #ccc;
    }
`;

export const NavMenu = styled.nav`
    margin-top: -30px;
    ul {
        list-style: none;
        padding: 0;
    }

    li > div {
        padding: 10px 5px;
        cursor: pointer;
        border-radius: 5px;
        font-size: 0.95rem;
        font-weight: bold;

        &:hover {
            background-color: #e6f5ff;
        }
    }
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

export const ContentArea = styled.main`
    flex: 1;
    padding: 60px 70px 50px 70px;
    background: #f8f9fb;
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

export const PostDivider = styled.hr`
    border: none;
    border-top: 1px solid #ddd;
    margin: 20px 0;
`;

export const CommentItem = styled.div`
    background: #ffffff;
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

export const PostCommentBox = styled.div`
    background: #ffffff;
    border-radius: 12px;
    padding: 20px;
    box-shadow: 0 3px 8px rgba(0,0,0,0.05);
    display: flex;
    flex-direction: column;
    gap: 25px;
`;

export const CommentSection = styled.div`
    background: #f4f6f8;
    border-radius: 8px;
    padding: 15px;
`;

export const SectionTitle = styled.h3`
    margin: 0 0 10px 0;
    font-size: 1.1rem;
    color: #333;
`;

export const LogoImage = styled.img`
    width: 160px;
    height: auto;
    cursor: pointer;
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

export const Divider = styled.div`
    width: 100%;
    height: 1px;
    background-color: #ddd;
    margin: -5px 0;
`;

    export const CommentFormContainer = styled.div`
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    gap: 10px;

    input, textarea {
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