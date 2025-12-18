import {
    CommunityWrapper,
<<<<<<< HEAD
=======
    LogoImage,
    CloseButton,
    CloseIconImg,
>>>>>>> main
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

<<<<<<< HEAD
=======
import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
>>>>>>> main
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
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

    useEffect(() => {
        setChannels(mockChannels);
        const joined = JSON.parse(localStorage.getItem('joinedChannels') || '[]');
        setJoinedChannels(joined);
    }, []);

<<<<<<< HEAD
    const handleJoin = (channelId: number) => {
        if (joinedChannels.includes(channelId)) return;
=======
    const fetchApprovedChannels = async () => {
        try {
            const response = await channelApi.getApprovedChannels();
            if (response.success && response.data.responses) {
                const mappedChannels: Channel[] = response.data.responses.map((ch, idx) => ({
                    channelId: idx + 1,
                    channelName: ch.channelName,
                    channelIntro: ch.channelIntro || '',
                }));
                setChannels(mappedChannels);
            } else {
                setChannels([]);
            }
        } catch (error) {
            console.error('Failed to fetch approved channels', error);
            setChannels([]);
        } finally {
            setLoading(false);
        }
    };
>>>>>>> main

        const updated = [...joinedChannels, channelId];
        setJoinedChannels(updated);
        localStorage.setItem('joinedChannels', JSON.stringify(updated));
        alert('채널에 가입되었습니다!');
    };

    return (
        <CommunityWrapper>
<<<<<<< HEAD
            <Header />
=======
            <CloseButton onClick={() => navigate('/main')}>
                <CloseIconImg src={CloseIcon} alt="닫기" />
            </CloseButton>

            <LogoImage
                src={RebloomLogo}
                alt="Rebloom Logo"
                onClick={() => navigate('/')}
            />
>>>>>>> main

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
