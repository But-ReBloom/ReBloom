import * as S from "./style.ts"
import Header from "../../components/notfound_header/nfh.tsx";
import ExpreviewContentBox from "../../components/expreview-content_box/epr-cb.tsx";

// 활동 리뷰 페이지
export default function ExpReviewPage (){
    
    return (
        <>
        <S.Wrapper>
            <S.Container>
                <Header/>{/*홈화면 이동 헤더*/}
                <ExpreviewContentBox/>{/* 인삿말 박스*/}
            </S.Container>
        </S.Wrapper> 
        </>
    );
};