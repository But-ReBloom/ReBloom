import {
    CommunityWrapper,
    CentralBox,
    HeaderTop,
    SortDropdown,
    RightButtons,
    Button,
    LeftColumn,
    LeftPostItem,
    PostTitle,
    PostDescription,
} from './style';

import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import Header from '../../components/mainpage-Header/mph';
import { channelApi } from '../../api/channel';

interface Channel {
    channelId: number;
    channelName: string;
    channelIntro: string;
}

function CommunityPage() {
    const navigate = useNavigate();
    const [channels, setChannels] = useState<Channel[]>([]);
    const [joinedChannels, setJoinedChannels] = useState<number[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchChannels = async () => {
            try {
                setLoading(true);
                const response = await channelApi.getAllChannels();
                if (response.success && response.data) {
                    const channelList = response.data.responses.map((ch) => ({
                        channelId: ch.channelId,
                        channelName: ch.channelName,
                        channelIntro: ch.channelIntro,
                    }));
                    setChannels(channelList);
                }
            } catch (error) {
                console.error('채널 목록 조회 실패:', error);
                // API 실패 시 빈 배열 유지
            } finally {
                setLoading(false);
            }
        };
        
        fetchChannels();
        const joined = JSON.parse(localStorage.getItem('joinedChannels') || '[]');
        setJoinedChannels(joined);
    }, []);

    const handleJoin = (channelId: number) => {
        if (joinedChannels.includes(channelId)) return;

        const updated = [...joinedChannels, channelId];
        setJoinedChannels(updated);
        localStorage.setItem('joinedChannels', JSON.stringify(updated));
        alert('채널에 가입되었습니다!');
    };

    return (
        <CommunityWrapper>
            <Header />

            <CentralBox>
                <HeaderTop>
                    <SortDropdown>
                        <select>
                            <option>인기순</option>
                            <option>최신순</option>
                        </select>
                    </SortDropdown>

                    <RightButtons>
                        <Button onClick={() => navigate('/channeljoin')}>
                            채널 생성 →
                        </Button>
                    </RightButtons>
                </HeaderTop>

                <LeftColumn>
                    {loading ? (
                        <p style={{ textAlign: 'center', padding: '20px' }}>채널 목록 로딩 중...</p>
                    ) : channels.length === 0 ? (
                        <p style={{ textAlign: 'center', padding: '20px' }}>등록된 채널이 없습니다.</p>
                    ) : (
                        channels.map(channel => {
                            const joined = joinedChannels.includes(channel.channelId);

                            return (
                                <LeftPostItem
                                    key={channel.channelId}
                                    style={{
                                        display: 'flex',
                                        justifyContent: 'space-between',
                                        alignItems: 'center',
                                    }}
                                >
                                    <div
                                        style={{ cursor: 'pointer' }}
                                        onClick={() =>
                                            navigate(`/channel/${channel.channelId}`)
                                        }
                                    >
                                        <PostTitle>{channel.channelName}</PostTitle>
                                        <PostDescription>
                                            {channel.channelIntro}
                                        </PostDescription>
                                    </div>

                                    <Button
                                        disabled={joined}
                                        onClick={() => handleJoin(channel.channelId)}
                                    >
                                        {joined ? '가입됨' : '가입하기'}
                                    </Button>
                                </LeftPostItem>
                            );
                        })
                    )}
                </LeftColumn>
            </CentralBox>
        </CommunityWrapper>
    );
}

export default CommunityPage;
