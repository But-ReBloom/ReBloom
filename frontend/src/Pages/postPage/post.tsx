import { useNavigate } from 'react-router-dom';
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
    HideNoticeButton,
    Divider,
    SubMenu,
} from './pst';
import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
// import { posts as initialPosts } from './posts';
import { postApi } from '../../api/post';
import type { CreatePostResponse } from '../../types/PostTypes';

const WritePostButton = styled.button`
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
    &:hover {
        background-color: #5db9eeac;
    }
`;

const PaginationWrapper = styled.div`
    margin-top: 20px;
    display: flex;
    justify-content: center;
    gap: 5px;
`;

const PageButton = styled.button<{ active?: boolean }>`
    padding: 5px 10px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    background-color: ${props => (props.active ? '#2b90d9' : '#f0f0f0')};
    color: ${props => (props.active ? '#fff' : '#000')};
`;

const POSTS_PER_PAGE = 9;

function Post() {
    const navigate = useNavigate();
    const [hideNotices, setHideNotices] = useState(false);
    const [expandedCategory, setExpandedCategory] = useState<string | null>(null);
    const [currentPage, setCurrentPage] = useState(1);
    const [allPosts, setAllPosts] = useState<any[]>([]);

    useEffect(() => {
        const fetchPosts = async () => {
            try {
                const response = await postApi.searchPosts({ keyword: "" });
                if (response.success) {
                    // Map API response to UI format
                    const mappedPosts = response.data.posts.map((post: CreatePostResponse) => ({
                        id: post.postId,
                        tag: post.postType === 'NORMAL' ? '[일반]' : '[인기]',
                        title: post.postTitle,
                        notice: false,
                        category: '소통', // Default or map from channelId
                        favorite: false,
                        content: post.postContent,
                        comments: [],
                        author: post.userId,
                        date: post.postCreatedAt,
                        views: post.viewers,
                        likes: 0
                    }));
                    setAllPosts(mappedPosts);
                }
            } catch (error) {
                console.error("Failed to fetch posts", error);
            }
        };
        fetchPosts();
    }, []);

    const handleCloseClick = () => navigate('/main');
    const handleToggleNotices = () => {
        setHideNotices(prev => !prev);
        setCurrentPage(1);
    };
    const handleWritePost = () => navigate('/myPostPage');
    const handlePostClick = (id: number) => navigate(`/post/${id}`);

    const sortedPosts = [...allPosts].sort((a, b) => {
        if (a.notice && !b.notice) return -1;
        if (!a.notice && b.notice) return 1;
        return 0;
    });

    const filteredPosts = sortedPosts.filter(post => !hideNotices || !post.notice);
    const totalPages = Math.ceil(filteredPosts.length / POSTS_PER_PAGE);
    const currentPosts = filteredPosts.slice(
        (currentPage - 1) * POSTS_PER_PAGE,
        currentPage * POSTS_PER_PAGE
    );

    const categories = [
        { name: '공지사항', emoji: '📢' },
        { name: '즐겨찾는 게시판', emoji: '⭐' },
        { name: '함께해요', emoji: '🤝' },
        { name: '소통', emoji: '💬' },
    ];

    const toggleCategory = (category: string) => {
        setExpandedCategory(prev => (prev === category ? null : category));
    };

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

                <WritePostButton onClick={handleWritePost}>게시글 쓰기</WritePostButton>

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
                                        {filteredPosts
                                            .filter(post =>
                                                category.name === '즐겨찾는 게시판'
                                                    ? post.favorite
                                                    : post.category === category.name
                                            )
                                            .map(post => (
                                                <li
                                                    key={post.id}
                                                    onClick={() => handlePostClick(post.id)}
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
                    <h1>전체글 보기</h1>
                    <span>총 {filteredPosts.length}개의 글</span>
                    <HideNoticeButton onClick={handleToggleNotices}>
                        {hideNotices ? '공지 보기' : '공지 숨기기'}
                    </HideNoticeButton>
                </Header>

                <PostList>
                    {currentPosts.map(post => (
                        <PostItem
                            key={post.id}
                            $notice={post.notice}
                            onClick={() => handlePostClick(post.id)}
                        >
                            <Tag>{post.tag}</Tag>
                            <span>{post.title}</span>
                        </PostItem>
                    ))}
                </PostList>

                <PaginationWrapper>
                    {Array.from({ length: totalPages }, (_, idx) => idx + 1).map(page => (
                        <PageButton
                            key={page}
                            active={page === currentPage}
                            onClick={() => setCurrentPage(page)}
                        >
                            {page}
                        </PageButton>
                    ))}
                </PaginationWrapper>
            </ContentArea>

            <CloseButton onClick={handleCloseClick}>
                <CloseIconImg src={CloseIcon} alt="닫기" />
            </CloseButton>
        </Container>
    );
}

export default Post;
