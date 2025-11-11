import FT_Header from "../../components/findtaste-Header/fth.tsx";
import FT_HobbyTest_Description_Box from "../../components/ftdescription-box/ft-d.tsx";
import * as S from "./style.ts";

function FT_HobbyTest_Description() {
  return (
    <S.Background>
      <title>ReBloom - 취향발견(설명)</title>
      <S.Header>
        <FT_Header />
      </S.Header>
      <S.MainContainer>
        <S.RowContainer>
          <FT_HobbyTest_Description_Box />
          <S.TextBox>
            <S.TopTextBox>
              <S.Text>
                각 문항을 읽고, 본인의 생각과 가장 가까운 정도에 해당하는
                동그라미를 선택해주세요.
              </S.Text>
            </S.TopTextBox>
            <S.BottomTextBox>
              <S.Text>
                선택지는 ‘동의함’부터 ‘동의하지 않음’까지 단계별로 구성되어
                있으며, <br /> 솔직하게 답변할수록 결과가 정확해집니다.
              </S.Text>
            </S.BottomTextBox>
          </S.TextBox>
          <S.StartConnect_Button
            onClick={() => (window.location.href = "/taste/hobby/test")}
          >
            다음 <span className="arrow">{""}</span>
          </S.StartConnect_Button>
        </S.RowContainer>
      </S.MainContainer>
    </S.Background>
  );
}
export default FT_HobbyTest_Description;
