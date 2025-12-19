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
import { channelApi } from '../../api/channel';
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
        const fetchChannel = async () => {
            if (!id) return;
            
            try {
                setLoading(true);
                const response = await channelApi.getChannel(Number(id));
                if (response.success && response.data) {
                    const data = response.data;
                    const hobbies: string[] = [];
                    if (data.linkedHobbyName1) hobbies.push(data.linkedHobbyName1);
                    if (data.linkedHobbyName2) hobbies.push(data.linkedHobbyName2);
                    if (data.linkedHobbyName3) hobbies.push(data.linkedHobbyName3);
                    
                    setChannel({
                        channelId: data.channelId,
                        channelName: data.channelTitle,
                        channelIntro: data.channelIntro,
                        description: data.channelDescription,
                        hobbies: hobbies,
                    });
                }
            } catch (error) {
                console.error('채널 정보 조회 실패:', error);
                setChannel({
                    channelId: Number(id),
                    channelName: '알 수 없는 채널',
                    channelIntro: '',
                    description: '',
                    hobbies: [],
                });
            } finally {
                setLoading(false);
            }
        };
        
        fetchChannel();
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