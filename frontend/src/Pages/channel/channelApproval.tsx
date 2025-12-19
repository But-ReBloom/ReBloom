import { useState, useEffect } from 'react';
import {
    CommunityWrapper,
    LogoImage,
    CloseButton,
    CloseIconImg,
    CentralBox,
    HeaderTop,
    SubmitButton,
} from './cap';
import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import { useNavigate } from 'react-router-dom';
import { channelApi } from '../../api/channel';

interface ChannelRequest {
    channelId: number;
    channelName: string;
    channelIntro: string;
    channelDescription?: string;
    userId: string;
    requestType: 'JOIN' | 'CREATE';
    channelStatus: 'WAITING' | 'APPROVED' | 'REJECTED';
}

function ChannelApproval() {
    const navigate = useNavigate();
    const [requests, setRequests] = useState<ChannelRequest[]>([]);
    const [expandedId, setExpandedId] = useState<number | null>(null);

    useEffect(() => {
        const fetchPendingChannels = async () => {
            try {
                const response = await channelApi.getPendingChannels();
                if (response.success && response.data?.responses) {
                    const channels: ChannelRequest[] = response.data.responses.map((item) => ({
                        channelId: item.channelId,
                        channelName: item.channelName,
                        channelIntro: item.channelIntro,
                        channelDescription: '',
                        userId: item.userId,
                        requestType: 'CREATE', 
                        channelStatus: 'WAITING',
                    }));
                    setRequests(channels);
                }
            } catch (err) {
                console.error('채널 조회 실패', err);
            }
        };

        fetchPendingChannels();
    }, []);

    const toggleExpand = (id: number) => {
        setExpandedId(prev => (prev === id ? null : id));
    };

    const handleApprove = async (channelId: number) => {
        try {
            const response = await channelApi.approveChannel(channelId);
            if (response.success) {
                setRequests(prev =>
                    prev.map(req =>
                        req.channelId === channelId ? { ...req, channelStatus: 'APPROVED' } : req
                    )
                );
            }
        } catch (err) {
            console.error('승인 실패', err);
        }
    };

    const handleReject = async (channelId: number) => {
        try {
            const response = await channelApi.rejectChannel(channelId);
            if (response.success) {
                setRequests(prev =>
                    prev.map(req =>
                        req.channelId === channelId ? { ...req, channelStatus: 'REJECTED' } : req
                    )
                );
            }
        } catch (err) {
            console.error('거절 실패', err);
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
                    <h2>채널 승인</h2>
                </HeaderTop>

                {requests.length === 0 && <p>승인 대기 중인 채널이 없습니다.</p>}

                {requests.map(req => (
                    <div
                        key={req.channelId}
                        style={{
                            border: '1px solid #ccc',
                            borderRadius: '12px',
                            padding: '14px 20px',
                            marginBottom: '10px',
                            backgroundColor:
                                req.channelStatus === 'APPROVED'
                                    ? '#e0ffe0'
                                    : req.channelStatus === 'REJECTED'
                                    ? '#ffe0e0'
                                    : '#fff',
                            cursor: 'pointer',
                        }}
                    >
                        <div
                            onClick={() => toggleExpand(req.channelId)}
                            style={{ display: 'flex', justifyContent: 'space-between', fontWeight: '700' }}
                        >
                            {req.channelName}
                            <span>{expandedId === req.channelId ? '▲' : '▼'}</span>
                        </div>

                        {expandedId === req.channelId && (
                            <div style={{ marginTop: '10px', fontSize: '14px' }}>
                                <div>소개: {req.channelIntro}</div>
                                <div>신청자: {req.userId}</div>
                                {req.channelDescription && <div>상세: {req.channelDescription}</div>}

                                <div style={{ marginTop: '10px', display: 'flex', gap: '10px' }}>
                                    {req.channelStatus === 'WAITING' && (
                                        <>
                                            <SubmitButton status="approve" onClick={() => handleApprove(req.channelId)}>승인</SubmitButton>
                                            <SubmitButton status="reject" onClick={() => handleReject(req.channelId)}>거절</SubmitButton>
                                        </>
                                    )}
                                    {req.channelStatus === 'APPROVED' && <span>승인됨</span>}
                                    {req.channelStatus === 'REJECTED' && <span>거절됨</span>}
                                </div>
                            </div>
                        )}
                    </div>
                ))}
            </CentralBox>
        </CommunityWrapper>
    );
}

export default ChannelApproval;
