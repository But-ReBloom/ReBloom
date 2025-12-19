import { useNavigate, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
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
    // SearchBox,
    ProfileSection,
    WritePostButton,
    ChannelContainer,
    HobbyTagContainer,
} from './cstyle';

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
}

interface Post {
    postId: number;
    channelId: number;
    author: string;
    content: string;
    createdAt: string;
    likes: number;
    comments: string[];
}

// 더미 채널 데이터 (시현용)
const dummyChannels: Record<number, Channel> = {
    1: {
        channelId: 1,
        channelName: '러닝 크루',
        channelIntro: '함께 달리며 건강을 챙기는 러닝 커뮤니티입니다.',
        description: '주말마다 함께 달리며 건강을 챙기는 크루입니다. 초보자도 환영합니다! 매주 토요일 아침 7시에 한강에서 모여요.',
        hobbies: ['러닝', '운동', '건강'],
    },
    2: {
        channelId: 2,
        channelName: '홈쿠킹 연구소',
        channelIntro: '집에서 새로운 요리 레시피를 공유하는 공간입니다.',
        description: '집에서 맛있는 요리를 만들어보세요! 레시피 공유, 요리 팁, 재료 추천 등 다양한 정보를 나눕니다.',
        hobbies: ['요리', '베이킹', '레시피'],
    },
    3: {
        channelId: 3,
        channelName: '독서 모임',
        channelIntro: '매주 책을 읽고 이야기를 나누는 독서 동아리입니다.',
        description: '매월 한 권의 책을 선정해서 함께 읽고 토론합니다. 다양한 장르의 책을 읽으며 새로운 관점을 얻어보세요.',
        hobbies: ['독서', '토론', '글쓰기'],
    },
    4: {
        channelId: 4,
        channelName: '사진 찍는 사람들',
        channelIntro: '일상의 순간을 카메라에 담는 사진 동호회입니다.',
        description: '카메라, 스마트폰 상관없이 사진 찍는 것을 좋아하는 분들 모여주세요! 출사도 가고 사진 피드백도 나눕니다.',
        hobbies: ['사진', '출사', '편집'],
    },
    5: {
        channelId: 5,
        channelName: '영화 리뷰 클럽',
        channelIntro: '최신 영화부터 명작까지 리뷰를 공유하는 모임입니다.',
        description: '매주 영화를 감상하고 리뷰를 공유합니다. 영화 추천, 감상평, 영화 관련 이야기를 나눠요.',
        hobbies: ['영화', '리뷰', '감상'],
    },
    6: {
        channelId: 6,
        channelName: '캠핑 어디까지 가봤니',
        channelIntro: '캠핑 장소와 장비 정보를 공유하는 캠핑 마니아 모임입니다.',
        description: '전국 캠핑장 정보, 장비 리뷰, 캠핑 요리 레시피 등을 공유합니다. 같이 캠핑도 가요!',
        hobbies: ['캠핑', '아웃도어', '자연'],
    },
    7: {
        channelId: 7,
        channelName: '보드게임 카페',
        channelIntro: '다양한 보드게임을 함께 즐기는 게이머들의 공간입니다.',
        description: '보드게임 좋아하시는 분들 모여주세요! 정기적으로 오프라인 모임도 가집니다.',
        hobbies: ['보드게임', '전략', '모임'],
    },
    8: {
        channelId: 8,
        channelName: '식물 집사 모임',
        channelIntro: '반려식물 키우기 팁과 경험을 나누는 모임입니다.',
        description: '식물 키우기 초보부터 고수까지! 식물 관리법, 분갈이, 번식 방법 등을 공유합니다.',
        hobbies: ['식물', '가드닝', '인테리어'],
    },
};

