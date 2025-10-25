import * as S from "./style.ts";
import List from "../myexps-list/mep-l.tsx";

export default function MepCb() {
    return (
        <>
            <S.Wrapper>
                <S.Container>
                    <S.TitleBox>
                        <S.Question>어떤 활동에 대한 리뷰를 남기고 싶으신가요?</S.Question>
                        <S.Line />
                    </S.TitleBox>
                    <List />
                </S.Container>
            </S.Wrapper>
        </>
    );
}
