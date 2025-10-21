import Header from "../../components/notfound_header/nfh.tsx";
import Body from "../../components/notfound_body/nfb.tsx";
import * as S from "./style.ts"

export default function NotFound () {
    return(
        <>
            <S.Notfound_Container>
                <Header/>
                <Body/>
            </S.Notfound_Container>
        </>
    );
};