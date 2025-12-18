import {
    CommunityWrapper,
    LogoImage,
    CloseButton,
    CloseIconImg,
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

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';

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
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetchApprovedChannels();
    }, []);

    const fetchApprovedChannels = () => {
        const localChannels = JSON.parse(
            localStorage.getItem('channels') || '[]'
        );

        const approvedChannels: Channel[] = localChannels
            .filter(
                (ch: any) =>
                    ch.channelStatus === 'APPROVED' &&
                    ch.requestType === 'CREATE'
            )
            .map((ch: any) => ({
                channelId: ch.channelId,
                channelName: ch.channelName,
                channelIntro: ch.channelIntro,
            }));

        if (approvedChannels.length > 0) {
            setChannels(approvedChannels);
        } else {
            setChannels(mockChannels);
        }

        setLoading(false);
    };

    return (
        <CommunityWrapper>
            <CloseButton onClick={() => navigate('/main')}>
                <CloseIconImg src={CloseIcon} alt="닫기" />
            </CloseButton>

            <LogoImage
                src={RebloomLogo}
                alt="Rebloom Logo"
                onClick={() => navigate('/')}
            />

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
                        <Button onClick={() => navigate('/channelApproval')}>
                            채널 승인 →
                        </Button>
                    </RightButtons>
                </HeaderTop>

                <LeftColumn>
                    {loading && <p>로딩중...</p>}

                    {!loading && channels.length === 0 && (
                        <p>표시할 채널이 없습니다.</p>
                    )}

                    {!loading &&
                        channels.map(channel => (
                            <LeftPostItem
                            key={channel.channelId}
                            onClick={() => navigate(`/channel/${channel.channelId}`)}
                            style={{ cursor: 'pointer' }}
                            >
                            <PostTitle>{channel.channelName}</PostTitle>
                            <PostDescription>{channel.channelIntro}</PostDescription>
                            </LeftPostItem>

                        ))}
                </LeftColumn>
            </CentralBox>
        </CommunityWrapper>
    );
}

export default CommunityPage;