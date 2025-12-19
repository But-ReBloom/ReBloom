import { Link, useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import * as S from "./style.ts";
import NextPButton from "../nextpage-button/nxt-p-b.tsx";
import { hobbyApi } from "../../api/hobby";
import { channelApi } from "../../api/channel";
import type { GetHobbyResponse } from "../../types/hobby";
import type { FindOneChannelResponse } from "../../types/channel";
import { toast, ToastContainer } from "react-toastify";
import LoadingPage from "../../Pages/loadingpage/loading.tsx";

type ViewMode = "CHANNEL" | "HOBBY";

interface ChannelDisplay {
  channelId: number;
  channelTitle: string;
  channelIntro: string;
}

interface HobbyDisplay {
  hobbyId: number;
  hobbyName: string;
}

export default function EPTmenuBar() {
  const navigate = useNavigate();
  const [hobbies, setHobbies] = useState<HobbyDisplay[]>([]);
  const [channels, setChannels] = useState<ChannelDisplay[]>([]);
  const [loading, setLoading] = useState(true);
  const [resetting, setResetting] = useState(false);
  const [mode, setMode] = useState<ViewMode>("CHANNEL");

  useEffect(() => {
    const fetchData = async () => {
      try {
        // Fetch hobbies (12개만 표시)
        const hobbyRes = await hobbyApi.findAllHobbies();
        if (hobbyRes.success && hobbyRes.data.hobbies) {
          setHobbies(hobbyRes.data.hobbies.slice(0, 12).map((h: GetHobbyResponse) => ({
            hobbyId: h.hobbyId,
            hobbyName: h.hobbyName,
          })));
        }

        // Fetch approved channels
        const channelRes = await channelApi.getApprovedChannels();
        if (channelRes.success && channelRes.data.responses) {
          setChannels(channelRes.data.responses.map((ch: FindOneChannelResponse) => ({
            channelId: ch.channelId,
            channelTitle: ch.channelName,
            channelIntro: ch.channelIntro,
          })));
        }
      } catch (error) {
        console.error("Failed to fetch data", error);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  const handleResetActivity = async () => {
    if (resetting) return;
    
    const confirmed = window.confirm("정말로 취향을 초기화하시겠습니까?\n모든 활동 기록이 삭제됩니다.");
    if (!confirmed) return;
    
    try {
      setResetting(true);
      const response = await hobbyApi.resetActivity();
      if (response.success) {
        toast.success("취향이 초기화되었습니다!");
        // 취향 테스트 페이지로 이동
        setTimeout(() => {
          navigate("/taste/hobby");
        }, 1500);
      } else {
        toast.error("초기화에 실패했습니다.");
      }
    } catch (error) {
      console.error("Failed to reset activity", error);
      toast.error("초기화 중 오류가 발생했습니다.");
    } finally {
      setResetting(false);
    }
  };

  const next = () => {
    if (mode === "CHANNEL") setMode("HOBBY");
  };
  const prev = () => {
    if (mode === "HOBBY") setMode("CHANNEL");
  };

  if (loading) {
    return (
      <LoadingPage />
    );
  }

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
              ? (channels.length > 0 ? channels : [{ channelId: 0, channelTitle: "채널이 없습니다", channelIntro: "" }]).map((ch) => (
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
              : (hobbies.length > 0 ? hobbies : [{ hobbyId: 0, hobbyName: "취미가 없습니다" }]).map((hb) => (
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
            <S.MenuBox 
              style={{ backgroundColor: "#f5a623", color: "#fff", cursor: resetting ? "not-allowed" : "pointer" }}
              onClick={handleResetActivity}
            >
              {resetting ? "초기화 중..." : "취향 초기화"}
            </S.MenuBox>
          </S.MenuContent>
        </S.Menu>
      </S.IntroduceUs>
      <ToastContainer position="top-right" autoClose={2000} />
    </S.Wrapper>
  );
}
