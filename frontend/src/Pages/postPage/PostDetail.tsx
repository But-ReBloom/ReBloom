import { useParams, useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import styled from 'styled-components';
import {
    Container,
    Sidebar,
    CafeInfo,
    ProfileSection,
    NavMenu,
    SearchBox,
    ContentArea,
    Header,
    PostList,
    PostItem,
    Tag,
    CloseButton,
    CloseIconImg,
    LogoImage,
    Divider,
    SubMenu,
    CommentItem,
    PostDivider,
    CommentFormContainer,
    LikeButton
} from './PD';
import CloseIcon from '../../assets/images/close.svg';
import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import { posts as initialPosts } from './posts';

function PostDetail() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();

    const [expandedCategory, setExpandedCategory] = useState<string | null>(null);
    const [allPosts, setAllPosts] = useState<any[]>([]);
    const [post, setPost] = useState<any>(null);

    const [commentAuthor, setCommentAuthor] = useState('');
    const [commentText, setCommentText] = useState('');

    const [liked, setLiked] = useState(false); // 하트 클릭 상태

    useEffect(() => {
        const savedPosts = JSON.parse(localStorage.getItem('myPosts') || '[]');

        // 중복 제거
        const mergedPosts = [...savedPosts, ...initialPosts].filter(
            (p, index, self) => index === self.findIndex(post => post.id === p.id)
        );

        setAllPosts(mergedPosts);

        const currentPost = mergedPosts.find((p: any) => p.id.toString() === id);
        if (currentPost) {
            if (currentPost.likes === undefined) currentPost.likes = 0;
            setPost(currentPost);
        }
    }, [id]);

    const toggleCategory = (category: string) => {
        setExpandedCategory(prev => (prev === category ? null : category));
    };

    const PostListButton = styled.button`
        width: 100%;
        padding: 10px;
        margin: 12px 0;
        margin-top: -10px;
        margin-bottom: -5px;
        background-color: #5db9eeff;
        color: white;
        border: none;
        border-radius: 6px;
        cursor: pointer;
        font-weight: bold;
    `;

    const handleAddComment = () => {
        if (!commentAuthor || !commentText || !post) return;

        const newComment = { author: commentAuthor, text: commentText };
        const updatedPost = { ...post };
        if (!updatedPost.comments) updatedPost.comments = [];
        updatedPost.comments.push(newComment);

        // 상태 업데이트
        setPost(updatedPost);
        const updatedPosts = allPosts.map(p => (p.id === post.id ? updatedPost : p));
        setAllPosts(updatedPosts);

        // 로컬스토리지 업데이트
        const savedPosts = JSON.parse(localStorage.getItem('myPosts') || '[]');
        const index = savedPosts.findIndex((p: any) => p.id === post.id);
        if (index >= 0) {
            savedPosts[index] = updatedPost;
        } else {
            savedPosts.push(updatedPost);
        }
        localStorage.setItem('myPosts', JSON.stringify(savedPosts));

        setCommentAuthor('');
        setCommentText('');
    };

    const handleLike = () => {
        if (!post) return;

        const updatedPost = { ...post };
        if (!updatedPost.likes) updatedPost.likes = 0;

        if (liked) {
            updatedPost.likes -= 1;
        } else {
            updatedPost.likes += 1;
        }

        setLiked(!liked);
        setPost(updatedPost);

        const updatedPosts = allPosts.map(p => (p.id === post.id ? updatedPost : p));
        setAllPosts(updatedPosts);

        const savedPosts = JSON.parse(localStorage.getItem('myPosts') || '[]');
        const index = savedPosts.findIndex((p: any) => p.id === post.id);
        if (index >= 0) {
            savedPosts[index] = updatedPost;
        } else {
            savedPosts.push(updatedPost);
        }
        localStorage.setItem('myPosts', JSON.stringify(savedPosts));
    };

    if (!post) {
        return <Container>존재하지 않는 게시글입니다.</Container>;
    }

    const categories = [
        { name: '공지사항', emoji: '📢' },
        { name: '즐겨찾는 게시판', emoji: '⭐' },
        { name: '함께해요', emoji: '🤝' },
        { name: '소통', emoji: '💬' },
    ];

    return (
        <Container>
            <Sidebar>
                <LogoImage src={RebloomLogo} alt="Rebloom Logo" onClick={() => navigate('/main')} />
                <Divider />
                <CafeInfo>
                    <p>Rebloom 게시글 페이지입니다.</p>
                </CafeInfo>
                <ProfileSection>
                    <img src="" alt="프로필" />
                    <div>
                        <strong>홍길동</strong>
                        <p>레벨 3</p>
                    </div>
                </ProfileSection>

                <PostListButton onClick={() => navigate('/post')}>
                    게시글 보기
                </PostListButton>

                <SearchBox>
                    <input type="text" placeholder="게시글 검색" />
                </SearchBox>

                <NavMenu>
                    <ul>
                        {categories.map(category => (
                            <li key={category.name}>
                                <div onClick={() => toggleCategory(category.name)}>
                                    {category.emoji} {category.name}
                                </div>
                                {expandedCategory === category.name && (
                                    <SubMenu>
                                        {allPosts
                                            .filter(p =>
                                                category.name === '즐겨찾는 게시판'
                                                    ? p.favorite
                                                    : p.category === category.name
                                            )
                                            .map(p => (
                                                <li
                                                    key={p.id}
                                                    onClick={() => navigate(`/post/${p.id}`)}
                                                >
                                                    ㄴ {p.title}
                                                </li>
                                            ))}
                                    </SubMenu>
                                )}
                            </li>
                        ))}
                    </ul>
                </NavMenu>
            </Sidebar>

            <ContentArea>
                <Header>
                    <h1>게시글 보기</h1>
                    <CloseButton onClick={() => navigate('/main')}>
                        <CloseIconImg src={CloseIcon} alt="닫기" />
                    </CloseButton>
                </Header>

                <PostList>
                    <PostItem $notice={post.notice}>
                        <Tag>{post.tag}</Tag>
                        <div>
                            <h2>{post.title}</h2>
                            <p>{post.content}</p>

                            <div style={{ marginTop: '10px' }}>
                                <LikeButton liked={liked} onClick={handleLike}>
                                    ❤️ {post.likes || 0}
                                </LikeButton>
                            </div>

                        </div>
                    </PostItem>
                </PostList>

                <PostDivider />

                <div style={{ marginTop: '30px' }}>
                    <h3>💬 댓글</h3>
                    {post.comments && post.comments.length > 0 ? (
                        post.comments.map((comment: any, idx: number) => (
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
                            onChange={(e) => setCommentAuthor(e.target.value)}
                        />
                        <textarea
                            placeholder="댓글 입력"
                            rows={3}
                            value={commentText}
                            onChange={(e) => setCommentText(e.target.value)}
                        />
                        <button onClick={handleAddComment}>댓글 작성</button>
                    </CommentFormContainer>
                </div>
            </ContentArea>
        </Container>
    );
}

export default PostDetail;
