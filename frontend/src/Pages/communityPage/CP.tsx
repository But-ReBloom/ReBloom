import {
    CommunityWrapper,
    LogoImage,
    CloseButton,
    CloseIconImg,
    CentralBox,
    HeaderTop,
    SortDropdown,
    RightButtons,
    Button,
    ContentWrapper,
    LeftColumn,
    RightColumn,
    LeftPostItem,
    RightPostItem,
    PostTitle,
    PostDescription,
    } from './style';

    import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
    import CloseIcon from '../../assets/images/close.svg';
    import { useNavigate } from 'react-router-dom';
    import { useEffect, useState } from 'react';
    import { postApi } from '../../api/post';
    import type { CreatePostResponse } from '../../types/PostTypes';
    // import Header from '../../components/normal_header/nh';

    function Community() {
    const navigate = useNavigate();
    const [leftPosts, setLeftPosts] = useState<CreatePostResponse[]>([]);
    const [rightPosts, setRightPosts] = useState<CreatePostResponse[]>([]);

    const handleCloseClick = () => {
        navigate('/main');
    };

    useEffect(() => {
        const fetchData = async () => {
            try {
                const popularRes = await postApi.getPopularPosts().catch(() => null);
                if (popularRes && popularRes.success) {
                    setRightPosts(popularRes.data.posts);
                }

                const searchRes = await postApi.searchPosts({ keyword: "" }).catch(() => null);
                if (searchRes && searchRes.success) {
                    setLeftPosts(searchRes.data.posts);
                }
            } catch (error) {
                console.error("Failed to fetch posts", error);
            }
        };
        fetchData();
    }, []);

    return (
        <>
        {/* <Header/> */}
        <CommunityWrapper>
        <CloseButton onClick={handleCloseClick}>
            <CloseIconImg src={CloseIcon} alt="닫기" />
        </CloseButton>

        <LogoImage
        src={RebloomLogo}
        alt="Rebloom Logo"
        style={{ cursor: 'pointer' }}
        onClick={() => navigate('/')}
        />

        <CentralBox>
            <HeaderTop>
            <SortDropdown>
                <select>
                <option value="latest">인기순</option>
                <option value="popular">최신순</option>
                <option value="oldest">오래된순</option>
                </select>
            </SortDropdown>

            <RightButtons>
                <Button onClick={() => navigate('/myPostPage')}>내 게시글 →</Button>
                <Button onClick={() => navigate('/post')}>게시글 보기 →</Button>
            </RightButtons>
            </HeaderTop>

            <ContentWrapper>
            <LeftColumn>
                {leftPosts.map((post) => (
                <LeftPostItem key={post.postId} onClick={() => navigate(`/post/${post.postId}`)}>
                    <PostTitle>{post.postTitle}</PostTitle>
                    <PostDescription>{post.postContent}</PostDescription>
                </LeftPostItem>
                ))}
            </LeftColumn>

            <RightColumn>
                {rightPosts.map((post) => (
                <RightPostItem key={post.postId} onClick={() => navigate(`/post/${post.postId}`)}>
                    <PostTitle>{post.postTitle}</PostTitle>
                    <PostDescription>조회수: {post.viewers}</PostDescription>
                </RightPostItem>
                ))}
            </RightColumn>
            </ContentWrapper>
        </CentralBox>
        </CommunityWrapper>
        </>
    );
}

export default Community;
