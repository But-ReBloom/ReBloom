    import styled from 'styled-components';

    export const CommunityWrapper = styled.div`
    background: radial-gradient(circle at 12% 75%, #6dc9ffe3 0%, #6dc9ff45 55%);
    width: 100%;
    height: 100vh;
    `;

    export const LogoImage = styled.img`
    position: absolute;
    top: 17px;
    left: 36px;
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

    export const CentralBox = styled.div`
    position: absolute;
    top: 52%;
    left: 50%;
    transform: translate(-50%, -50%);
    width: 900px;
    max-height: 650px;
    background: rgba(255, 255, 255, 0.85);
    border-radius: 16px;
    padding: 40px;
    overflow-y: auto;
    `;

    export const HeaderTop = styled.div`
    margin-bottom: 20px;
    margin-top: -10px;
    h2 {
        font-size: 24px;
        font-weight: 700;
    }
    `;

    export const FormWrapper = styled.div`
    display: flex;
    flex-direction: column;
    gap: 14px;
    `;

    export const Input = styled.input`
    padding: 12px 16px;
    border-radius: 10px;
    border: 1px solid #ccc;
    font-size: 14px;
    `;

    export const TextArea = styled.textarea`
    padding: 14px 16px;
    height: 100px;
    border-radius: 10px;
    border: 1px solid #ccc;
    font-size: 14px;
    resize: none;
    `;

    export const SubmitButton = styled.button`
    margin-top: 20px;
    padding: 14px;
    border-radius: 30px;
    border: none;
    background: radial-gradient(circle at 12% 75%, #41a6ff 0%, #3e55bf 55%);
    color: white;
    font-size: 15px;
    font-weight: 600;
    cursor: pointer;
    &:hover {
        background: radial-gradient(circle at 12% 75%, #4fb8ff 0%, #3e55bf 55%);
    }
    `;

    /* 취미 선택 UI */
    export const HobbySelectorWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    margin-top: 10px;
    `;

    export const HobbyOption = styled.div<{ selected: boolean }>`
    padding: 8px 16px;
    border-radius: 20px;
    border: 1px solid ${({ selected }) => (selected ? '#3e55bf' : '#ccc')};
    background-color: ${({ selected }) => (selected ? '#3e55bf' : 'white')};
    color: ${({ selected }) => (selected ? 'white' : '#333')};
    font-size: 14px;
    cursor: pointer;
    transition: 0.2s;
    &:hover {
        border-color: #41a6ff;
    }
    `;

    export const CustomHobbyInputWrapper = styled.div`
    display: flex;
    gap: 6px;
    margin-top: 10px;
    input {
        flex: 1;
        padding: 10px 12px;
        border-radius: 10px;
        border: 1px solid #ccc;
        font-size: 14px;
    }
    button {
        padding: 10px 16px;
        border-radius: 20px;
        border: none;
        background-color: #3e55bf;
        color: white;
        cursor: pointer;
        font-weight: 600;
        &:hover {
        background-color: #41a6ff;
        }
    }
    `;

    export const StatusMessage = styled.p`
    margin-top: 12px;
    color: red;
    font-size: 14px;
    `;

