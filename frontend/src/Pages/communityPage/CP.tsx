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
    CommentsList,
    CommentItem,
    } from './style';

    import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
    import CloseIcon from '../../assets/images/close.svg';
    import { useNavigate } from 'react-router-dom';
    import type { Post } from '../../types/PostTypes';

    function Community() {
    const navigate = useNavigate();

    const handleCloseClick = () => {
        navigate('/main');
    };

    const leftPosts: Post[] = [
        { id: 1, title: '모여라 활잽이들', description: '설명' },
        { id: 2, title: '모여라 활잽이들', description: '설명' },
        { id: 3, title: '모여라 활잽이들', description: '설명' },
        { id: 4, title: '모여라 활잽이들', description: '설명' },
    ];

    const rightPosts: Post[] = [
        {
        id: 101,
        title: '모여라 활잽이들',
        comments: ['안녕하세요', '안녕하세요', '반갑습니다'],
        },
        {
        id: 102,
        title: '모여라 활잽이들',
        comments: ['댓글1', '댓글2', '댓글3', '댓글4'],
        },
    ];

    return (
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
                {leftPosts.map(({ id, title, description }) => (
                <LeftPostItem key={id}>
                    <PostTitle>{title}</PostTitle>
                    <PostDescription>{description}</PostDescription>
                </LeftPostItem>
                ))}
            </LeftColumn>

            <RightColumn>
                {rightPosts.map(({ id, title, comments }) => (
                <RightPostItem key={id}>
                    <PostTitle>{title}</PostTitle>
                    <CommentsList>
                    {comments?.map((comment, i) => (
                        <CommentItem key={i}>{comment}</CommentItem>
                    ))}
                    </CommentsList>
                </RightPostItem>
                ))}
            </RightColumn>
            </ContentWrapper>
        </CentralBox>
        </CommunityWrapper>
    );
}

export default Community;
