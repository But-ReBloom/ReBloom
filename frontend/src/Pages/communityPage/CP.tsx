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

interface Channel {
    channelId: number;
    channelName: string;
    channelIntro: string;
}

// 더미 채널 데이터 (시현용)
const dummyChannels: Channel[] = [
    {
        channelId: 1,
        channelName: '러닝 크루',
        channelIntro: '함께 달리며 건강을 챙기는 러닝 커뮤니티입니다.',
    },
    {
        channelId: 2,
        channelName: '홈쿠킹 연구소',
        channelIntro: '집에서 새로운 요리 레시피를 공유하는 공간입니다.',
    },
    {
        channelId: 3,
        channelName: '독서 모임',
        channelIntro: '매주 책을 읽고 이야기를 나누는 독서 동아리입니다.',
    },
    {
        channelId: 4,
        channelName: '사진 찍는 사람들',
        channelIntro: '일상의 순간을 카메라에 담는 사진 동호회입니다.',
    }
];

function CommunityPage() {
    const navigate = useNavigate();
    const [channels, setChannels] = useState<Channel[]>([]);
    const [joinedChannels, setJoinedChannels] = useState<number[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // 더미 데이터 사용 (시현용)
        setLoading(true);
        setTimeout(() => {
            setChannels(dummyChannels);
            setLoading(false);
        }, 300); // 살짝 로딩 효과
        
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
