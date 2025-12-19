import { useParams, useLocation, useNavigate } from 'react-router-dom';
import { useState, useEffect, useRef } from 'react';
import {
    Container,
    LogoImage,
    Sidebar,
    CloseButton,
    CloseIconImg,
    ContentArea,
    BackButton,
    ChannelTitle,
    ChannelIntro,
    ChannelDescription,
    HobbyTag,
    JoinChannelButton,
    ProfileSection,
    WritePostButton,
    LikeButton,
    PostAuthor,
    CommentSection,
    // SectionTitle,
    CommentFormContainer,
    CommentItem,
    PostList,
    PostItem,
} from './PDP';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import React_svg from '../../assets/images/react.svg';

import { authApi } from '../../api/auth';
import type { FindUserInfoResponse } from '../../types/auth';

interface Channel {
    channelId: number;
    channelName: string;
    channelIntro: string;
    description?: string;
    hobbies?: string[];
    joined?: boolean;
}

interface Post {
    postId: number;
    channelId: number;
    author: string;
    content: string;
    createdAt: string;
    likes: number;
    comments: { author: string; text: string }[];
}

// 더미 채널 데이터 (시현용)
const mockChannels: Channel[] = [
    {
        channelId: 1,
        channelName: '러닝 크루',
        channelIntro: '함께 달리는 러닝 커뮤니티',
        description: '주말마다 함께 달리며 건강을 챙기는 크루입니다. 초보자도 환영합니다!',
        hobbies: ['러닝', '운동', '건강'],
        joined: true,
    },
    {
        channelId: 2,
        channelName: '홈쿠킹 연구소',
        channelIntro: '집에서 요리하는 사람들',
        description: '집에서 새로운 요리 레시피를 공유하는 공간입니다.',
        hobbies: ['요리', '베이킹', '레시피'],
        joined: true,
    },
    {
        channelId: 3,
        channelName: '독서 모임',
        channelIntro: '매주 책을 읽고 이야기를 나누는 독서 동아리입니다.',
        description: '매월 한 권의 책을 선정해서 함께 읽고 토론합니다.',
        hobbies: ['독서', '토론', '글쓰기'],
        joined: false,
    },
    {
        channelId: 4,
        channelName: '사진 찍는 사람들',
        channelIntro: '일상의 순간을 카메라에 담는 사진 동호회입니다.',
        description: '카메라, 스마트폰 상관없이 사진 찍는 것을 좋아하는 분들 모여주세요!',
        hobbies: ['사진', '출사', '편집'],
        joined: false,
    },
    {
        channelId: 5,
        channelName: '영화 리뷰 클럽',
        channelIntro: '최신 영화부터 명작까지 리뷰를 공유하는 모임입니다.',
        description: '매주 영화를 감상하고 리뷰를 공유합니다.',
        hobbies: ['영화', '리뷰', '감상'],
        joined: true,
    },
    {
        channelId: 6,
        channelName: '캠핑 어디까지 가봤니',
        channelIntro: '캠핑 장소와 장비 정보를 공유하는 캠핑 마니아 모임입니다.',
        description: '전국 캠핑장 정보, 장비 리뷰, 캠핑 요리 레시피 등을 공유합니다.',
        hobbies: ['캠핑', '아웃도어', '자연'],
        joined: false,
    },
    {
        channelId: 7,
        channelName: '보드게임 카페',
        channelIntro: '다양한 보드게임을 함께 즐기는 게이머들의 공간입니다.',
        description: '보드게임 좋아하시는 분들 모여주세요!',
        hobbies: ['보드게임', '전략', '모임'],
        joined: true,
    },
    {
        channelId: 8,
        channelName: '식물 집사 모임',
        channelIntro: '반려식물 키우기 팁과 경험을 나누는 모임입니다.',
        description: '식물 키우기 초보부터 고수까지!',
        hobbies: ['식물', '가드닝', '인테리어'],
        joined: false,
    },
];

