import Header from "../../components/mainpage-Header/mph.tsx";
import { Body } from "../../components/mainpage-Body/mpb.tsx";

import * as S from "./style.ts";

function MainPage() {
    return (
        <S.MainPage_Container>
            <Header />
            <Body />
        </S.MainPage_Container>
    );
}

export default MainPage;