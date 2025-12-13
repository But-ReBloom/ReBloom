import { useEffect, useState } from "react";
import * as S from "./style";
import Header from "../../components/mainpage-Header/mph";
import Point from "../../assets/images/Point.svg";
import Archive from "../../assets/images/Archive.svg";

/* ===============================
   타입 정의
================================ */
type CategoryKey = "사회" | "창의성" | "배움" | "건강";

type Quest = {
  title: string;
  description: string;
  current: number;
  goal: number;
};

type Achievement = {
  title: string;
  description: string;
  progress: number; // 0~100
  quests: Quest[];
};

type BubbleState = {
  id: string;
  achievement: Achievement;
  x: number;
  y: number;
};

/* ===============================
   유틸
================================ */
const AREA_WIDTH = 420;
const AREA_HEIGHT = 440;
const MIN_DIST = 130;

const getBubbleStyle = (progress: number) => {
  if (progress === 100) {
    return {
      background: "linear-gradient(90deg, #41a6ff, #3e55bf)",
      color: "#fff",
    };
  }
  if (progress < 25) return { background: "#cfefff", color: "#333" };
  if (progress < 40) return { background: "#ffd6d6", color: "#333" };
  if (progress < 60) return { background: "#d7f5df", color: "#333" };
  if (progress < 80) return { background: "#e6dcff", color: "#333" };
  return { background: "#ffe7c7", color: "#333" };
};

