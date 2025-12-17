import * as S from "./style";

export default function LoadingPage() {
  return (
    <S.Wrapper>
      <S.Container>
        {/* 한숨 */}
        <S.Sigh />
        {/* 파란 캐릭터 */}
        <S.square
          $width={100}
          $height={236}
          $background="#007BFF"
          style={{ marginRight: 60 }}
        >
          <S.BlueInner>
            <S.WhiteEyes style={{ transform: "scaleY(-1)" }}>
              <S.BlackEyes />
            </S.WhiteEyes>
          </S.BlueInner>
        </S.square>

        <HandAnimation />

        {/* 노란 캐릭터 */}
        <S.square $width={100} $height={330} $background="#FFB800">
          <S.YellowInner>
            <S.WhiteEyes style={{ marginTop: 20 }}>
              <S.BlackEyes />
            </S.WhiteEyes>
          </S.YellowInner>
        </S.square>
      </S.Container>
    </S.Wrapper>
  );
}

const HandAnimation = () => {
  return <S.Hand />;
};

const Sigh = () => {
    return <S.Sigh />;
};
