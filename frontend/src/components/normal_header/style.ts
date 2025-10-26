import styled from "styled-components";

export const GlobalFont = styled.div`
  @font-face {
    font-family: "PyeojinGothic";
    src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/2504-1@1.0/PyeojinGothic-Bold.woff2")
      format("woff2");
    font-weight: 600;
    font-style: normal;
    font-display: swap;
  }
`;

export const HeaderContainer = styled.div`
  padding: 16px 36px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 80px;
  position: fixed;
  opacity: 1;
`;

export const Logo_svg = styled.img`
  width: 160px;
  margin: 0;
`;

export const Exit_img = styled.img`
  width: 44px;
`;

export const ExitBtn = styled.button`
  background: none;
  border: none;
  cursor: pointer;
  transition: all 0.3s ease;

  &:hover {
    transform: scale(1.2);
  }
`;

/* ========== 오른쪽 영역: 메뉴 + 버튼 ========== */
export const HeaderRight = styled.div`
  display: flex;
  align-items: center;
  gap: 464px;

  .header-menu {
    display: block;
  }

  .header-menu ul {
    display: flex;
    align-items: center;
    flex-direction: row;
    gap: 40px;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .header-menu ul li {
    position: relative;
  }

  .header-menu ul li a {
    font-weight: 500;
    text-decoration: none;
    font-size: 16px;
    color: #333;
    cursor: pointer;
    position: relative;
    transition: all 0.3s ease;
    display: inline-block;
    padding: 4px 2px;
  }

  .header-menu ul li a::after {
    content: "";
    position: absolute;
    left: 0;
    bottom: -6px; /* 글자 밑 여백 */
    width: 0;
    height: 2px;
    background-color: #3e55bf;
    transition: width 0.3s ease;
  }

  .header-menu ul li a:hover {
    color: #3e55bf;
    transform: scale(1.2); /* 너무 크게 하지 않도록 소폭만 */
  }

  .header-menu ul li a:hover::after {
    width: 100%;
  }

  /* ========== 로그인 버튼 그룹 ========== */
  .go-login-button {
    display: flex;
    align-items: center;
    gap: 12px;
  }

  .login-button-go-lp {
    background: linear-gradient(90deg, #41a6ff, #3e55bf);
    color: #ffffff;
    padding: 8px 16px;
    cursor: pointer;
    font-size: 14px;
    transition: background-color 0.3s ease, transform 0.12s ease,
      color 0.12s ease;
    border: 1px solid #dedede;
  }

  /* id 선택자 스타일 (구성에서 id 사용했다면 처리) */
  #login-box_button {
    border-radius: 999px;
    transition: all 0.3s ease;
    display: inline-flex;
    align-items: center;
    justify-content: center;
  }

  #login-box_button:hover {
    background: #ffffff;
    color: #50b9ff;
    transform: scale(1.05);
    border-color: #dedede;
  }

  #login-box_button a {
    text-decoration: none;
    color: inherit;
    display: inline-block;
  }

  @media (max-width: 768px) {
    .header-menu ul {
      gap: 20px;
    }
    .header-menu ul li a {
      font-size: 14px;
    }
    #login-box_button {
      padding: 6px 12px;
    }
  }
`;
