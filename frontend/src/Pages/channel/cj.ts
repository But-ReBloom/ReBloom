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
    max-height: 650px; /* 최대 높이 지정 */
    background: rgba(255, 255, 255, 0.85);
    border-radius: 16px;
    padding: 40px;
    overflow-y: auto; /* 내용이 넘치면 스크롤 가능 */
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

export const Select = styled.select`
    padding: 12px 16px;
    border-radius: 10px;
    border: 1px solid #ccc;
    font-size: 14px;
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
export const PostDescription = styled.p`
    margin: -10px 0 0 10px;
    font-size: 14px;
    color: #666;
    `;
    export const LeftPostItem = styled.div`
    height: 100px;
    `;

    export const RightPostItem = styled.div`
    min-height: 215px;
    padding: 20px 25px;
    `;

    export const PostTitle = styled.h3`
    margin: 10px 0 20px 8px;
    font-weight: 700;
    font-size: 20px;
    `;
    export const LeftColumn = styled.div`
    flex: 1;
    display: flex;
    flex-direction: column;
    gap: 20px;
    overflow-y: auto;
    margin-top: 30px;
    `;

export const SortDropdown = styled.div`
  select {
    padding: 10px 23px;
    width: 110px;
    border-radius: 60px;
    border: 1px solid #ccc;
    background-color: white;
    font-size: 14px;
    cursor: pointer;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
    appearance: none;
    background-image: url('data:image/svg+xml;utf8,<svg fill="gray" height="12" viewBox="0 0 24 24" width="12" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/></svg>');
    background-repeat: no-repeat;
    background-position: left 70px center;
    background-size: 30px 30px;
  }
`;
export const RightButtons = styled.div`
  display: flex;
  gap: 12px;
`;

export const Button = styled.button`
  padding: 11px 15px 10px 21px;
  width: 140px;
  border-radius: 60px;
  border: 1px solid #ccc;
  background-color: white;
  font-size: 14px;
  cursor: pointer;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
  font-weight: 600;
  color: #333;
  text-align: center;

  &:hover {
    background-color: #f5f5f5;
  }
`;

export const HobbySelectorWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 8px;
`;

export const HobbyOption = styled.button<{ selected?: boolean }>`
  padding: 8px 16px;
  border-radius: 20px;
  border: 1px solid ${({ selected }) => (selected ? '#4a90e2' : '#ccc')};
  background-color: ${({ selected }) => (selected ? '#4a90e2' : 'white')};
  color: ${({ selected }) => (selected ? 'white' : '#333')};
  font-size: 14px;
  cursor: pointer;
  transition: all 0.2s ease;

  &:hover {
    background-color: ${({ selected }) => (selected ? '#3a7bc8' : '#f5f5f5')};
  }
`;

export const StatusMessage = styled.p`
  margin-top: 12px;
  font-size: 14px;
  text-align: center;
  font-weight: 500;
`;