// 더미 게시글 데이터 (시현용)
const mockPosts: Post[] = [
    // 러닝 크루 (channelId: 1)
    {
        postId: 1,
        channelId: 1,
        author: '홍길동',
        content: '이번 주말에 러닝 같이 하실 분? 한강 반포지구에서 아침 7시에 만나요!',
        createdAt: '2025-12-17 10:00',
        likes: 12,
        comments: [
            { author: '김철수', text: '좋아요! 저도 참여할게요!' },
            { author: '박지민', text: '같이 뛰어요! 기대됩니다 🏃' },
            { author: '이수현', text: '저도 참여할게요! 첫 참여인데 잘 부탁드려요~' },
        ],
    },
    {
        postId: 2,
        channelId: 1,
        author: '김철수',
        content: '새로운 러닝 코스 추천해요! 올림픽공원 코스 진짜 좋습니다. 평지라서 초보자도 뛰기 좋아요 👍',
        createdAt: '2025-12-16 14:30',
        likes: 8,
        comments: [
            { author: '홍길동', text: '좋은 정보 감사합니다!' },
            { author: '운동맨', text: '다음에 가봐야겠네요' },
        ],
    },
    {
        postId: 3,
        channelId: 1,
        author: '박지민',
        content: '오늘 첫 10km 완주했어요! 🎉 다들 응원 감사합니다! 작년에 1km도 힘들었는데 이제 10km를 뛸 수 있다니 감격이에요.',
        createdAt: '2025-12-15 18:20',
        likes: 25,
        comments: [
            { author: '홍길동', text: '축하해요! 대단합니다!' },
            { author: '김철수', text: '와 정말 멋져요! 저도 열심히 해야겠어요' },
            { author: '운동초보', text: '저도 목표로 하고 있어요. 꼭 따라갈게요!' },
        ],
    },
    // 홈쿠킹 연구소 (channelId: 2)
    {
        postId: 4,
        channelId: 2,
        author: '이영희',
        content: '최근에 만든 초콜릿 케이크 레시피 공유합니다!\n\n재료: 생크림 200ml, 다크초콜릿 150g, 박력분 100g, 달걀 3개\n\n1. 초콜릿을 중탕으로 녹여주세요\n2. 달걀과 설탕을 휘핑합니다\n3. 박력분을 체쳐서 넣어주세요\n4. 180도에서 25분 구워주면 완성!',
        createdAt: '2025-12-15 12:00',
        likes: 18,
        comments: [
            { author: '요리왕', text: '맛있겠다! 이번 주말에 도전해볼게요' },
            { author: '달콤이', text: '레시피 감사해요! 사진도 올려주세요~' },
        ],
    },
    {
        postId: 5,
        channelId: 2,
        author: '최민수',
        content: '에어프라이어로 치킨 만들어봤는데 진짜 바삭해요! 비법은 전분가루예요. 밑간한 닭에 전분 묻히고 180도에서 25분!',
        createdAt: '2025-12-14 20:00',
        likes: 32,
        comments: [
            { author: '치킨러버', text: '대박! 당장 해봐야겠어요' },
            { author: '이영희', text: '오늘 저녁 메뉴 정했다 ㅋㅋ' },
            { author: '에어프라이어매니아', text: '온도 팁 감사합니다!' },
        ],
    },
    // 독서 모임 (channelId: 3)
    {
        postId: 6,
        channelId: 3,
        author: '정수현',
        content: '이번 달 선정 도서는 "데미안"입니다. 헤르만 헤세의 고전 명작이죠. 다들 읽고 오세요~ 12월 28일에 온라인 토론회 진행합니다!',
        createdAt: '2025-12-10 09:00',
        likes: 15,
        comments: [
            { author: '독서광', text: '좋은 선정이에요! 오랜만에 다시 읽어봐야겠네요' },
            { author: '책벌레', text: '벌써 읽기 시작했어요. 토론회 기대됩니다!' },
        ],
    },
    {
        postId: 7,
        channelId: 3,
        author: '김독서',
        content: '"작은 아씨들" 감상평입니다. 시대를 초월한 메시지가 인상깊었어요. 특히 조의 캐릭터가 현대의 여성상과도 잘 맞닿아 있다고 느꼈습니다.',
        createdAt: '2025-12-08 21:30',
        likes: 10,
        comments: [
            { author: '정수현', text: '공감해요! 저도 조가 가장 좋았어요' },
            { author: '문학소녀', text: '저도 인상깊게 읽었어요. 영화도 추천드려요!' },
        ],
    },
    // 사진 찍는 사람들 (channelId: 4)
    {
        postId: 8,
        channelId: 4,
        author: '사진작가',
        content: '오늘 출사 다녀왔어요! 서해 일몰 사진 공유합니다 📷 골든아워에 찍으니까 색감이 정말 예쁘게 나왔어요.',
        createdAt: '2025-12-16 19:00',
        likes: 45,
        comments: [
            { author: '카메라덕후', text: '와 너무 예뻐요! 어떤 렌즈 쓰셨어요?' },
            { author: '출사마니아', text: '어디서 찍으셨어요? 저도 가보고 싶어요' },
            { author: '색감장인', text: '색감 대박... 보정은 어떻게 하셨나요?' },
        ],
    },
    // 영화 리뷰 클럽 (channelId: 5)
    {
        postId: 9,
        channelId: 5,
        author: '영화광',
        content: '듄2 봤는데요... 역대급입니다. 꼭 아이맥스로 보세요! 한스 짐머 음악이 정말 압도적이에요. 사막 전투신은 소름 돋았습니다.',
        createdAt: '2025-12-12 22:00',
        likes: 28,
        comments: [
            { author: '시네필', text: '동의합니다! 올해 최고의 영화에요' },
            { author: 'SF팬', text: '사막신 진짜 압도적이었죠. 티모시 샬라메 연기도 최고!' },
        ],
    },
    // 캠핑 어디까지 가봤니 (channelId: 6)
    {
        postId: 10,
        channelId: 6,
        author: '캠핑마스터',
        content: '가평 ○○캠핑장 후기입니다. 시설 깨끗하고 뷰 최고! 특히 밤하늘 별이 정말 잘 보여요. 사이트 간격도 넓어서 프라이버시 보장됩니다.',
        createdAt: '2025-12-11 15:00',
        likes: 22,
        comments: [
            { author: '캠핑초보', text: '정보 감사합니다! 예약하기 어렵나요?' },
            { author: '텐트매니아', text: '다음 주 가볼게요. 추천 사이트 번호 있으신가요?' },
        ],
    },
    // 보드게임 카페 (channelId: 7)
    {
        postId: 11,
        channelId: 7,
        author: '게임러버',
        content: '이번 주 토요일 오프라인 모임 있습니다! 강남역 3시 집합! 이번에 새로 나온 "윙스팬" 해볼 예정이에요. 초보자도 환영합니다~',
        createdAt: '2025-12-13 10:00',
        likes: 16,
        comments: [
            { author: '보드게임덕후', text: '참여할게요! 윙스팬 해보고 싶었어요' },
            { author: '전략가', text: '몇 명 모였나요? 저도 참석하고 싶어요' },
        ],
    },
    // 식물 집사 모임 (channelId: 8)
    {
        postId: 12,
        channelId: 8,
        author: '식물덕후',
        content: '몬스테라 분갈이 했어요! 뿌리가 화분을 뚫고 나왔었네요 😅 2년만에 분갈이했더니 뿌리가 엄청 많아졌어요. 건강하게 잘 자라줘서 뿌듯합니다.',
        createdAt: '2025-12-14 11:00',
        likes: 19,
        comments: [
            { author: '초록이', text: '건강하게 잘 자랐네요! 저도 분갈이 해야하는데 엄두가 안나요' },
            { author: '가드너', text: '분갈이 후 관리법 공유해주세요~' },
        ],
    },
];

