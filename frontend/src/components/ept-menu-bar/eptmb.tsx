import { Link } from "react-router-dom";
import * as S from "./style.ts";
import NextPButton from "../nextpage-button/nxt-p-b.tsx";

export default function EPTmenuBar() {
  return (
    <>
      <S.Wrapper>
        <S.Container>
          <S.MenuContent>
            <S.MenuBox>취향 테스트</S.MenuBox>
            <Link to="/expreview">
              <S.MenuBox>활동 리뷰</S.MenuBox>
            </Link>
            <S.MenuBox>취향 초기화</S.MenuBox>
          </S.MenuContent>
        </S.Container>
        <S.IntroduceUs>
          <S.IntroCon>
            <S.Btn>
              <div className="" style={{ transform: "rotate(180deg)" }} >
                <NextPButton />
              </div>
            </S.Btn>
            <S.IntroTexting>
              <h1>우리 팀을 소개합니다!</h1>
              <p>저희는 여러분의 쉼터를 찾아드리는 But 입니다.</p>
              <p>더 나은 서비스를 제공하기 위해 항상 노력하겠습니다.</p>
            </S.IntroTexting>
            <S.Btn>
              <div className="">
                <NextPButton />
              </div>
            </S.Btn>
          </S.IntroCon>
        </S.IntroduceUs>
      </S.Wrapper>
    </>
  );
}
