import { Link } from "react-router-dom";
import { useState } from "react";
import * as S from "./style.ts";
import NextPButton from "../nextpage-button/nxt-p-b.tsx";

export default function EPTmenuBar() {
  // 👇 1. 카드에 표시할 내용 배열
  const introData = [
    {
      title: "우리 팀을 소개합니다!",
      desc1: "저희는 여러분의 쉼터를 찾아드리는 But 입니다.",
      desc2: "더 나은 서비스를 제공하기 위해 항상 노력하겠습니다.",
    },
    {
      title: "팀원 구성",
      desc1: "기획자, 디자이너, 개발자로 구성된 팀입니다.",
      desc2: "각자의 전문성을 살려 협업하고 있습니다.",
    },
    {
      title: "우리의 목표",
      desc1: "사람들의 불편함을 해결하는 혁신적인 서비스를 만듭니다.",
      desc2: "끊임없는 실험과 개선을 통해 성장하고 있습니다.",
    },
  ];

  // 👇 2. 현재 보여줄 인덱스 상태
  const [index, setIndex] = useState(0);

  // 👇 3. 화살표 클릭 시 인덱스 이동
  const next = () => {
    setIndex((prev) => (prev + 1) % introData.length);
  };
  const prev = () => {
    setIndex((prev) => (prev === 0 ? introData.length - 1 : prev - 1));
  };

  // 👇 4. 현재 데이터 가져오기
  const { title, desc1, desc2 } = introData[index];

  return (
    <>
      <S.Wrapper>
        <S.Container>
          <S.MenuContent>
            <Link to="/taste/hobby">
              <S.MenuBox>취향 테스트</S.MenuBox>
            </Link>
            <Link to="/expreview">
              <S.MenuBox>활동 리뷰</S.MenuBox>
            </Link>
            <S.MenuBox>취향 초기화</S.MenuBox>
          </S.MenuContent>
        </S.Container>

        {/* 아래부터 카드 부분 */}
        <S.IntroduceUs>
          <S.IntroCon>
            {/* 왼쪽 화살표 */}
            <S.Btn onClick={prev}>
              <div style={{ transform: "rotate(180deg)" }}>
                <NextPButton />
              </div>
            </S.Btn>

            {/* 가운데 텍스트 부분 */}
            <S.IntroTexting>
              <h1>{title}</h1>
              <p>{desc1}</p>
              <p>{desc2}</p>
            </S.IntroTexting>

            {/* 오른쪽 화살표 */}
            <S.Btn onClick={next}>
              <NextPButton />
            </S.Btn>
          </S.IntroCon>
        </S.IntroduceUs>
      </S.Wrapper>
    </>
  );
}
