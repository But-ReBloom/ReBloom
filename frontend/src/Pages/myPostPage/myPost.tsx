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
} from './MYP';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import { posts } from '../postPage/posts';

function MyPostPage() {
    const navigate = useNavigate();
    const [expandedCategory, setExpandedCategory] = useState<string | null>(null);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');

    const handleCloseClick = () => navigate('/');
    const handleClear = () => {
        setTitle('');
        setContent('');
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
                                        {posts
                                            .filter(post =>
                                                category.name === '즐겨찾는 게시판'
                                                    ? post.favorite
                                                    : post.category === category.name
                                            )
                                            .map(post => (
                                                <li key={post.id}>ㄴ {post.title}</li>
                                            ))}
                                    </SubMenu>
                                )}
                            </li>
                        ))}
                    </ul>
                </NavMenu>
            </Sidebar>

            <PostEditorContainer>
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
                    <button onClick={handleClear} style={{ backgroundColor: '#ff6b6b' }}>
                        지우기
                    </button>
                    <button>작성 완료</button>
                </div>
            </PostEditorContainer>

            <CloseButton onClick={handleCloseClick}>
                <CloseIconImg src={CloseIcon} alt="닫기" />
            </CloseButton>
        </Container>
    );
}

export default MyPostPage;
