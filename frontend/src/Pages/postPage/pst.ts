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
    h3 {
        margin: 0 0 5px;
        font-size: 1.2rem;
    }
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

    li > div {   // 상위 메뉴 카테고리
        padding: 10px 5px;
        cursor: pointer;
        border-radius: 5px;
        font-size: 0.95rem;
        font-weight: bold;   // ← 상위 메뉴만 볼드
        &:hover {
            background-color: #e6f5ff;
        }
    }
`;


export const SubMenu = styled.ul`
    list-style: none;
    margin-left: 7px;
    margin-bottom: 3px;
    margin-top: 1px;
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
    padding: 50px 60px 40px 60px; 
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

    span {
        color: gray;
        font-size: 0.95rem;
        align-self: flex-start;
        margin-top: 8px;
    }
`;

export const HideNoticeButton = styled.button`
    margin-left: auto;
    background: #6dc9ff;
    color: white;
    border: none;
    padding: 8px 14px;
    border-radius: 6px;
    cursor: pointer;

    &:hover {
        background: #58b3e6;
    }

    align-self: flex-start;
    margin-top: 25px;
    margin-bottom: -20px;
`;

export const PostList = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
`;

export const PostItem = styled.div<{ $notice?: boolean }>`
    background: ${({ $notice }) => ($notice ? '#fffbea' : '#ffffff')};
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 12px 18px;
    display: flex;
    align-items: center;
    gap: 10px;
    cursor: pointer;

    &:hover {
        background: #f3f9ff;
    }
`;

export const Tag = styled.span`
    font-weight: bold;
    color: #2b90d9;
`;

export const LogoImage = styled.img`
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

export const Divider = styled.div`
    width: 100%;
    height: 1px;
    background-color: #ddd;
    margin: -5px 0;
`;

