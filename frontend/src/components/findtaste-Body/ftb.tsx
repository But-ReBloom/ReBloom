import * as S from "./style.ts";

function FT_Body() {
    return (
    <>
        <S.Center>
            <S.Title_box>
                <S.Title>나만의 취향을 발견할 준비 되셨나요?</S.Title>
                <S.Blue_Line/>
            </S.Title_box>
            <S.subTitle_box>
                <S.subTitle>숨겨진 성향과 스타일을 찾아보세요.</S.subTitle>
                <S.Connect_Button onClick={() => (window.location.href = "/taste/hobby/")}>
                취향테스트 하기 <span className="arrow">{">"}</span>
                </S.Connect_Button>
            </S.subTitle_box>
        </S.Center>
    </>
    );
}

export default FT_Body;
