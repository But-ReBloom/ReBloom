import styled from 'styled-components';

export const CommunityWrapper = styled.div`
    background: radial-gradient(circle at 20% 80%, #6dc9ffe3 0%, #6dc9ff30 60%);
    width: 100%;
    height: 100vh;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
`;

export const LogoImage = styled.img`
    position: absolute;
    top: 20px;
    left: 36px;
    width: 160px;
    transition: transform 0.2s;
    &:hover {
        transform: scale(1.05);
    }
`;

export const CloseButton = styled.button`
    position: absolute;
    top: 25px;
    right: 35px;
    background: none;
    border: none;
    cursor: pointer;
    transition: transform 0.2s;
    &:hover {
        transform: rotate(90deg);
    }
`;

export const CloseIconImg = styled.img`
    width: 34px;
    height: 34px;
`;

export const CentralBox = styled.div`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 900px;
    max-height: 650px;
    background: rgba(255, 255, 255, 0.95);
    border-radius: 20px;
    padding: 40px;
    overflow-y: auto;
    box-shadow: 0 10px 25px rgba(0,0,0,0.15);

    /* 스크롤바 스타일 */
    &::-webkit-scrollbar {
        width: 8px;
    }
    &::-webkit-scrollbar-thumb {
        background-color: rgba(100,100,100,0.3);
        border-radius: 4px;
    }
    &::-webkit-scrollbar-track {
        background: transparent;
    }
`;

export const HeaderTop = styled.div`
    margin-bottom: 25px;
    h2 {
        font-size: 28px;
        font-weight: 700;
        color: #333;
    }
`;

export const Input = styled.input`
    padding: 14px 18px;
    border-radius: 12px;
    border: 1px solid #ccc;
    font-size: 15px;
    font-weight: 500;
    background-color: #f9f9f9;
    &:focus {
        border-color: #41a6ff;
        outline: none;
        box-shadow: 0 0 8px rgba(65, 166, 255, 0.3);
    }
`;

export const TextArea = styled.textarea`
    padding: 16px 18px;
    height: 120px;
    border-radius: 12px;
    border: 1px solid #ccc;
    font-size: 14px;
    background-color: #fafafa;
    resize: none;
    &:focus {
        border-color: #41a6ff;
        outline: none;
        box-shadow: 0 0 8px rgba(65, 166, 255, 0.3);
    }
`;

export const SubmitButton = styled.button<{ status?: 'approve' | 'reject' }>` 
    margin-top: 10px;
    padding: 12px 24px;
    border-radius: 30px;
    border: none;
    font-size: 15px;
    font-weight: 600;
    cursor: pointer;
    color: white;
    background: ${({ status }) =>
        status === 'approve'
            ? 'linear-gradient(135deg, #4caf50, #2e7d32)'
            : status === 'reject'
            ? 'linear-gradient(135deg, #f44336, #c62828)'
            : 'radial-gradient(circle at 12% 75%, #41a6ff 0%, #3e55bf 55%)'};
    transition: transform 0.2s, opacity 0.2s;
    &:hover {
        transform: translateY(-2px);
        opacity: 0.9;
    }
`;
