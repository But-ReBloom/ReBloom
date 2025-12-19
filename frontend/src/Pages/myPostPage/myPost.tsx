import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import {
    Container,
    CloseButton,
    CloseIconImg,
    LogoImage,
    Sidebar,
    ProfileSection,
    WritePostButton,
    PostEditorContainer,
    CategorySelectWrapper,
    CategorySelect,
    BackButton,
} from './MYP';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import React_svg from "../../assets/images/react.svg";
import { postApi } from '../../api/post';
import { authApi } from '../../api/auth';
import { channelApi } from '../../api/channel';
import type { FindUserInfoResponse } from '../../types/auth';

function MyPostPage() {
    const navigate = useNavigate();
    // const [expandedCategory, setExpandedCategory] = useState<string | null>(null);
    const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);
    const [selectedCategory, setSelectedCategory] = useState('소통');
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [loading, setLoading] = useState(false);

    const categories = [
        { name: '공지사항', emoji: '📢' },
        { name: '즐겨찾는 게시판', emoji: '⭐' },
        { name: '함께해요', emoji: '🤝' },
        { name: '소통', emoji: '💬' },
    ];

    useEffect(() => {
        const fetchUserInfo = async () => {
            try {
                const response = await authApi.findCurrentUser();
                if (response.success) setUserInfo(response.data);
            } catch (error) {
                console.error("사용자 정보 불러오기 실패", error);
            }
        };
        fetchUserInfo();
    }, []);

    // const toggleCategory = (category: string) => {
    //     setExpandedCategory(prev => (prev === category ? null : category));
    // };

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
        if (!userInfo) {
            alert('사용자 정보를 불러오는 중입니다. 잠시 후 다시 시도해주세요.');
            return;
        }

        setLoading(true);
        try {
            const channelRes = await channelApi.searchChannel({ keyword: selectedCategory });
            let channelId: number | null = null;

            if (channelRes.success && channelRes.data.responses && channelRes.data.responses.length > 0) {
                const matchIndex = channelRes.data.responses.findIndex((c: { channelName: string }) => c.channelName === selectedCategory);
                channelId = matchIndex >= 0 ? matchIndex + 1 : 1;
            }

            if (!channelId) {
                alert(`'${selectedCategory}' 채널을 찾을 수 없습니다.`);
                setLoading(false);
                return;
            }

            const response = await postApi.createPost({
                userId: userInfo.userId,
                channelId,
                postTitle: title,
                postContent: content,
                postType: 'NORMAL',
            });

            if (response.success) {
                handleClear();
                navigate('/post', { state: { channelId } });
            } else {
                alert('게시글 작성 실패');
            }
        } catch (error) {
            console.error("게시글 작성 오류", error);
            alert('게시글 작성 중 오류가 발생했습니다.');
        } finally {
            setLoading(false);
        }
    };
    

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

            <WritePostButton onClick={() => navigate(-1)}>
                채널로 돌아가기
            </WritePostButton>
                <BackButton onClick={() => navigate('/community')}>
                    ← 커뮤니티로 돌아가기
                </BackButton>


                {/* <NavMenu>
                    <ul>
                        {categories.map(category => (
                            <li key={category.name}>
                                <div onClick={() => toggleCategory(category.name)}>
                                    {category.emoji} {category.name}
                                </div>
                                {expandedCategory === category.name && <SubMenu />}
                            </li>
                        ))}
                    </ul>
                </NavMenu> */}
            </Sidebar>

            <PostEditorContainer>
                <CategorySelectWrapper>
                    <CategorySelect value={selectedCategory} onChange={e => setSelectedCategory(e.target.value)}>
                        {categories.map(c => (
                            <option key={c.name} value={c.name}>{c.emoji} {c.name}</option>
                        ))}
                    </CategorySelect>
                </CategorySelectWrapper>

                <h2>게시글 작성</h2>

                <input type="text" placeholder="제목을 입력하세요" value={title} onChange={e => setTitle(e.target.value)} />
                <textarea placeholder="내용을 입력하세요" rows={15} value={content} onChange={e => setContent(e.target.value)} />

                <div style={{ display: 'flex', gap: '10px', justifyContent: 'flex-end' }}>
                    <button onClick={handleClear} style={{ backgroundColor: '#ff6b6b' }}>지우기</button>
                    <button onClick={handleSavePost} style={{ backgroundColor: '#5db9ee', color: '#fff' }} disabled={loading}>
                        {loading ? '작성중...' : '작성 완료'}
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
