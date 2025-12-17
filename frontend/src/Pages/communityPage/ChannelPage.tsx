// ChannelPage.tsx
import {
    Container,
    LogoImage,
    Sidebar,
    CloseButton,
    CloseIconImg,
    ContentArea,
    BackButton,
    ChannelTitle,
    ChannelIntro,
    ChannelDescription,
    HobbyTag,
    JoinChannelButton,
    SearchBox,
    ProfileSection,
    WritePostButton,
} from './cstyle';

import RebloomLogo from '../../assets/images/Rebloom-logo.svg';
import CloseIcon from '../../assets/images/close.svg';
import { useNavigate, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';

interface Channel {
    channelId: number;
    channelName: string;
    channelIntro: string;
    description?: string;
    hobbies?: string[];
    joined?: boolean;
}

const mockChannels: Channel[] = [
    {
        channelId: 1,
        channelName: '러닝 크루',
        channelIntro: '함께 달리는 러닝 커뮤니티',
        description: '주말마다 함께 달리며 건강을 챙기는 크루입니다.',
        hobbies: ['러닝', '운동', '건강'],
        joined: false,
    },
    {
        channelId: 2,
        channelName: '홈쿠킹 연구소',
        channelIntro: '집에서 요리하는 사람들',
        description: '집에서 새로운 요리 레시피를 공유하는 공간입니다.',
        hobbies: ['요리', '베이킹', '레시피'],
        joined: true,
    },
];

function ChannelPage() {
    const { id } = useParams<{ id: string }>();
    const navigate = useNavigate();
    const [channel, setChannel] = useState<Channel | null>(null);

    useEffect(() => {
        const localChannels = JSON.parse(localStorage.getItem('channels') || '[]');
        const approvedChannels: Channel[] = localChannels
            .filter((ch: any) => ch.channelStatus === 'APPROVED' && ch.requestType === 'CREATE')
            .map((ch: any) => ({
                channelId: ch.channelId,
                channelName: ch.channelName,
                channelIntro: ch.channelIntro,
                description: ch.description || '',
                hobbies: ch.hobbies || [],
                joined: ch.joined || false,
            }));

        const channelMap = new Map<number, Channel>();
        mockChannels.forEach(ch => channelMap.set(ch.channelId, ch));
        approvedChannels.forEach(ch => channelMap.set(ch.channelId, ch));

        const combinedChannels = Array.from(channelMap.values());
        const currentChannel = combinedChannels.find(ch => String(ch.channelId) === id);

        if (currentChannel) {
            setChannel(currentChannel);
        } else {
            setChannel({
                channelId: Number(id),
                channelName: '알 수 없는 채널',
                channelIntro: '',
                description: '',
                hobbies: [],
                joined: false,
            });
        }
    }, [id]);

    if (!channel) return <p>로딩중...</p>;

    return (
        <Container>
            <Sidebar>
                <LogoImage src={RebloomLogo} alt="Rebloom Logo" onClick={() => navigate('/')} />
                <BackButton onClick={() => navigate('/community')}>← 커뮤니티로 돌아가기</BackButton>
                <SearchBox>
                    <input placeholder="채널 검색..." />
                </SearchBox>
                <ProfileSection>
                    <img src="https://via.placeholder.com/60" alt="프로필" />
                    <div>
                        <strong>사용자 이름</strong>
                        <p>사용자 소개</p>
                    </div>
                </ProfileSection>
                <WritePostButton
                    onClick={() =>
                        navigate('/myPostPage', {
                            state: { channelId: channel.channelId, channelName: channel.channelName },
                        })
                    }
                >
                    글 작성
                </WritePostButton>
            </Sidebar>
            <ContentArea>
                <CloseButton onClick={() => navigate('/main')}>
                    <CloseIconImg src={CloseIcon} alt="닫기" />
                </CloseButton>
                <ChannelTitle>{channel.channelName}</ChannelTitle>
                <ChannelIntro>{channel.channelIntro}</ChannelIntro>
                <ChannelDescription>{channel.description}</ChannelDescription>
                <div>
                    {channel.hobbies?.map((hobby, idx) => (
                        <HobbyTag key={idx}>{hobby}</HobbyTag>
                    ))}
                </div>
                <JoinChannelButton joined={channel.joined}>
                    {channel.joined ? '가입됨' : '가입하기'}
                </JoinChannelButton>
            </ContentArea>
        </Container>
    );
}

export default ChannelPage;
