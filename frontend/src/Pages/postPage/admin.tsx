import { useState, useEffect } from 'react';
import styled from 'styled-components';

const RequestList = styled.ul`
    list-style: none;
    padding: 0;
`;

const RequestItem = styled.li`
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
`;

const Button = styled.button`
    margin-left: 5px;
`;

function AdminPage() {
    const [requests, setRequests] = useState<string[]>([]);

    useEffect(() => {
        const pending = JSON.parse(localStorage.getItem('pendingJoinRequests') || '[]');
        setRequests(pending);
    }, []);

    const handleApprove = (user: string) => {
        const joinedChannels = JSON.parse(localStorage.getItem('joinedChannels') || '[]');
        if (!joinedChannels.includes(user)) joinedChannels.push(user);
        localStorage.setItem('joinedChannels', JSON.stringify(joinedChannels));

        const updated = requests.filter(u => u !== user);
        setRequests(updated);
        localStorage.setItem('pendingJoinRequests', JSON.stringify(updated));
    };

    const handleReject = (user: string) => {
        const updated = requests.filter(u => u !== user);
        setRequests(updated);
        localStorage.setItem('pendingJoinRequests', JSON.stringify(updated));
    };

    return (
        <div>
            <h1>채널 가입 신청 목록</h1>
            <RequestList>
                {requests.map(user => (
                    <RequestItem key={user}>
                        <span>{user}</span>
                        <div>
                            <Button onClick={() => handleApprove(user)}>승인</Button>
                            <Button onClick={() => handleReject(user)}>거절</Button>
                        </div>
                    </RequestItem>
                ))}
            </RequestList>
        </div>
    );
}

export default AdminPage;
