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
    JoinChannelButton,
    SearchBox,
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
    joined?: boolean;
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

const initialChannels: Channel[] = [
    {
        channelId: 1,
        channelName: '러닝 크루',
        channelIntro: '함께 달리는 러닝 커뮤니티',
        description: '주말마다 함께 달리며 건강을 챙기는 크루입니다.',
        hobbies: ['러닝', '운동', '건강'],
        joined: false,
    },
    {
        channelId: 2,
        channelName: '홈쿠킹 연구소',
        channelIntro: '집에서 요리하는 사람들',
        description: '집에서 새로운 요리 레시피를 공유하는 공간입니다.',
        hobbies: ['요리', '베이킹', '레시피'],
        joined: true,
    },
];

const initialPosts: Post[] = [
    {
        postId: 1,
        channelId: 1,
        author: '홍길동',
        content: '이번 주말에 러닝 같이 하실 분?',
        createdAt: '2025-12-17 10:00',
        likes: 3,
        comments: ['좋아요!', '같이 뛰어요!']
    },
    {
        postId: 2,
        channelId: 1,
        author: '김철수',
        content: '새로운 러닝 코스 추천해요!',
        createdAt: '2025-12-16 14:30',
        likes: 1,
        comments: ['좋아요']
    },
    {
        postId: 3,
        channelId: 2,
        author: '이영희',
        content: '최근에 만든 초콜릿 케이크 레시피 공유합니다.',
        createdAt: '2025-12-15 12:00',
        likes: 2,
        comments: ['맛있겠다!']
    },
];

function ChannelPage() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    // ✅ mockChannels를 상태로 관리
    const [channels, setChannels] = useState<Channel[]>(initialChannels);
    const [posts, setPosts] = useState<Post[]>(initialPosts);
    const [channel, setChannel] = useState<Channel | null>(null);
    const [loading, setLoading] = useState(false);
    const [joinStatus, setJoinStatus] = useState<'NONE' | 'PENDING' | 'APPROVED'>('NONE');
    const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);

    // 사용자 정보 불러오기
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

    // 채널 정보 설정
    useEffect(() => {
        const currentChannel = channels.find(ch => String(ch.channelId) === id);
        if (currentChannel) {
            setChannel(currentChannel);
            setJoinStatus(currentChannel.joined ? 'APPROVED' : 'NONE');
        } else {
            setChannel({
                channelId: Number(id),
                channelName: '알 수 없는 채널',
                channelIntro: '',
                description: '',
                hobbies: [],
                joined: false,
            });
        }
    }, [id, channels]);

    // 채널 게시글 필터링
    useEffect(() => {
        if (channel) {
            setPosts(initialPosts.filter(post => post.channelId === channel.channelId));
        }
    }, [channel]);

    // 채널 가입 신청
    const handleJoinChannel = async () => {
        if (!channel || joinStatus !== 'NONE') return;

        const applyMessage = prompt('가입 신청 메시지를 입력하세요', '안녕하세요! 가입 요청드립니다.');
        if (!applyMessage) return;

        setLoading(true);

        try {
            const token = localStorage.getItem('token');
            const response = await fetch('/channel/member/apply', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({
                    channelId: channel.channelId,
                    applyMessage,
                }),
            });

            const data = await response.json();

            if (data.success) {
                alert('가입 신청이 완료되었습니다.');
                setJoinStatus('PENDING');

                // ✅ 채널 상태 업데이트
                setChannels(prev =>
                    prev.map(ch =>
                        ch.channelId === channel.channelId ? { ...ch, joined: true } : ch
                    )
                );
            } else {
                if (data.error_name === 'AlreadyUsingIdException') {
                    alert('이미 가입 신청한 채널입니다.');
                    setJoinStatus('PENDING');
                } else {
                    alert(data.message || '가입 신청 실패');
                }
            }
        } catch (error) {
            console.error(error);
            alert('가입 신청 중 오류가 발생했습니다.');
        } finally {
            setLoading(false);
        }
    };

    if (!channel) return <p>로딩중...</p>;

    return (
        <Container>
            <Sidebar>
                <LogoImage src={RebloomLogo} alt="Rebloom Logo" onClick={() => navigate('/')} />

                <ProfileSection>
                    <img src={React_svg} alt="프로필" />
                    <div>
                        <strong>{userInfo?.userName || '사용자 이름'}</strong>
                        <p>레벨 {userInfo ? Math.floor(userInfo.userTierPoint / 1000) + 1 : 1}</p>
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

                <SearchBox>
                    <input placeholder="채널 검색..." />
                </SearchBox>

                <BackButton onClick={() => navigate('/community')}>← 커뮤니티로 돌아가기</BackButton>

                <div style={{ marginTop: 'auto', padding: '20px 0' }}>
                    <JoinChannelButton
                        onClick={handleJoinChannel}
                        joined={joinStatus === 'APPROVED'}
                        disabled={loading || joinStatus === 'APPROVED' || joinStatus === 'PENDING'}
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
                <CloseButton onClick={() => navigate('/main')}>
                    <CloseIconImg src={CloseIcon} alt="닫기" />
                </CloseButton>

                <ChannelContainer>
                    <ChannelTitle>{channel?.channelName}</ChannelTitle>
                    <ChannelIntro>{channel?.channelIntro}</ChannelIntro>
                    <ChannelDescription>{channel?.description}</ChannelDescription>

                    <HobbyTagContainer>
                        {channel?.hobbies?.map((hobby, idx) => (
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
                                    background: '#ffffffff',
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
                                <span style={{ fontSize: '12px', color: '#999' }}>{post.createdAt}</span>
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
