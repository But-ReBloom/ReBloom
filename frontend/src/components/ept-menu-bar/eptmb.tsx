import { Link } from "react-router-dom";
import { useState } from "react";
import * as S from "./style.ts";
import NextPButton from "../nextpage-button/nxt-p-b.tsx";

type ViewMode = "CHANNEL" | "HOBBY";

export default function EPTmenuBar() {
  const hobbyFindAllDummy = {
    data: {
      hobbies: [
        { hobbyId: 1, hobbyName: "독서" },
        { hobbyId: 2, hobbyName: "사진 촬영" },
        { hobbyId: 3, hobbyName: "코딩" },
        { hobbyId: 4, hobbyName: "요리" },
        { hobbyId: 5, hobbyName: "운동" },
        { hobbyId: 6, hobbyName: "게임" },
        { hobbyId: 7, hobbyName: "영화 감상" },
        { hobbyId: 8, hobbyName: "악기 연주" },
      ],
    },
  };

  const channelListDummy = [
    {
      channelId: 1,
      channelTitle: "혼자서도 즐기는 취미 채널",
      channelIntro: "몰입형 취미 중심",
    },
    {
      channelId: 2,
      channelTitle: "액티브 라이프 채널",
      channelIntro: "활동적인 취미 공유",
    },
    {
      channelId: 3,
      channelTitle: "크리에이티브 채널",
      channelIntro: "창작 중심 커뮤니티",
    },
    {
      channelId: 4,
      channelTitle: "음악/영상 채널",
      channelIntro: "창작 및 감상 중심",
    },
    {
      channelId: 5,
      channelTitle: "운동/헬스 채널",
      channelIntro: "건강과 활동 중심",
    },
  ];

  const [mode, setMode] = useState<ViewMode>("CHANNEL");

  const next = () => {
    if (mode === "CHANNEL") setMode("HOBBY");
  };
  const prev = () => {
    if (mode === "HOBBY") setMode("CHANNEL");
  };

  return (
    <S.Wrapper>
      <S.Container />

      <S.IntroduceUs>
        <S.IntroCon>
          {/* 왼쪽 버튼: HOBBY 모드에서만 */}
          {mode === "HOBBY" && (
            <S.Btn onClick={prev} style={{ marginRight: "16px" }}>
              <div style={{ transform: "rotate(180deg)" }}>
                <NextPButton />
              </div>
            </S.Btn>
          )}

          {/* 중앙 콘텐츠 */}
          <S.IntroTexting
            style={{
              display: "grid",
              gridTemplateColumns: "repeat(4, 1fr)",
              gap: "16px",
              justifyContent: "center", // ← 중앙 정렬
              marginLeft: 0, // 좌측 마진 제거
            }}
          >
            {mode === "CHANNEL"
              ? channelListDummy.map((ch) => (
                  <button
                    key={`ch-${ch.channelId}`}
                    style={{
                      padding: "16px",
                      borderRadius: "12px",
                      backgroundColor: "#f0f4f8",
                      display: "flex",
                      flexDirection: "column",
                      gap: "8px",
                      boxShadow: "0 4px 8px rgba(0,0,0,0.1)",
                      border: "none",
                      cursor: "pointer",
                    }}
                  >
                    <h2 style={{ margin: 0, fontSize: "20px", color: "#333" }}>
                      {ch.channelTitle}
                    </h2>
                    <p style={{ margin: 0, color: "#666" }}>
                      {ch.channelIntro}
                    </p>
                  </button>
                ))
              : hobbyFindAllDummy.data.hobbies.map((hb) => (
                  <div
                    key={`hb-${hb.hobbyId}`}
                    style={{
                      padding: "16px",
                      borderRadius: "12px",
                      backgroundColor: "#fff7e6",
                      display: "flex",
                      flexDirection: "column",
                      gap: "6px",
                      boxShadow: "0 3px 6px rgba(0,0,0,0.1)",
                      width: "180px",
                    }}
                  >
                    <h2
                      style={{ margin: 0, fontSize: "20px", color: "#ff7f50" }}
                    >
                      {hb.hobbyName}
                    </h2>
                    <p style={{ margin: 0, color: "#555" }}>추천 활동</p>
                  </div>
                ))}
          </S.IntroTexting>

          {/* 오른쪽 버튼: CHANNEL 모드에서만 표시 */}
          {mode === "CHANNEL" && (
            <S.Btn onClick={next} style={{ marginLeft: "16px" }}>
              <NextPButton />
            </S.Btn>
          )}
        </S.IntroCon>

        {/* 하단 메뉴 */}
        <S.Menu>
          <p
            style={{ fontSize: "28px", fontWeight: 650, marginBottom: "16px" }}
          >
            다양한 콘텐츠!
          </p>
          <S.MenuContent style={{ display: "flex", gap: "24px" }}>
            <Link to="/taste/hobby">
              <S.MenuBox style={{ backgroundColor: "#4a90e2", color: "#fff" }}>
                취향 테스트
              </S.MenuBox>
            </Link>
            <Link to="/expreview">
              <S.MenuBox style={{ backgroundColor: "#50e3c2", color: "#fff" }}>
                활동 리뷰
              </S.MenuBox>
            </Link>
            <S.MenuBox style={{ backgroundColor: "#f5a623", color: "#fff" }}>
              취향 초기화
            </S.MenuBox>
          </S.MenuContent>
        </S.Menu>
      </S.IntroduceUs>
    </S.Wrapper>
  );
}
