import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    background: radial-gradient(circle at 12% 75%, #6dc9ffe3 0%, #ffffff 55%);
    min-height: 100vh;
    position: relative;
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

export const Sidebar = styled.aside`
    width: 280px;
    background: #ffffffff;
    border-right: 1px solid #ddd;
    padding: 20px 20px 30px 20px;
    display: flex;
    flex-direction: column;
    gap: 30px;
`;

export const Divider = styled.div`
    width: 100%;
    height: 1px;
    background-color: #ddd;
    margin: -5px 0;
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

/* ================= Write Post Button ================= */
export const WritePostButton = styled.button`
    width: 100%;
    padding: 10px;
    margin: 12px 0;
    margin-top: -10px;
    margin-bottom: -5px;
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

/* ================= Search Box ================= */
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

export const PostEditorContainer = styled.div`
    width: 1000px;
    height: 600px;
    margin: 75px auto; 
    padding: 30px; /* 안쪽 여백 조절 */
    display: flex;
    flex-direction: column;
    gap: 15px;

    background: rgba(250, 250, 250, 0.7);
    border: 1px solid rgba(0, 0, 0, 0.2);
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);

    h2 {
        margin-bottom: 22px;
        margin-left: 20px;
        font-size: 30px;
    }

    input {
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #ccc;
        font-size: 1rem;
        background-color: #fff;
    }

    textarea {
        padding: 10px;
        border-radius: 6px;
        border: 1px solid #ccc;
        font-size: 1rem;
        resize: vertical;
        background-color: #fff;
    }

    button {
        align-self: flex-end;
        
        padding: 10px 20px;
        background-color: #5db9eeff;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;

        &:hover {
            background-color: #5db9eeac;
        }
    }
`;
