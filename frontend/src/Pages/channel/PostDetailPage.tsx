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

const mockChannels: Channel[] = [
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

const mockPosts: Post[] = [
    {
        postId: 1,
        channelId: 1,
        author: '홍길동',
        content: '이번 주말에 러닝 같이 하실 분?',
        createdAt: '2025-12-17 10:00',
        likes: 3,
        comments: [
            { author: '사용자1', text: '좋아요!' },
            { author: '사용자2', text: '같이 뛰어요!' },
        ],
    },
    {
        postId: 2,
        channelId: 1,
        author: '김철수',
        content: '새로운 러닝 코스 추천해요!',
        createdAt: '2025-12-16 14:30',
        likes: 1,
        comments: [{ author: '사용자3', text: '좋아요' }],
    },
    {
        postId: 3,
        channelId: 2,
        author: '이영희',
        content: '최근에 만든 초콜릿 케이크 레시피 공유합니다.',
        createdAt: '2025-12-15 12:00',
        likes: 2,
        comments: [{ author: '사용자4', text: '맛있겠다!' }],
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
        const currentChannel = mockChannels.find(ch => ch.channelId === channelId);
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
                        <p>레벨 {userInfo ? Math.floor(userInfo.userTierPoint / 1000) + 1 : 1}</p>
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
