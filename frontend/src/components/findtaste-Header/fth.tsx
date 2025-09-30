import logo from '../../assets/images/Rebloom-logo.svg';
import { Link } from "react-router-dom";
import * as S from './style.ts';

function FT_Header() {
    return (
        <>
            <S.MainContainer>
                <Link to="/"><S.Logo_svg src={logo}/></Link>
                <S.CloseButton href="./">X</S.CloseButton>      
            </S.MainContainer>
        </>
    );
}

export default FT_Header