import Header from "../../components/mainpage-Header/mph.tsx";
import { Body } from "../../components/mainpage-Body/mpb.tsx";
import * as S from "./style.ts";
import { useLocation } from "react-router-dom";
import { toast } from "react-toastify";
import { useEffect } from "react";

function MainPage() {
  const location = useLocation();
  const storedUserId = localStorage.getItem("userId");
  const userId = location.state?.id || storedUserId;

  useEffect(() => {
    if (userId) {
      toast.success(`환영합니다! ${userId}님!`);
    }
  }, [userId]);

  return (
    <S.MainPage_Container>
      <Header state={{ id: userId }} />
      <Body />
    </S.MainPage_Container>
  );
}

export default MainPage;