// 더미 게시글 데이터 (시현용)
const initialPosts: Post[] = [
    // 러닝 크루 (channelId: 1)
    {
        postId: 1,
        channelId: 1,
        author: '홍길동',
        content: '이번 주말에 러닝 같이 하실 분? 한강 반포지구에서 아침 7시에 만나요!',
        createdAt: '2025-12-17 10:00',
        likes: 12,
        comments: ['좋아요!', '같이 뛰어요!', '저도 참여할게요!'],
    },
    {
        postId: 2,
        channelId: 1,
        author: '김철수',
        content: '새로운 러닝 코스 추천해요! 올림픽공원 코스 진짜 좋습니다 👍',
        createdAt: '2025-12-16 14:30',
        likes: 8,
        comments: ['좋아요', '다음에 가봐야겠네요'],
    },
    {
        postId: 3,
        channelId: 1,
        author: '박지민',
        content: '오늘 첫 10km 완주했어요! 🎉 다들 응원 감사합니다!',
        createdAt: '2025-12-15 18:20',
        likes: 25,
        comments: ['축하해요!', '대단해요!', '다음 목표는 하프마라톤!'],
    },
    // 홈쿠킹 연구소 (channelId: 2)
    {
        postId: 4,
        channelId: 2,
        author: '이영희',
        content: '최근에 만든 초콜릿 케이크 레시피 공유합니다. 생크림 200ml, 다크초콜릿 150g...',
        createdAt: '2025-12-15 12:00',
        likes: 18,
        comments: ['맛있겠다!', '레시피 감사해요!'],
    },
    {
        postId: 5,
        channelId: 2,
        author: '최민수',
        content: '에어프라이어로 치킨 만들어봤는데 진짜 바삭해요! 비법은 전분가루!',
        createdAt: '2025-12-14 20:00',
        likes: 32,
        comments: ['대박!', '오늘 저녁 메뉴 정했다'],
    },
    // 독서 모임 (channelId: 3)
    {
        postId: 6,
        channelId: 3,
        author: '정수현',
        content: '이번 달 선정 도서는 "데미안"입니다. 다들 읽고 오세요~',
        createdAt: '2025-12-10 09:00',
        likes: 15,
        comments: ['좋은 선정이에요!', '벌써 읽기 시작했어요'],
    },
    {
        postId: 7,
        channelId: 3,
        author: '김독서',
        content: '"작은 아씨들" 감상평입니다. 시대를 초월한 메시지가...',
        createdAt: '2025-12-08 21:30',
        likes: 10,
        comments: ['공감해요', '저도 인상깊게 읽었어요'],
    },
    // 사진 찍는 사람들 (channelId: 4)
    {
        postId: 8,
        channelId: 4,
        author: '사진작가',
        content: '오늘 출사 다녀왔어요! 일몰 사진 공유합니다 📷',
        createdAt: '2025-12-16 19:00',
        likes: 45,
        comments: ['와 너무 예뻐요!', '어디서 찍으셨어요?', '색감 대박'],
    },
    // 영화 리뷰 클럽 (channelId: 5)
    {
        postId: 9,
        channelId: 5,
        author: '영화광',
        content: '듄2 봤는데요... 역대급입니다. 꼭 아이맥스로 보세요!',
        createdAt: '2025-12-12 22:00',
        likes: 28,
        comments: ['동의합니다!', '사막신 진짜 압도적'],
    },
    // 캠핑 (channelId: 6)
    {
        postId: 10,
        channelId: 6,
        author: '캠핑마스터',
        content: '가평 ○○캠핑장 후기입니다. 시설 깨끗하고 뷰 최고!',
        createdAt: '2025-12-11 15:00',
        likes: 22,
        comments: ['정보 감사합니다', '다음 주 가볼게요'],
    },
    // 보드게임 카페 (channelId: 7)
    {
        postId: 11,
        channelId: 7,
        author: '게임러버',
        content: '이번 주 토요일 오프라인 모임 있습니다! 강남역 3시 집합!',
        createdAt: '2025-12-13 10:00',
        likes: 16,
        comments: ['참여할게요!', '몇 명 모였나요?'],
    },
    // 식물 집사 모임 (channelId: 8)
    {
        postId: 12,
        channelId: 8,
        author: '식물덕후',
        content: '몬스테라 분갈이 했어요! 뿌리가 화분을 뚫고 나왔었네요 😅',
        createdAt: '2025-12-14 11:00',
        likes: 19,
        comments: ['건강하게 잘 자랐네요', '저도 분갈이 해야하는데'],
    },
];

