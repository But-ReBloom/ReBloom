import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import {
    Container,
    CloseButton,
    CloseIconImg,
    LogoImage,
    Sidebar,
    Divider,
    CafeInfo,
    ProfileSection,
    WritePostButton,
    SearchBox,
    NavMenu,
    SubMenu,
    PostEditorContainer,
    CategorySelectWrapper,
    CategorySelect,
} from './MYP';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
// import { posts as initialPosts } from '../postPage/posts';
import { postApi } from '../../api/post';

function MyPostPage() {
    const navigate = useNavigate();
    const [expandedCategory, setExpandedCategory] = useState<string | null>(null);

    const [selectedCategory, setSelectedCategory] = useState('소통');
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleCloseClick = () => navigate('/');
    const handleClear = () => {
        setTitle('');
        setContent('');
    };

    const handleSavePost = async () => {
        if (!title.trim() || !content.trim()) {
            alert('제목과 내용을 입력해주세요.');
            return;
        }

        try {
            // Map category to channelId (placeholder logic)
            let channelId = 1; 
            
            const response = await postApi.createPost({
                userId: "testUser", // Placeholder user ID
                channelId: channelId,
                postTitle: title,
                postContent: content,
                postType: 'NORMAL'
            });

            if (response.success) {
                handleClear();
                navigate('/post');
            } else {
                alert('게시글 작성 실패');
            }
        } catch (error) {
            console.error("Failed to create post", error);
            alert('게시글 작성 중 오류가 발생했습니다.');
        }
    };

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
                <LogoImage
                    src={RebloomLogo}
                    alt="Rebloom Logo"
                    onClick={() => navigate('/')}
                />
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

                <WritePostButton onClick={() => navigate('/post')}>
                    게시글 보기
                </WritePostButton>

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
                                        {/* Sidebar posts list removed for now */}
                                    </SubMenu>

                                )}
                            </li>
                        ))}
                    </ul>
                </NavMenu>
            </Sidebar>

            <PostEditorContainer>
                <CategorySelectWrapper>
                    <CategorySelect
                        value={selectedCategory}
                        onChange={(e) => setSelectedCategory(e.target.value)}
                    >
                        {categories.map(c => (
                            <option key={c.name} value={c.name}>
                                {c.emoji} {c.name}
                            </option>
                        ))}
                    </CategorySelect>
                </CategorySelectWrapper>

                <h2>게시글 작성</h2>

                <input
                    type="text"
                    placeholder="제목을 입력하세요"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                />

                <textarea
                    placeholder="내용을 입력하세요"
                    rows={15}
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                ></textarea>

                <div style={{ display: 'flex', gap: '10px', justifyContent: 'flex-end' }}>
                    <button
                        onClick={handleClear}
                        style={{ backgroundColor: '#ff6b6b' }}
                    >
                        지우기
                    </button>
                    <button
                        onClick={handleSavePost}
                        style={{ backgroundColor: '#5db9ee', color: '#fff' }}
                    >
                        작성 완료
                    </button>
                </div>
            </PostEditorContainer>

            <CloseButton onClick={handleCloseClick}>
                <CloseIconImg src={CloseIcon} alt="닫기" />
            </CloseButton>
        </Container>
    );
}

export default MyPostPage;
