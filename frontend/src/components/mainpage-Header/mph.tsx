import logo from '../../assets/images/Rebloom-logo.svg';
import { Link } from "react-router-dom";
import * as S from './style.ts';
import Menu_Bar from '../menu-bar/mb.tsx';

function Header() {
    //메인 페이지_헤더
    return (
        <>
            <S.HeaderContainer>
                <Link to="/"><S.Logo_svg src={logo}/></Link>
                <S.HeaderRight>
                        <Menu_Bar/>
                        <div className="go-login-button">
                            <Link to="/login"><button className='login-button-go-lp' id='login-box_button'>Login</button></Link>
                        </div>
                </S.HeaderRight>
            </S.HeaderContainer>
        </>
    );
}

export default Header