/* ===============================
   메인 컴포넌트
================================ */
export default function Mypage() {
  /* ---------- 더미 데이터 ---------- */
  const data = {
    userName: "오용준",
    userTier: "silver",
    userPoint: 1200,
  
    userArchive: {
      사회: [
        {
          title: "소통왕",
          description: "댓글과 피드백으로 활발히 소통",
          progress: 80,
          quests: [
            { title: "댓글 작성", description: "댓글 10회 작성", current: 8, goal: 10 },
            { title: "피드백 남기기", description: "피드백 5회", current: 5, goal: 5 },
          ],
        },
        {
          title: "기부천사",
          description: "사회 기여 활동 참여",
          progress: 45,
          quests: [
            { title: "기부 참여", description: "기부 1회 이상", current: 1, goal: 1 },
            { title: "캠페인 공유", description: "공유 5회", current: 2, goal: 5 },
          ],
        },
        {
          title: "커뮤니티리더",
          description: "커뮤니티를 주도적으로 이끎",
          progress: 100,
          quests: [
            { title: "공지 작성", description: "공지글 작성", current: 1, goal: 1 },
            { title: "유저 도움", description: "신규 유저 5명 도움", current: 5, goal: 5 },
          ],
        },{
          title: "응원대장",
          description: "다른 유저를 적극적으로 응원",
          progress: 30,
          quests: [
            { title: "응원 남기기", description: "응원 10회", current: 3, goal: 10 },
            { title: "응원 받은 글", description: "응원 받은 글 3개", current: 1, goal: 3 },
          ],
        },
        {
          title: "참여왕",
          description: "사회 활동에 꾸준히 참여",
          progress: 60,
          quests: [
            { title: "이벤트 참여", description: "이벤트 5회", current: 4, goal: 5 },
            { title: "설문 참여", description: "설문 3회", current: 3, goal: 3 },
          ],
        },        
      ],
  
      창의성: [
        {
          title: "사진작가",
          description: "창의적인 사진 공유",
          progress: 70,
          quests: [
            { title: "사진 업로드", description: "사진 5장 업로드", current: 4, goal: 5 },
            { title: "좋아요 받기", description: "좋아요 30회", current: 18, goal: 30 },
          ],
        },
        {
          title: "아이디어뱅크",
          description: "독창적 아이디어 제안",
          progress: 55,
          quests: [
            { title: "아이디어 등록", description: "아이디어 3건", current: 2, goal: 3 },
            { title: "채택됨", description: "아이디어 채택 1회", current: 1, goal: 1 },
          ],
        },
        {
          title: "콘셉트메이커",
          description: "콘텐츠 콘셉트 기획",
          progress: 35,
          quests: [
            { title: "콘셉트 제안", description: "콘셉트 5회 제안", current: 2, goal: 5 },
            { title: "승인 받기", description: "콘셉트 승인 1회", current: 0, goal: 1 },
          ],
        },{
          title: "콘셉트메이커",
          description: "콘텐츠 콘셉트 기획",
          progress: 40,
          quests: [
            { title: "콘셉트 제안", description: "콘셉트 5회 제안", current: 2, goal: 5 },
            { title: "채택", description: "콘셉트 채택 1회", current: 0, goal: 1 },
          ],
        },
        {
          title: "비주얼아티스트",
          description: "시각적 완성도 높은 콘텐츠 제작",
          progress: 90,
          quests: [
            { title: "디자인 업로드", description: "디자인 3회", current: 3, goal: 3 },
            { title: "좋아요", description: "좋아요 50회", current: 45, goal: 50 },
          ],
        },        
      ],
  
      배움: [
        {
          title: "성실학습자",
          description: "꾸준한 학습 기록",
          progress: 100,
          quests: [
            { title: "연속 학습", description: "7일 연속 학습", current: 7, goal: 7 },
            { title: "노트 작성", description: "노트 10개 작성", current: 10, goal: 10 },
          ],
        },
        {
          title: "탐구자",
          description: "새로운 지식 탐구",
          progress: 85,
          quests: [
            { title: "자료 탐색", description: "자료 10개 탐색", current: 9, goal: 10 },
            { title: "정리 글", description: "정리 글 3개 작성", current: 3, goal: 3 },
          ],
        },
        {
          title: "문제해결사",
          description: "학습 중 문제 해결",
          progress: 60,
          quests: [
            { title: "문제 해결", description: "문제 5개 해결", current: 3, goal: 5 },
            { title: "해설 작성", description: "해설 2개 작성", current: 2, goal: 2 },
          ],
        },{
          title: "탐구자",
          description: "새로운 지식 탐색",
          progress: 85,
          quests: [
            { title: "자료 탐색", description: "자료 10개 탐색", current: 9, goal: 10 },
            { title: "정리 노트", description: "정리 노트 3개", current: 3, goal: 3 },
          ],
        },
        {
          title: "문제해결사",
          description: "학습 중 문제 해결 능력",
          progress: 60,
          quests: [
            { title: "문제 해결", description: "문제 5개 해결", current: 3, goal: 5 },
            { title: "해설 작성", description: "해설 2회", current: 2, goal: 2 },
          ],
        },        
      ],
  
      건강: [
        {
          title: "루틴마스터",
          description: "건강 루틴 유지",
          progress: 80,
          quests: [
            { title: "운동 기록", description: "운동 10회", current: 6, goal: 10 },
            { title: "스트레칭", description: "스트레칭 5회", current: 5, goal: 5 },
          ],
        },
        {
          title: "얼리버드",
          description: "아침 활동 우수",
          progress: 50,
          quests: [
            { title: "기상 체크", description: "조기 기상 5회", current: 3, goal: 5 },
            { title: "아침 산책", description: "산책 3회", current: 2, goal: 3 },
          ],
        },
        {
          title: "체력왕",
          description: "체력 지표 우수",
          progress: 100,
          quests: [
            { title: "고강도 운동", description: "고강도 운동 5회", current: 5, goal: 5 },
            { title: "체력 테스트", description: "체력 테스트 통과", current: 1, goal: 1 },
          ],
        },{
          title: "얼리버드",
          description: "아침 생활 습관 유지",
          progress: 50,
          quests: [
            { title: "조기 기상", description: "7시 이전 기상 5회", current: 3, goal: 5 },
            { title: "아침 스트레칭", description: "스트레칭 5회", current: 2, goal: 5 },
          ],
        },
        {
          title: "체력왕",
          description: "체력 지표 우수",
          progress: 100,
          quests: [
            { title: "고강도 운동", description: "고강도 운동 5회", current: 5, goal: 5 },
            { title: "체력 테스트", description: "체력 테스트 통과", current: 1, goal: 1 },
          ],
        },        
      ],
    } as Record<CategoryKey, Achievement[]>,
  };
  

  /* ---------- 상태 ---------- */
  const categories = Object.keys(data.userArchive) as CategoryKey[];
  const [selectedCategory, setSelectedCategory] = useState<CategoryKey>("사회");
  const [selectedAchievement, setSelectedAchievement] =
    useState<Achievement | null>(null);
  const [bubbles, setBubbles] = useState<BubbleState[]>([]);

  /* ===============================
     Bubble 생성 (겹침 방지)
================================ */
  useEffect(() => {
    const achievements = data.userArchive[selectedCategory];
    const created: BubbleState[] = [];

    achievements.forEach((a, index) => {
      let x = 0;
      let y = 0;
      let valid = false;

      while (!valid) {
        valid = true;
        x = Math.random() * AREA_WIDTH;
        y = Math.random() * AREA_HEIGHT;

        for (const b of created) {
          const dx = b.x - x;
          const dy = b.y - y;
          if (Math.sqrt(dx * dx + dy * dy) < MIN_DIST) {
            valid = false;
            break;
          }
        }
      }

      created.push({
        id: `${selectedCategory}-${index}`,
        achievement: a,
        x,
        y,
      });
    });

    setBubbles(created);
    setSelectedAchievement(null);
  }, [selectedCategory]);

  return (
    <>
      <Header />

      <S.Background>
        <S.Wrapper>
          <S.Container>
            {/* ================= 왼쪽 ================= */}
            <S.LeftSection>
              <S.UserInfoSection>
                <S.ProfileInfo>
                  <S.UserImage />
                  <div
                    style={{
                      display: "flex",
                      flexDirection: "column",
                      gap: 12,
                    }}
                  >
                    <S.UserName>{data.userName}</S.UserName>
                    <S.UserTier>{data.userTier}</S.UserTier>
                  </div>
                </S.ProfileInfo>

                <S.PointArchive>
                  <S.PnA>
                    <S.addedimage src={Point} />
                    {data.userPoint}
                  </S.PnA>
                  <S.PnA>
                    <S.addedimage src={Archive} />
                    {Object.values(data.userArchive).flat().length}
                  </S.PnA>
                </S.PointArchive>

                <S.ArchiveMent>획득한 업적들</S.ArchiveMent>

                {categories.map((c) => (
                  <S.Box
                    key={c}
                    onClick={() => setSelectedCategory(c)}
                    style={{
                      fontWeight: selectedCategory === c ? 700 : 400,
                    }}
                  >
                    {c}
                  </S.Box>
                ))}
              </S.UserInfoSection>
            </S.LeftSection>

            {/* ================= 오른쪽 ================= */}
            <S.RightSection>
              {selectedAchievement ? (
                <>
                  <S.DetailTitle>{selectedAchievement.title}</S.DetailTitle>
                  <S.DetailDescription>
                    {selectedAchievement.description}
                  </S.DetailDescription>

                  {/* 업적 진행도 */}
                  <S.ProgressTitle>
                    <span>진행도</span>
                    <span>{selectedAchievement.progress}%</span>
                  </S.ProgressTitle>

                  <S.ProgressBar>
                    <S.ProgressFill progress={selectedAchievement.progress} />
                  </S.ProgressBar>

                  {selectedAchievement.progress === 100 && (
                    <div style={{ marginTop: 12, fontWeight: 600 }}>
                      🎉 완료
                    </div>
                  )}

                  {/* 퀘스트 목록 */}
                  <div style={{ marginTop: 24 }}>
                    {selectedAchievement.quests.map((q, idx) => {
                      const isDone = q.current >= q.goal;
                      const percent = Math.min(
                        Math.round((q.current / q.goal) * 100),
                        100
                      );

                      return (
                        <div key={idx} style={{ marginBottom: 16 }}>
                          <S.ProgressTitle>
                            <span>{q.title}</span>
                            <span>
                              {isDone ? "완료" : `${q.current}/${q.goal}`}
                            </span>
                          </S.ProgressTitle>

                          <S.ProgressBar>
                            <S.ProgressFill progress={percent} />
                          </S.ProgressBar>

                          <div
                            style={{
                              fontSize: 12,
                              color: "#6b7280",
                              marginTop: 4,
                            }}
                          >
                            {q.description}
                          </div>
                        </div>
                      );
                    })}
                  </div>
                </>
              ) : (
                <>
                  {bubbles.map((b) => (
                    <S.Bubble
                      key={b.id}
                      style={{
                        left: b.x,
                        top: b.y,
                        ...getBubbleStyle(b.achievement.progress),
                      }}
                      onClick={() => setSelectedAchievement(b.achievement)}
                    >
                      {b.achievement.title}
                    </S.Bubble>
                  ))}
                </>
              )}
            </S.RightSection>
          </S.Container>
        </S.Wrapper>
      </S.Background>
    </>
  );
}
