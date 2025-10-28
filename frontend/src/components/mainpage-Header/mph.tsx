import logo from '../../assets/images/Rebloom-logo.svg';
import { Link, useNavigate } from 'react-router-dom'
import * as S from './style.ts';

function Header() {

    const navigate = useNavigate();
    const goto_ftdp = () => {
        navigate('/taste');
    };

    navigate('/taste')

    return (
        <>
            <S.HeaderContainer>
                <Link to="/"><S.Logo_svg src={logo}/></Link>
                <S.HeaderRight>
                        <div className='header-menu'>
                            <ul>
                                <li><a href="" onClick={goto_ftdp}>취향 발견</a></li>
                                <li><a href="">취향 탐색</a></li>
                                <li><a href="">커뮤니티</a></li>
                                <li><a href="">마이페이지</a></li>
                            </ul>
                        </div>
                        <div className="go-login-button">
                            <Link to="/login"><button className='login-button-go-lp' id='login-box_button'>Login</button></Link>
                        </div>
                </S.HeaderRight>
            </S.HeaderContainer>
        </>
    );
}

export default Header