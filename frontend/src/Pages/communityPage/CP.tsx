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

const mockChannels: Channel[] = [
    { channelId: 1, channelName: '러닝 크루', channelIntro: '함께 달리는 러닝 커뮤니티' },
    { channelId: 2, channelName: '홈쿠킹 연구소', channelIntro: '집에서 요리하는 사람들' },
];

function CommunityPage() {
    const navigate = useNavigate();
    const [channels, setChannels] = useState<Channel[]>([]);
    const [joinedChannels, setJoinedChannels] = useState<number[]>([]);

    useEffect(() => {
        setChannels(mockChannels);
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
                    {channels.map(channel => {
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
                    })}
                </LeftColumn>
            </CentralBox>
        </CommunityWrapper>
    );
}

export default CommunityPage;
