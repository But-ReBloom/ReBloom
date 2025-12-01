import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    background: radial-gradient(circle at 12% 75%, #6dc9ffe3 0%, #ffffff 55%);
    min-height: 100vh;
    position: relative;
`;

export const LogoImage = styled.img`
    width: 160px;
    cursor: pointer;
`;

export const CloseButton = styled.button`
    position: absolute;
    top: 25px;
    right: 35px;
    background: none;
    border: none;
    cursor: pointer;
`;

export const CloseIconImg = styled.img`
    width: 34px;
    height: 34px;
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

export const PostEditorContainer = styled.div`
    width: 1000px;
    margin: 60px auto;
    padding: 30px;
    background: rgba(255, 255, 255, 0.9);
    border-radius: 12px;
    box-shadow: 0 3px 10px rgba(0,0,0,0.07);
    display: flex;
    flex-direction: column;
    gap: 15px;

    h2 {
        margin-left: 30px;
        font-size: 30px;
        font-weight: 600;
    }

    input,
    textarea {
        padding: 12px;
        border-radius: 6px;
        border: 1px solid #ccc;
        background: #fff;
        font-size: 1rem;
        transition: all 0.2s ease;

        &:focus {
            outline: none;
            border-color: #2b90d9;
            box-shadow: 0 0 3px #2b90d9;
        }
    }

    button {
        padding: 10px 20px;
        background-color: #5db9ee;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
        transition: 0.2s;

        &:hover {
            background-color: #2b90d9;
        }
    }
`;

export const CategorySelectWrapper = styled.div`
    display: flex;
    width: 100%;
    justify-content: flex-end;
    margin-top: 25px;
    margin-bottom: -70px;
    position: relative;
`;

export const CategorySelect = styled.select`
    width: 180px;
    padding: 10px 40px 10px 12px;
    border-radius: 6px;
    border: 1px solid #ccc;
    background: #fff;
    font-size: 1rem;
    cursor: pointer;
    appearance: none;
    -webkit-appearance: none;
    -moz-appearance: none;

    &:focus {
        outline: none;
        border-color: #2b90d9;
        box-shadow: 0 0 4px #2b90d9;
    }
`;

export const CategoryArrow = styled.div`
    position: absolute;
    right: 14px;
    top: 50%;
    transform: translateY(-50%);
    pointer-events: none;
    width: 14px;
    height: 14px;
    background-image: url("data:image/svg+xml;utf8,<svg fill='gray' height='24' width='24' viewBox='0 0 24 24' xmlns='http://www.w3.org/2000/svg'><path d='M7 10l5 5 5-5z'/></svg>");
    background-repeat: no-repeat;
    background-position: center;
    background-size: 14px;
`;
