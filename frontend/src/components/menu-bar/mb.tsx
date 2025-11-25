import { Link } from "react-router-dom";

function Menu_Bar() {
  return (
    <>
      <div className="header-menu">
        <ul>
          <li>
            <Link to="/explore/taste">취향 탐색</Link>
          </li>
          <li>
            <Link to="">커뮤니티</Link>
          </li>
          <li>
            <Link to="/mypage">마이페이지</Link>
          </li>
        </ul>
      </div>
    </>
  );
}

export default Menu_Bar;
