import { useParams, useNavigate } from 'react-router-dom';
import { useState } from 'react';
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
} from './PD';
import { posts } from './posts';
import CloseIcon from '../../assets/images/close.svg';
import RebloomLogo from '../../assets/images/Rebloom-logo.svg';

function PostDetail() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [expandedCategory, setExpandedCategory] = useState<string | null>(null);

    const post = posts.find(p => p.id.toString() === id);
    const categories = [
        { name: '공지사항', emoji: '📢' },
        { name: '즐겨찾는 게시판', emoji: '⭐' },
        { name: '함께해요', emoji: '🤝' },
        { name: '소통', emoji: '💬' },
    ];

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

    if (!post) {
        return <Container>존재하지 않는 게시글입니다.</Container>;
    }

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
                    <input type="text" placeholder="게시글 검색"/>
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
                                        {posts
                                            .filter(post =>
                                                category.name === '즐겨찾는 게시판'
                                                    ? post.favorite
                                                    : post.category === category.name
                                            )
                                            .map(post => (
                                                <li
                                                    key={post.id}
                                                    onClick={() => navigate(`/post/${post.id}`)}
                                                >
                                                    ㄴ {post.title}
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
                        </div>
                    </PostItem>
                </PostList>
                <PostDivider></PostDivider>
                <div style={{ marginTop: '30px' }}>
                    <h3>💬 댓글</h3>
                    {post.comments && post.comments.length > 0 ? (
                        post.comments.map((comment, idx) => (
                            <CommentItem key={idx}>
                                <strong>{comment.author}</strong>
                                <p>{comment.text}</p>
                            </CommentItem>
                        ))
                    ) : (
                        <p>아직 댓글이 없습니다.</p>
                    )}
                </div>
            </ContentArea>
        </Container>
    );
}

export default PostDetail;
