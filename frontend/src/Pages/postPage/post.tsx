import { useNavigate } from 'react-router-dom';
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
    HideNoticeButton,
    Divider,
    SubMenu,
} from './pst';
import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import { posts } from './posts';

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

function Post() {
    const navigate = useNavigate();
    const [hideNotices, setHideNotices] = useState(false);
    const [expandedCategory, setExpandedCategory] = useState<string | null>(null);

    const handleCloseClick = () => navigate('/main'); // 닫기 → 메인 페이지로
    const handleToggleNotices = () => setHideNotices(prev => !prev);
    const handleWritePost = () => navigate('/myPostPage');

    const sortedPosts = [...posts].sort((a, b) => {
        if (a.notice && !b.notice) return -1;
        if (!a.notice && b.notice) return 1;
        return 0;
    });

    const categories = [
        { name: '공지사항', emoji: '📢' },
        { name: '즐겨찾는 게시판', emoji: '⭐' },
        { name: '함께해요', emoji: '🤝' },
        { name: '소통', emoji: '💬' },
    ];

    const toggleCategory = (category: string) => {
        setExpandedCategory(prev => (prev === category ? null : category));
    };

    // 게시글 클릭 시 상세 페이지 이동
    const handlePostClick = (id: number) => {
        navigate(`/post/${id}`);
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
                                        {sortedPosts
                                            .filter(post =>
                                                category.name === '즐겨찾는 게시판'
                                                    ? post.favorite
                                                    : post.category === category.name
                                            )
                                            .filter(post => !hideNotices || !post.notice)
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
                    <span>총 {sortedPosts.length}개의 글</span>
                    <HideNoticeButton onClick={handleToggleNotices}>
                        {hideNotices ? '공지 보기' : '공지 숨기기'}
                    </HideNoticeButton>
                </Header>

                <PostList>
                    {sortedPosts.map(post => {
                        if (hideNotices && post.notice) return null;
                        return (
                            <PostItem
                                key={post.id}
                                $notice={post.notice}
                                onClick={() => handlePostClick(post.id)} >
                                <Tag>{post.tag}</Tag>
                                <span>{post.title}</span>
                            </PostItem>
                        );
                    })}
                </PostList>
            </ContentArea>

            <CloseButton onClick={handleCloseClick}>
                <CloseIconImg src={CloseIcon} alt="닫기" />
            </CloseButton>
        </Container>
    );
}

export default Post;
