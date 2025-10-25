import * as S from "./style.ts"
import Header from "../../components/notfound_header/nfh.tsx";
import ExpreviewContentBox from "../../components/expreview-content_box/epr-cb.tsx";

export default function ExpReviewPage (){
    
    return (
        <>
        <S.Wrapper>
            <S.Container>
                <Header/>
                <ExpreviewContentBox/>
            </S.Container>
        </S.Wrapper> 
        </>
    );
};