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

const initialChannels: Channel[] = [
    {
        channelId: 1,
        channelName: '러닝 크루',
        channelIntro: '함께 달리는 러닝 커뮤니티',
        description: '주말마다 함께 달리며 건강을 챙기는 크루입니다.',
        hobbies: ['러닝', '운동', '건강'],
    },
    {
        channelId: 2,
        channelName: '홈쿠킹 연구소',
        channelIntro: '집에서 요리하는 사람들',
        description: '집에서 새로운 요리 레시피를 공유하는 공간입니다.',
        hobbies: ['요리', '베이킹', '레시피'],
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
        comments: ['좋아요!', '같이 뛰어요!'],
    },
    {
        postId: 2,
        channelId: 1,
        author: '김철수',
        content: '새로운 러닝 코스 추천해요!',
        createdAt: '2025-12-16 14:30',
        likes: 1,
        comments: ['좋아요'],
    },
    {
        postId: 3,
        channelId: 2,
        author: '이영희',
        content: '최근에 만든 초콜릿 케이크 레시피 공유합니다.',
        createdAt: '2025-12-15 12:00',
        likes: 2,
        comments: ['맛있겠다!'],
    },
];

function ChannelPage() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    const [channels] = useState<Channel[]>(initialChannels);
    const [posts, setPosts] = useState<Post[]>([]);
    const [channel, setChannel] = useState<Channel | null>(null);
    const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);

    // ✅ 가입 여부 검사 (핵심)
    useEffect(() => {
        const joinedChannels: number[] = JSON.parse(
            localStorage.getItem('joinedChannels') || '[]'
        );

        if (!joinedChannels.includes(Number(id))) {
            alert('가입한 채널만 접근할 수 있습니다.');
            navigate('/community');
        }
    }, [id, navigate]);

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
        const currentChannel = channels.find(ch => String(ch.channelId) === id);
        if (currentChannel) {
            setChannel(currentChannel);
            setPosts(
                initialPosts.filter(post => post.channelId === currentChannel.channelId)
            );
        }
    }, [id, channels]);

    if (!channel) return null;

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
                            state: {
                                channelId: channel.channelId,
                                channelName: channel.channelName,
                            },
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
                                }}
                            >
                                <strong>{post.author}</strong>
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