function PostDetailPage() {
    const { postId } = useParams<{ postId: string }>();
    const location = useLocation();
    const navigate = useNavigate();
    const channelId = location.state?.channelId;

    const [post, setPost] = useState<Post | null>(null);
    const [liked, setLiked] = useState(false);
    const [comments, setComments] = useState<Post['comments']>([]);
    const [channel, setChannel] = useState<Channel | null>(null);
    const [loading, setLoading] = useState(false);
    const [joinStatus, setJoinStatus] = useState<'NONE' | 'PENDING' | 'APPROVED'>('NONE');
    const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);
    const [commentAuthor, setCommentAuthor] = useState('');
    const [commentText, setCommentText] = useState('');

    const initialized = useRef(false);

    // 사용자 정보 조회
    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const response = await authApi.findCurrentUser();
                if (response.success) setUserInfo(response.data);
            } catch (error) {
                console.error('사용자 정보 불러오기 실패', error);
            }
        };
        fetchUserInfo();
    }, []);

    useEffect(() => {
        let currentChannel = mockChannels.find(ch => ch.channelId === channelId);
        
        // 더미 데이터에 없는 채널 ID인 경우 기본값 설정
        if (!currentChannel && channelId) {
            currentChannel = {
                channelId: channelId,
                channelName: `채널 ${channelId}`,
                channelIntro: '채널 소개입니다.',
                description: '채널 상세 설명입니다.',
                hobbies: ['취미1', '취미2'],
                joined: false,
            };
        }
        
        if (currentChannel) {
            setChannel(currentChannel);
            setJoinStatus(currentChannel.joined ? 'APPROVED' : 'NONE');
        }
    }, [channelId]);

    useEffect(() => {
        if (initialized.current) return;
        const found = mockPosts.find(p => p.postId === Number(postId));
        if (found) {
            setPost(found);
            setComments([...found.comments]);
            initialized.current = true;
        }
    }, [postId]);

    const handleLike = () => setLiked(prev => !prev);

    const handleAddComment = () => {
        if (!commentAuthor.trim() || !commentText.trim()) return;
        const newComment = { author: commentAuthor.trim(), text: commentText.trim() };
        setComments(prev => [...prev, newComment]);
        setCommentAuthor('');
        setCommentText('');
    };

    const handleJoinChannel = async () => {
        if (!channel || joinStatus !== 'NONE') return;

        setLoading(true);
        setJoinStatus('PENDING');

        try {
            const token = localStorage.getItem('token');
            const response = await fetch(`/channel/join`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({
                    channelId: channel.channelId,
                    userEmail: localStorage.getItem('userEmail'),
                }),
            });
            const data = await response.json();
            if (!data.success) {
                alert(data.message || '가입 요청 실패');
                setJoinStatus('NONE');
            }
        } catch (error) {
            console.error(error);
            alert('가입 요청 중 오류 발생');
            setJoinStatus('NONE');
        } finally {
            setLoading(false);
        }
    };

    if (!post) return <p>게시글 로딩중...</p>;

    return (
        <Container>
            <Sidebar>
                <LogoImage src={RebloomLogo} alt="Rebloom Logo" onClick={() => navigate('/')} />

                <ProfileSection>
                    <img src={React_svg} alt="프로필" />
                    <div>
                        <strong>{userInfo?.userName || '사용자 이름'}</strong>
                    </div>

                </ProfileSection>

                <WritePostButton
                    onClick={() =>
                        navigate('/myPostPage', {
                            state: { channelId: channel?.channelId, channelName: channel?.channelName },
                        })
                    }
                >
                    글 작성
                </WritePostButton>
{/* 
                <SearchBox>
                    <input placeholder="채널 검색..." />
                </SearchBox> */}

                <BackButton onClick={() => navigate('/community')}>← 커뮤니티로 돌아가기</BackButton>

                <div style={{ marginTop: 'auto', padding: '20px 0' }}>
                    <JoinChannelButton
                        onClick={handleJoinChannel}
                        joined={joinStatus === 'APPROVED'}
                        disabled={loading}
                    >
                        {joinStatus === 'APPROVED'
                            ? '가입됨'
                            : joinStatus === 'PENDING'
                                ? '가입 대기중'
                                : '가입하기'}
                    </JoinChannelButton>
                </div>
            </Sidebar>

            <ContentArea>
                <CloseButton onClick={() => navigate(`/channel/${channelId}`)}>
                    <CloseIconImg src={CloseIcon} alt="닫기" />
                </CloseButton>

                <ChannelTitle>{channel?.channelName}</ChannelTitle>
                <ChannelIntro>{channel?.channelIntro}</ChannelIntro>
                <ChannelDescription>{channel?.description}</ChannelDescription>

                <div>
                    {channel?.hobbies?.map((hobby, idx) => (
                        <HobbyTag key={idx}>{hobby}</HobbyTag>
                    ))}
                </div>

                <div style={{ marginTop: '30px' }}>
                    <PostList>
                    <PostItem>
                        <PostAuthor>
                        {post.author}
                        <span>{post.createdAt}</span>
                        </PostAuthor>
                        <p>{post.content}</p>

                        <div style={{ marginTop: '10px' }}>
                        <LikeButton liked={liked} onClick={handleLike}>
                            ❤️ {liked ? post.likes + 1 : post.likes || 0}
                        </LikeButton>
                        </div>
                    </PostItem>
                    </PostList>


                    <CommentSection>
                        {/* <SectionTitle>💬 댓글</SectionTitle> */}

                        {comments.length > 0 ? (
                            comments.map((comment, idx) => (
                                <CommentItem key={idx}>
                                    <strong>{comment.author}</strong>
                                    <p>{comment.text}</p>
                                </CommentItem>
                            ))
                        ) : (
                            <p>아직 댓글이 없습니다.</p>
                        )}

                        <CommentFormContainer>
                            <input
                                type="text"
                                placeholder="작성자 이름"
                                value={commentAuthor}
                                onChange={e => setCommentAuthor(e.target.value)}
                            />
                            <textarea
                                placeholder="댓글 입력"
                                rows={3}
                                value={commentText}
                                onChange={e => setCommentText(e.target.value)}
                                onKeyDown={e => {
                                    if (e.key === 'Enter' && !e.shiftKey) {
                                        e.preventDefault();
                                        handleAddComment();
                                    }
                                }}
                            />
                            <button onClick={handleAddComment}>댓글 작성</button>
                        </CommentFormContainer>
                    </CommentSection>
                </div>
            </ContentArea>
        </Container>
    );
}

export default PostDetailPage;
