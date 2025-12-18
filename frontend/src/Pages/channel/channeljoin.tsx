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
    CustomHobbyInputWrapper,
    StatusMessage,
    } from './cj';

    import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
    import CloseIcon from '../../assets/images/close.svg';
    import { useNavigate } from 'react-router-dom';
    import { useState } from 'react';

    interface ChannelForm {
    channelTitle: string;
    channelIntro: string;
    channelDescription: string;
    userEmail: string;
    }

    function ChannelJoin() {
    const navigate = useNavigate();
    const [form, setForm] = useState<ChannelForm>({
        channelTitle: '',
        channelIntro: '',
        channelDescription: '',
        userEmail: '',
    });
    const [status, setStatus] = useState<string>('');
    const [loading, setLoading] = useState(false);

    const hobbyOptions = ['러닝', '요리', '독서', '기타'];
    const [selectedHobbies, setSelectedHobbies] = useState<string[]>([]);
    const [customHobby, setCustomHobby] = useState('');
    const [showCustomInput, setShowCustomInput] = useState(false);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleHobbyClick = (hobby: string) => {
        if (hobby === '기타') {
        setShowCustomInput(true);
        return;
        }
        if (selectedHobbies.includes(hobby)) return;
        setSelectedHobbies([...selectedHobbies, hobby]);
    };

    const handleAddCustomHobby = () => {
        const hobby = customHobby.trim();
        if (hobby && !selectedHobbies.includes(hobby)) {
        setSelectedHobbies([...selectedHobbies, hobby]);
        }
        setCustomHobby('');
        setShowCustomInput(false);
    };

    const handleRemoveHobby = (hobby: string) => {
        setSelectedHobbies(selectedHobbies.filter((h) => h !== hobby));
    };

    const handleSubmit = async () => {
        if (!form.channelTitle || !form.userEmail) {
        setStatus('채널 이름과 이메일은 필수입니다.');
        return;
        }

        setLoading(true);

        const localChannels = JSON.parse(localStorage.getItem('channels') || '[]');

        const newChannel = {
        channelId: Date.now(),
        channelName: form.channelTitle,
        channelIntro: form.channelIntro,
        channelDescription: form.channelDescription,
        userEmail: form.userEmail,
        hobbies: selectedHobbies,
        requestType: 'CREATE',
        channelStatus: 'PENDING',
        };

        localStorage.setItem('channels', JSON.stringify([...localChannels, newChannel]));
        setForm({ channelTitle: '', channelIntro: '', channelDescription: '', userEmail: '' });
        setSelectedHobbies([]);
        setLoading(false);
        setStatus('채널 생성 요청이 완료되었습니다.');
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
            {/* <Input
                name="userEmail"
                placeholder="사용자 이메일"
                value={form.userEmail}
                onChange={handleChange}
            /> */}

            <HobbySelectorWrapper>
                {hobbyOptions.map((hobby) => (
                <HobbyOption
                    key={hobby}
                    selected={selectedHobbies.includes(hobby)}
                    onClick={() => handleHobbyClick(hobby)}
                >
                    {hobby}
                </HobbyOption>
                ))}
            </HobbySelectorWrapper>

            {showCustomInput && (
                <CustomHobbyInputWrapper>
                <input
                    type="text"
                    placeholder="직접 입력"
                    value={customHobby}
                    onChange={(e) => setCustomHobby(e.target.value)}
                />
                <button type="button" onClick={handleAddCustomHobby}>
                    추가
                </button>
                </CustomHobbyInputWrapper>
            )}

            <HobbySelectorWrapper>
                {selectedHobbies.map((hobby) => (
                <HobbyOption key={hobby} selected={true} onClick={() => handleRemoveHobby(hobby)}>
                    {hobby} ✕
                </HobbyOption>
                ))}
            </HobbySelectorWrapper>

            <SubmitButton onClick={handleSubmit} disabled={loading}>
                {loading ? '생성 중...' : '채널 생성하기'}
            </SubmitButton>

            {status && <StatusMessage>{status}</StatusMessage>}
            </FormWrapper>
        </CentralBox>
        </CommunityWrapper>
    );
    }

    export default ChannelJoin;
