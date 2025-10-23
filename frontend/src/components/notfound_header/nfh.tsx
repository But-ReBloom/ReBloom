import logo from '../../assets/images/Rebloom-logo.svg';
import { Link } from "react-router-dom";
import * as S from './style.ts';

function Header() {
    //메인 페이지_헤더
    return (
        <>
            <S.HeaderContainer>
                <Link to="/"><S.Logo_svg src={logo}/></Link>
            </S.HeaderContainer>
        </>
    );
}

export default Header