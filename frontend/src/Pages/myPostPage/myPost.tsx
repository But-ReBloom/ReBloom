import { useNavigate, useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import {
    Container,
    CloseButton,
    CloseIconImg,
    LogoImage,
    Sidebar,
    ProfileSection,
    WritePostButton,
    SearchBox,
    ContentArea,
    BackButton,
    PostEditorContainer,
    ActionButtonGroup,
    ClearButton,
    SubmitButton,
} from './MYP';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import React_svg from '../../assets/images/react.svg';

import { postApi } from '../../api/post';
import { authApi } from '../../api/auth';
import type { FindUserInfoResponse } from '../../types/auth';

function MyPostPage() {
    const navigate = useNavigate();
    const location = useLocation();

    // 🔹 ChannelPage에서 넘어온 channelId
    const channelId = location.state?.channelId as number | undefined;

    const [userInfo, setUserInfo] = useState<FindUserInfoResponse | null>(null);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [loading, setLoading] = useState(false);

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

    const handleClear = () => {
        setTitle('');
        setContent('');
    };

    const handleSavePost = async () => {
        if (!title.trim() || !content.trim()) {
            alert('제목과 내용을 입력해주세요.');
            return;
        }
        if (!userInfo || !channelId) {
            alert('채널 정보가 없습니다.');
            return;
        }

        setLoading(true);
        try {
            const response = await postApi.createPost({
                userId: userInfo.userId,
                channelId,
                postTitle: title,
                postContent: content,
                postType: 'NORMAL',
            });

            if (response.success) {
                handleClear();
                navigate(`/channel/${channelId}`);
            } else {
                alert('게시글 작성 실패');
            }
        } catch (error) {
            console.error(error);
            alert('게시글 작성 중 오류 발생');
        } finally {
            setLoading(false);
        }
    };

    return (
        <Container>
            <Sidebar>
                <LogoImage
                    src={RebloomLogo}
                    alt="Rebloom Logo"
                    onClick={() => navigate('/')}
                />

                <ProfileSection>
                    <img src={React_svg} alt="프로필" />
                    <div>
                        <strong>{userInfo?.userName || '사용자 이름'}</strong>
                        <p>
                            레벨{' '}
                            {userInfo
                                ? Math.floor(userInfo.userTierPoint / 1000) + 1
                                : 1}
                        </p>
                    </div>
                </ProfileSection>

                <WritePostButton
                    onClick={() => navigate(`/channel/${channelId}`)}
                >
                    채널로 돌아가기
                </WritePostButton>

                <SearchBox>
                    <input placeholder="채널 검색..." />
                </SearchBox>

                <BackButton onClick={() => navigate('/community')}>
                    ← 커뮤니티로 돌아가기
                </BackButton>
            </Sidebar>

            <ContentArea>
                <PostEditorContainer>
                    <h2>게시글 작성</h2>

                    <input
                        type="text"
                        placeholder="제목을 입력하세요"
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                    />

                    <textarea
                        placeholder="내용을 입력하세요"
                        rows={15}
                        value={content}
                        onChange={e => setContent(e.target.value)}
                    />

                    <ActionButtonGroup>
                        <ClearButton onClick={handleClear}>
                            지우기
                        </ClearButton>

                        <SubmitButton
                            onClick={handleSavePost}
                            disabled={loading}
                        >
                            {loading ? '작성중...' : '작성 완료'}
                        </SubmitButton>
                    </ActionButtonGroup>
                </PostEditorContainer>

                <CloseButton onClick={() => navigate('/')}>
                    <CloseIconImg src={CloseIcon} alt="닫기" />
                </CloseButton>
            </ContentArea>
        </Container>
    );
}

export default MyPostPage;
