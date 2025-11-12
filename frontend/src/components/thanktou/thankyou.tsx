import * as S from "./style.ts";
import Header from "../normal_header/nh.tsx";
import { useLocation, useNavigate } from "react-router-dom";
import NotFound from "../../Pages/404page/NF.tsx";
import Hihand from "../../assets/images/HelloHand.svg";

export default function Thankyou() {
  const location = useLocation();
  const { state } = location; // navigate에서 보낸 state
  const type = state?.type;
  const message = state?.message;

  return (
    <>
      {type === "ExpsReview" ? (
        <ExpsThank message={message} value={"소중한 리뷰"}/>
      ) : type === "HobbyTest" ? (
        <ExpsThank message={message} value={"테스트에 응해주셔서"}/>
      ) : (
        <NotFound/>
      )}
    </>
  );
}

function ExpsThank(props) {
  const navigate = useNavigate();
  console.log(props.message);
  return (
    <>
      <S.Wrapper>
        <Header />
        <S.Container>
          <img src={Hihand} alt="" style={{width : "80px", marginBottom : "40px"}}/>
          <S.Title>수고하셨습니다.</S.Title>
          <S.SubTitle>
            {props.value} 감사합니다. 다음에 또 이용해주세요.
          </S.SubTitle>
          <S.HomeButton
            onClick={() => {
              navigate("/");
            }}
          >
            나가기
          </S.HomeButton>
        </S.Container>
      </S.Wrapper>
    </>
  );
}
