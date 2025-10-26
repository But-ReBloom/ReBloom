import * as S from "./style.ts"
import Header from "../../components/normal_header/nh.tsx";
import Content from "../../components/myexps-content_box/mep-cb.tsx";
export default function MyExps (){
    
    return (
        <>
        <S.Wrapper>
            <S.Container>
                <Header/> {/*홈화면 이동 헤더*/}
                <Content/> {/*리스트 박스*/}
            </S.Container>
        </S.Wrapper> 
        </>
    );
};