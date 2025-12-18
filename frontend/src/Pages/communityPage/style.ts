import styled from 'styled-components';

export const CommunityWrapper = styled.div`
  background: radial-gradient(circle at 12% 75%, #6dc9ffe3 0%, #d7ecf9e3 55%);
  width: 100%;
  height: 100vh;
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

export const CentralBox = styled.div`
  position: absolute;
  top: 53%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 1400px;
  height: 590px;
  background: rgba(250, 250, 250, 0.7);
  border: 1px solid rgba(0, 0, 0, 0.2);
  border-radius: 12px;
  padding: 20px 30px;
  box-sizing: border-box;
`;

export const HeaderTop = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
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

export const ContentWrapper = styled.div`
  display: flex;
  margin-top: 20px;
  height: 460px;
  gap: 30px;
`;

export const LeftColumn = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 20px;
  overflow-y: auto;
  margin-top: 30px;
`;

export const RightColumn = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 30px;
  overflow-y: auto;
`;

export const PostItem = styled.div`
  background: #fff;
  border-radius: 12px;
  padding: 15px 20px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
`;

export const LeftPostItem = styled(PostItem)`
  height: 100px;
`;

export const RightPostItem = styled(PostItem)`
  min-height: 215px;
  padding: 20px 25px;
`;

export const PostTitle = styled.h3`
  margin: 10px 0 20px 8px;
  font-weight: 700;
  font-size: 20px;
`;

export const PostDescription = styled.p`
  margin: -10px 0 0 10px;
  font-size: 14px;
  color: #666;
`;

export const CommentsList = styled.ul`
  display: flex;
  flex-direction: column;
  gap: 12px;   // 👈 요소 간 간격
  margin: 0;
  padding-left: 16px;
  font-size: 14px;
  color: #444;
`;

export const CommentItem = styled.li`
  margin: 0 0 0 10px;
  font-size: 14px;
  color: #333;
  font-weight: 400;
`;
