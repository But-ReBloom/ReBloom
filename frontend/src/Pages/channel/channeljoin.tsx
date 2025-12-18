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

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setForm({ ...form, [name]: value });
    };

    const handleSubmit = async () => {
        if (!form.channelTitle || !form.userEmail) {
            setStatus('채널 이름과 이메일은 필수입니다.');
            return;
        }

        setLoading(true);

        const localChannels = JSON.parse(localStorage.getItem('channels') || '[]');

        const newChannel = {
            channelId: Date.now(), // 임시 ID
            channelName: form.channelTitle,
            channelIntro: form.channelIntro,
            channelDescription: form.channelDescription,
            userId: form.userEmail,
            requestType: 'CREATE', // 승인 목록에 표시
            channelStatus: 'PENDING',
        };

        localStorage.setItem('channels', JSON.stringify([...localChannels, newChannel]));
        // setStatus(`✅ 채널 생성 완료: ${form.channelTitle}`);
        setForm({ channelTitle: '', channelIntro: '', channelDescription: '', userEmail: '' });

        setLoading(false);
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

                    <SubmitButton onClick={handleSubmit} disabled={loading}>
                        {loading ? '생성 중...' : '채널 생성하기'}
                    </SubmitButton>
                    {status && <p style={{ marginTop: '12px' }}>{status}</p>}
                </FormWrapper>
            </CentralBox>
        </CommunityWrapper>
    );
}

export default ChannelJoin;
