import * as S from "./style.ts";
import Page from "../../assets/images/page.svg";
import Clock from "../../assets/images/mi_clock.svg";
import Tag from "../../assets/images/Tag.svg";
import Submitimg from "../../assets/images/submitinfo.svg";

export default function OneExpInfo() {
  let now = new Date();
  let year = now.getFullYear();
  let month = now.getMonth() + 1;
  let date = now.getDate();

  let formattedDate = `${year}년 ${month < 10 ? "0" + month : month}월 ${
    date < 10 ? "0" + date : date
  }일`;
  return (
    <>
      <S.Wrraper>
        <S.Container>
          <S.Locate>
            <S.ExpInfo>
              <img
                src={Page}
                alt="페이지 아이콘"
                style={{ width: "20px", height: "20px" }}
              />
              활동명 예시
            </S.ExpInfo>
            <S.ExpInfo>
              <img
                src={Clock}
                alt="활동 날짜"
                style={{ width: "20px", height: "20px" }}
              />
              {formattedDate}
            </S.ExpInfo>
          </S.Locate>
          <S.Locate>
            <S.ExpInfo>
              <img
                src={Tag}
                alt="태그"
                style={{ width: "20px", height: "20px" }}
              />
              관련 태그
            </S.ExpInfo>
            <S.ExpInfo>
              <img
                src={Submitimg}
                alt="제출 여부"
                style={{ width: "24px", height: "24px" }}
              />
              제출
            </S.ExpInfo>
          </S.Locate>
        </S.Container>
      </S.Wrraper>
    </>
  );
}
