import Header from "../../components/mainpage-Header/mph.tsx";
import { Body } from "../../components/mainpage-Body/mpb.tsx";
import * as S from "./style.ts";
import { useLocation } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect, useRef } from "react";

function MainPage() {
  const location = useLocation();
  const storedUserId = localStorage.getItem("userId");
  const userId = location.state?.id || storedUserId;
  const hasShownToast = useRef(false);

  useEffect(() => {
    if (location.state?.id && !hasShownToast.current) {
      hasShownToast.current = true;
      toast.success(`환영합니다! ${location.state.id}님!`);
      window.history.replaceState({}, document.title);
    }
  }, [location.state?.id]);

  return (
    <S.MainPage_Container>
      <Header state={{ id: userId }} />
      <Body />
    </S.MainPage_Container>
  );
}

export default MainPage;
