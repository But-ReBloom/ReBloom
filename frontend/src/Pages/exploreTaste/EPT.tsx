import * as S from "./style.ts";
import Header from "../../components/mainpage-Header/mph.tsx";
import EPTmenuBar from "../../components/ept-menu-bar/eptmb.tsx";


export default function EPT() {
  return (
    <>
      <S.Wrapper>
        <Header />
        <EPTmenuBar />
      </S.Wrapper>
    </>
  );
}
