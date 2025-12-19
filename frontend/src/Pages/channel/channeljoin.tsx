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
    Select,
} from './cj';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import { useNavigate } from 'react-router-dom';
import { useState, useEffect } from 'react';
import { channelApi } from '../../api/channel';
import { hobbyApi } from '../../api/hobby';
import type { GetHobbyResponse } from '../../types/hobby';
import type { CreateChannelRequest } from '../../types/channel';

interface ChannelForm {
    channelTitle: string;
    channelIntro: string;
    channelDescription: string;
    channelLinkedHobby1: number;
    channelLinkedHobby2?: number;
    channelLinkedHobby3?: number;
}

function ChannelJoin() {
    const navigate = useNavigate();
    const [form, setForm] = useState<ChannelForm>({
        channelTitle: '',
        channelIntro: '',
        channelDescription: '',
        channelLinkedHobby1: 0,
        channelLinkedHobby2: undefined,
        channelLinkedHobby3: undefined,
    });
    const [status, setStatus] = useState<string>('');
    const [loading, setLoading] = useState(false);
    const [hobbies, setHobbies] = useState<GetHobbyResponse[]>([]);

    useEffect(() => {
        const fetchHobbies = async () => {
            try {
                const response = await hobbyApi.findAllHobbies();
                if (response.success && response.data) {
                    setHobbies(response.data.hobbies);
                }
            } catch (error) {
                console.error('취미 목록 조회 실패:', error);
            }
        };
        fetchHobbies();
    }, []);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
        const { name, value } = e.target;
        if (name.startsWith('channelLinkedHobby')) {
            setForm({ ...form, [name]: value ? Number(value) : undefined });
        } else {
            setForm({ ...form, [name]: value });
        }
    };

    const handleSubmit = async () => {
        if (!form.channelTitle || !form.channelIntro || !form.channelDescription) {
            setStatus('채널 이름, 소개, 상세 설명은 필수입니다.');
            return;
        }

        if (!form.channelLinkedHobby1) {
            setStatus('최소 하나의 취미를 선택해주세요.');
            return;
        }

        setLoading(true);
        setStatus('');

        try {
            const request: CreateChannelRequest = {
                channelTitle: form.channelTitle,
                channelIntro: form.channelIntro,
                channelDescription: form.channelDescription,
                channelLinkedHobby1: form.channelLinkedHobby1,
                channelLinkedHobby2: form.channelLinkedHobby2,
                channelLinkedHobby3: form.channelLinkedHobby3,
            };

            const response = await channelApi.createChannel(request);
            
            if (response.success) {
                setStatus('✅ 채널이 생성되었습니다!');
                setForm({
                    channelTitle: '',
                    channelIntro: '',
                    channelDescription: '',
                    channelLinkedHobby1: 0,
                    channelLinkedHobby2: undefined,
                    channelLinkedHobby3: undefined,
                });
                setTimeout(() => navigate('/community'), 2000);
            } else {
                setStatus('채널 생성에 실패했습니다. 다시 시도해주세요.');
            }
        } catch (error: any) {
            console.error('채널 생성 오류:', error);
            setStatus(error.message || '채널 생성 중 오류가 발생했습니다.');
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
                
                <label style={{ fontSize: '14px', fontWeight: '600', marginTop: '10px' }}>연관 취미 선택</label>
                <Select
                    name="channelLinkedHobby1"
                    value={form.channelLinkedHobby1 || ''}
                    onChange={handleChange}
                >
                    <option value="">첫 번째 취미 선택 (필수)</option>
                    {hobbies.map(hobby => (
                        <option key={hobby.hobbyId} value={hobby.hobbyId}>
                            {hobby.hobbyName}
                        </option>
                    ))}
                </Select>
                <Select
                    name="channelLinkedHobby2"
                    value={form.channelLinkedHobby2 || ''}
                    onChange={handleChange}
                >
                    <option value="">두 번째 취미 선택 (선택)</option>
                    {hobbies.map(hobby => (
                        <option key={hobby.hobbyId} value={hobby.hobbyId}>
                            {hobby.hobbyName}
                        </option>
                    ))}
                </Select>
                <Select
                    name="channelLinkedHobby3"
                    value={form.channelLinkedHobby3 || ''}
                    onChange={handleChange}
                >
                    <option value="">세 번째 취미 선택 (선택)</option>
                    {hobbies.map(hobby => (
                        <option key={hobby.hobbyId} value={hobby.hobbyId}>
                            {hobby.hobbyName}
                        </option>
                    ))}
                </Select>

                <SubmitButton onClick={handleSubmit} disabled={loading}>
                    {loading ? '생성 중...' : '채널 생성하기'}
                </SubmitButton>
                {status && <p style={{ marginTop: '12px', color: status.includes('✅') ? 'green' : 'red' }}>{status}</p>}
            </FormWrapper>
            </CentralBox>
        </CommunityWrapper>
    );
}

export default ChannelJoin;