function ChannelPage() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    const [posts, setPosts] = useState<Post[]>(initialPosts);
    const [channel, setChannel] = useState<Channel | null>(null);
    const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const response = await authApi.findCurrentUser();
                if (response.success) setUserInfo(response.data);
            } catch (error) {
                console.error(error);
            }
        };
        fetchUserInfo();
    }, []);

    useEffect(() => {
        // 더미 데이터 사용 (시현용)
        if (!id) return;
        
        setLoading(true);
        setTimeout(() => {
            const channelId = Number(id);
            const dummyChannel = dummyChannels[channelId];
            
            if (dummyChannel) {
                setChannel(dummyChannel);
            } else {
                // 더미 데이터에 없는 채널 ID인 경우 기본값 설정
                setChannel({
                    channelId: channelId,
                    channelName: `채널 ${channelId}`,
                    channelIntro: '채널 소개입니다.',
                    description: '채널 상세 설명입니다.',
                    hobbies: ['취미1', '취미2'],
                });
            }
            setLoading(false);
        }, 200);
    }, [id]);

    useEffect(() => {
        if (channel) {
            setPosts(initialPosts.filter(post => post.channelId === channel.channelId));
        }
    }, [channel]);

    if (loading) return <p>로딩중...</p>;
    if (!channel) return <p>채널을 찾을 수 없습니다.</p>;

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
                            state: { channelId: channel.channelId, channelName: channel.channelName },
                        })
                    }
                >
                    글 작성
                </WritePostButton>

                {/* <SearchBox>
                    <input placeholder="채널 검색..." />
                </SearchBox> */}

                <BackButton onClick={() => navigate('/community')}>
                    ← 커뮤니티로 돌아가기
                </BackButton>
            </Sidebar>

            <ContentArea>
                <CloseButton onClick={() => navigate('/main')}>
                    <CloseIconImg src={CloseIcon} alt="닫기" />
                </CloseButton>

                <ChannelContainer>
                    <ChannelTitle>{channel.channelName}</ChannelTitle>
                    <ChannelIntro>{channel.channelIntro}</ChannelIntro>
                    <ChannelDescription>{channel.description}</ChannelDescription>

                    <HobbyTagContainer>
                        {channel.hobbies?.map((hobby, idx) => (
                            <HobbyTag key={idx}>{hobby}</HobbyTag>
                        ))}
                    </HobbyTagContainer>
                </ChannelContainer>

                <div style={{ marginTop: '30px' }}>
                    <h3>게시글</h3>
                    {posts.length === 0 ? (
                        <p>등록된 게시글이 없습니다.</p>
                    ) : (
                        posts.map(post => (
                            <div
                                key={post.postId}
                                style={{
                                    border: '1px solid #ffffff',
                                    background: '#ffffff',
                                    padding: '10px',
                                    marginBottom: '10px',
                                    borderRadius: '5px',
                                    cursor: 'pointer',
                                }}
                                onClick={() =>
                                    navigate(`/post/${post.postId}`, {
                                        state: { channelId: channel.channelId },
                                    })
                                }
                            >
                                <strong>{post.author}</strong>{' '}
                                <span style={{ fontSize: '12px', color: '#999' }}>
                                    {post.createdAt}
                                </span>
                                <p>{post.content}</p>
                            </div>
                        ))
                    )}
                </div>
            </ContentArea>
        </Container>
    );
}

export default ChannelPage;