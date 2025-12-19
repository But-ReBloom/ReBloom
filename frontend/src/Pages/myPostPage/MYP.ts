import styled from 'styled-components';

export const Container = styled.div`
    display: flex;
    height: 100vh;
`;

export const Sidebar = styled.div`
    width: 280px;
    background-color: #f8f9fa;
    padding: 20px;
    display: flex;
    flex-direction: column;
    padding-top: 100px;
`;

export const ContentArea = styled.div`
    flex: 1;
    padding: 24px;
    overflow-y: auto;
`;

/* ===== Logo ===== */
export const LogoImage = styled.img`
    position: absolute;
    top: 17px;
    left: 36px;
    width: 160px;
    cursor: pointer;
    transition: transform 0.3s ease;

    &:hover {
        transform: scale(1.1);
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
        border: 0.5px solid #000;
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
        font-size: 1rem;

        &:focus {
            outline: none;
            border-color: #2b90d9;
            box-shadow: 0 0 3px #2b90d9;
        }
    }
`;

export const CategorySelectWrapper = styled.div`
    display: flex;
    justify-content: flex-end;
    margin-top: 25px;
    margin-bottom: -70px;
`;

export const CategorySelect = styled.select`
    width: 180px;
    padding: 10px;
    border-radius: 6px;
    border: 1px solid #ccc;
    font-size: 1rem;
`;

export const ActionButtonGroup = styled.div`
    display: flex;
    gap: 12px;
    justify-content: flex-end;
    margin-top: 10px;
`;

export const ClearButton = styled.button`
    padding: 10px 18px;
    background-color: #ff5c5c;
    color: #ffffffff;
    border: 1.5px solid #ff5c5c;
    border-radius: 8px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s ease;

    &:hover {
        background-color: #ff5c5c;
        color: #fff;
        transform: translateY(-1px);
    }
`;

export const SubmitButton = styled.button<{ disabled?: boolean }>`
    padding: 10px 22px;
    background: linear-gradient(135deg, #5db9ee, #2b90d9);
    color: #fff;
    border: none;
    border-radius: 8px;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.2s ease;
    box-shadow: 0 4px 10px rgba(43, 144, 217, 0.25);

    &:hover {
        background: linear-gradient(135deg, #2b90d9, #1c6fb8);
        transform: translateY(-1px);
        box-shadow: 0 6px 14px rgba(43, 144, 217, 0.35);
    }

    &:disabled {
        background: #ccc;
        cursor: not-allowed;
        box-shadow: none;
        transform: none;
    }
`;

export const Divider = styled.hr`
    border: none;
    border-top: 1px solid #ddd;
    margin: 20px 0;
`;

export const CafeInfo = styled.div`
    padding: 15px 0;
    font-size: 0.9rem;
    color: #666;
`;

export const NavMenu = styled.nav`
    display: flex;
    flex-direction: column;
    gap: 8px;
    margin-top: 20px;
`;

export const SubMenu = styled.div`
    display: flex;
    flex-direction: column;
    gap: 6px;
    padding-left: 15px;
    font-size: 0.9rem;
    color: #555;

    a {
        text-decoration: none;
        color: inherit;
        &:hover {
            color: #2b90d9;
        }
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