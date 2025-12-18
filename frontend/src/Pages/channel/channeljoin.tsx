    import {
    CommunityWrapper,
    LogoImage,
    CloseButton,
    CloseIconImg,
    CentralBox,
    HeaderTop,
    FormWrapper,
    Input,
    TextArea,
    SubmitButton,
    HobbySelectorWrapper,
    HobbyOption,
    StatusMessage,
    } from './cj';

    import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
    import CloseIcon from '../../assets/images/close.svg';
    import { useNavigate } from 'react-router-dom';
    import { useState, useEffect } from 'react';
    import { channelApi } from '../../api/channel';
    import { hobbyApi } from '../../api/hobby';
    import { authApi } from '../../api/auth';
    import type { GetHobbyResponse } from '../../types/hobby';

    interface ChannelForm {
    channelTitle: string;
    channelIntro: string;
    channelDescription: string;
    }

    interface HobbyDisplay {
    hobbyId: number;
    hobbyName: string;
    }

    function ChannelJoin() {
    const navigate = useNavigate();
    const [form, setForm] = useState<ChannelForm>({
        channelTitle: '',
        channelIntro: '',
        channelDescription: '',
    });
    const [status, setStatus] = useState<string>('');
    const [statusType, setStatusType] = useState<'success' | 'error'>('error');
    const [loading, setLoading] = useState(false);

    const [hobbies, setHobbies] = useState<HobbyDisplay[]>([]);
    const [selectedHobbies, setSelectedHobbies] = useState<HobbyDisplay[]>([]);
    const [userEmail, setUserEmail] = useState<string>('');

    // 취미 목록 및 사용자 정보 가져오기
    useEffect(() => {
        const fetchData = async () => {
            try {
                // 사용자 정보 가져오기
                const userRes = await authApi.findCurrentUser();
                if (userRes.success) {
                    setUserEmail(userRes.data.userEmail);
                }
                
                // 취미 목록 가져오기
                const hobbyRes = await hobbyApi.findAllHobbies();
                if (hobbyRes.success && hobbyRes.data.hobbies) {
                    setHobbies(hobbyRes.data.hobbies.map((h: GetHobbyResponse) => ({
                        hobbyId: h.hobbyId,
                        hobbyName: h.hobbyName,
                    })));
                }
            } catch (error) {
                console.error('Failed to fetch data', error);
            }
        };
        fetchData();
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleHobbyClick = (hobby: HobbyDisplay) => {
        if (selectedHobbies.find(h => h.hobbyId === hobby.hobbyId)) {
            // 이미 선택됨 - 제거
            setSelectedHobbies(selectedHobbies.filter(h => h.hobbyId !== hobby.hobbyId));
        } else if (selectedHobbies.length < 3) {
            // 최대 3개까지 선택 가능
            setSelectedHobbies([...selectedHobbies, hobby]);
        } else {
            setStatus('취미는 최대 3개까지 선택 가능합니다.');
            setStatusType('error');
        }
    };

    const handleSubmit = async () => {
        if (!form.channelTitle || !form.channelIntro) {
            setStatus('채널 이름과 소개는 필수입니다.');
            setStatusType('error');
            return;
        }

        if (selectedHobbies.length === 0) {
            setStatus('최소 1개의 취미를 선택해주세요.');
            setStatusType('error');
            return;
        }

        if (!userEmail) {
            setStatus('로그인이 필요합니다.');
            setStatusType('error');
            navigate('/login');
            return;
        }

        setLoading(true);
        setStatus('');

        try {
            const response = await channelApi.createChannel({
                channelTitle: form.channelTitle,
                channelIntro: form.channelIntro,
                channelDescription: form.channelDescription || form.channelIntro,
                userEmail: userEmail,
                channelLinkedHobby1: selectedHobbies[0].hobbyId,
                channelLinkedHobby2: selectedHobbies[1]?.hobbyId,
                channelLinkedHobby3: selectedHobbies[2]?.hobbyId,
            });

            if (response.success && response.data.channelId) {
                // 채널 생성 후 자동 승인
                try {
                    await channelApi.approveChannel(response.data.channelId);
                } catch (approveError) {
                    console.error('Failed to auto-approve channel', approveError);
                    // 승인 실패해도 채널은 생성됨
                }
                
                setStatus('채널이 성공적으로 생성되었습니다!');
                setStatusType('success');
                setForm({ channelTitle: '', channelIntro: '', channelDescription: '' });
                setSelectedHobbies([]);
                
                // 2초 후 커뮤니티 페이지로 이동
                setTimeout(() => {
                    navigate('/community');
                }, 2000);
            } else {
                setStatus('채널 생성에 실패했습니다.');
                setStatusType('error');
            }
        } catch (error) {
            console.error('Failed to create channel', error);
            setStatus('채널 생성 중 오류가 발생했습니다.');
            setStatusType('error');
        } finally {
            setLoading(false);
        }
    };

    return (
        <CommunityWrapper>
        <CloseButton onClick={() => navigate('/main')}>
            <CloseIconImg src={CloseIcon} alt="닫기" />
        </CloseButton>

        <LogoImage src={RebloomLogo} alt="Rebloom Logo" onClick={() => navigate('/')} />

        <CentralBox>
            <HeaderTop>
            <h2>채널 생성</h2>
            </HeaderTop>

            <FormWrapper>
            <Input
                name="channelTitle"
                placeholder="채널 이름"
                value={form.channelTitle}
                onChange={handleChange}
            />
            <Input
                name="channelIntro"
                placeholder="채널 소개"
                value={form.channelIntro}
                onChange={handleChange}
            />
            <TextArea
                name="channelDescription"
                placeholder="채널 상세 설명"
                value={form.channelDescription}
                onChange={handleChange}
            />

            <p style={{ margin: '16px 0 8px', fontSize: '14px', color: '#666' }}>
                관련 취미 선택 (최대 3개)
            </p>
            <HobbySelectorWrapper style={{ flexWrap: 'wrap', maxHeight: '200px', overflowY: 'auto' }}>
                {hobbies.map((hobby) => (
                <HobbyOption
                    key={hobby.hobbyId}
                    selected={selectedHobbies.some(h => h.hobbyId === hobby.hobbyId)}
                    onClick={() => handleHobbyClick(hobby)}
                >
                    {hobby.hobbyName}
                </HobbyOption>
                ))}
            </HobbySelectorWrapper>

            {selectedHobbies.length > 0 && (
                <div style={{ margin: '12px 0' }}>
                    <p style={{ fontSize: '14px', color: '#333', marginBottom: '8px' }}>선택된 취미:</p>
                    <HobbySelectorWrapper>
                        {selectedHobbies.map((hobby) => (
                        <HobbyOption key={hobby.hobbyId} selected={true} onClick={() => handleHobbyClick(hobby)}>
                            {hobby.hobbyName} ✕
                        </HobbyOption>
                        ))}
                    </HobbySelectorWrapper>
                </div>
            )}

            <SubmitButton onClick={handleSubmit} disabled={loading}>
                {loading ? '생성 중...' : '채널 생성하기'}
            </SubmitButton>

            {status && <StatusMessage style={{ color: statusType === 'success' ? '#4caf50' : '#f44336' }}>{status}</StatusMessage>}
            </FormWrapper>
        </CentralBox>
        </CommunityWrapper>
    );
    }

    export default ChannelJoin;
