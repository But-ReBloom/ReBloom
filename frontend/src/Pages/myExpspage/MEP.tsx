import * as S from "./style.ts"
import Header from "../../components/notfound_header/nfh.tsx";
import Content from "../../components/myexps-content_box/mep-cb.tsx";
export default function MyExps (){
    
    return (
        <>
        <S.Wrapper>
            <S.Container>
                <Header/>
                <Content/>
            </S.Container>
        </S.Wrapper> 
        </>
    );
};