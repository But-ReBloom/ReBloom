import FT_Body from "../../components/findtaste-Body/ftb.tsx";
// import FT_Header from "../../components/findtaste-Header/fth.tsx";
<<<<<<< HEAD
import Header from "../../components/mainpage-Header/mph.tsx";
=======
import Header from "../../components/normal_header/nh.tsx";
>>>>>>> 240c33b22f560ae63c68a42ea4015ae853a1b962
import * as S from "./style.ts";

function FT_HobbyTest() {
    return (
        <S.Background>
            <title>ReBloom - 취향발견</title>
            <S.header>
                {/* <FT_Header/> */}
                <Header/>
            </S.header>
            <S.MainContainer>
                <FT_Body/>
            </S.MainContainer>
        </S.Background>
    )
}

export default FT_HobbyTest