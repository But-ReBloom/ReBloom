import * as S from "./style.ts";
import Header from "../../components/normal_header/nh.tsx";

export default function TestResult(props) {
  return (
    <S.Background>
      <Header />
      <S.Wrrapper>
        {props.social}
        {props.creativity}
      </S.Wrrapper>
    </S.Background>
  );
}
