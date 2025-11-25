import { useState } from "react";
import Header from "../../components/normal_header/nh.tsx";
import FT_HobbyTest_Description_Box from "../../components/ftdescription-box/ft-d.tsx";
import * as S from "./style.ts";
// import { ToastContainer, toast } from "react-toastify";

function FT_HobbyTest_Description() {
  const [selectedValue, setSelectedValue] = useState<number | null>(null);

  const handleNext = () => {
    // if (selectedValue === null) {
    //   toast.warning("먼저 선택해주세요!", {
    //     position: "bottom-center",
    //     autoClose: 2000,
    //   });
    //   return;
    // }
    window.location.href = "/taste/hobby/test";
  };

  return (
    <S.Background>
      <S.Header>
        <Header />
      </S.Header>
      <S.MainContainer>
        <S.RowContainer>
          <div>
            <FT_HobbyTest_Description_Box
              onSelect={(val) => setSelectedValue(val)}
            />
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
                  있으며, <br /> 솔직하게 답변하실수록 결과가 정확해집니다.
                </S.Text>
              </S.BottomTextBox>
            </S.TextBox>
          </div>
          <S.StartConnect_Button onClick={handleNext}>
            다음 <span className="arrow">{""}</span>
          </S.StartConnect_Button>
        </S.RowContainer>
      </S.MainContainer>
    </S.Background>
  );
}

export default FT_HobbyTest_Description;
