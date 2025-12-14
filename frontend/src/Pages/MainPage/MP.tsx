import Header from "../../components/mainpage-Header/mph.tsx";
import { Body } from "../../components/mainpage-Body/mpb.tsx";
import * as S from "./style.ts";
import { useLocation } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect } from "react";

function MainPage() {
  const location = useLocation();
  const storedUserEmail = localStorage.getItem("userEmail");
  const userId = location.state?.id || storedUserEmail;

  useEffect(() => {
    if (location.state?.id) {
      toast.success(`환영합니다! ${location.state.id}님!`);
    }
  }, [location.state]);

  //메인페이지
  return (
    <S.MainPage_Container>
      <Header props={{ state: { id: userId } }} />
      <Body />
    </S.MainPage_Container>
  );
}

export default MainPage;
