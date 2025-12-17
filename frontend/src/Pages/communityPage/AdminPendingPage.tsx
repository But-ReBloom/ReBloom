    import { useEffect, useState } from 'react';
    import styled from 'styled-components';

    const Container = styled.div`
    padding: 24px;
    `;

    const Table = styled.table`
    width: 100%;
    border-collapse: collapse;
    `;

    const Th = styled.th`
    border-bottom: 1px solid #ccc;
    padding: 8px;
    text-align: left;
    `;

    const Td = styled.td`
    border-bottom: 1px solid #eee;
    padding: 8px;
    `;

    const ActionButton = styled.button<{ approve?: boolean }>`
    margin-right: 8px;
    padding: 6px 12px;
    background-color: ${props => (props.approve ? '#5db9ee' : '#aaa')};
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    &:hover {
        background-color: ${props => (props.approve ? '#2078ab' : '#888')};
    }
    `;

    interface PendingRequest {
    channelName: string;
    userId: string;
    requestedAt: string;
    }

    function AdminPendingPage() {
    const [pendingList, setPendingList] = useState<PendingRequest[]>([]);

    useEffect(() => {
        const fetchPending = async () => {
        try {
            // 서버 호출 (실제)
            // const res = await fetch('/channel/admin/find/pending', { headers: { Authorization: `Bearer ${token}` } });
            // const json = await res.json();
            // setPendingList(json.data);

            // mock
            const mock = JSON.parse(localStorage.getItem('pendingJoinRequests') || '[]');
            setPendingList(mock.map((ch: string, i: number) => ({
            channelName: ch,
            userId: `user${i+1}`,
            requestedAt: new Date().toISOString().split('T')[0]
            })));
        } catch (err) {
            console.error(err);
        }
        };

        fetchPending();
    }, []);

    const handleApprove = (channelName: string) => {
        alert(`${channelName} 가입 승인`);
        // 서버 PUT 요청
        setPendingList(prev => prev.filter(req => req.channelName !== channelName));
    };

    const handleReject = (channelName: string) => {
        alert(`${channelName} 가입 거부`);
        // 서버 PUT 요청
        setPendingList(prev => prev.filter(req => req.channelName !== channelName));
    };

    return (
        <Container>
        <h1>가입 신청 목록 (관리자)</h1>
        <Table>
            <thead>
            <tr>
                <Th>채널</Th>
                <Th>신청자</Th>
                <Th>신청일</Th>
                <Th>액션</Th>
            </tr>
            </thead>
            <tbody>
            {pendingList.map(req => (
                <tr key={req.channelName + req.userId}>
                <Td>{req.channelName}</Td>
                <Td>{req.userId}</Td>
                <Td>{req.requestedAt}</Td>
                <Td>
                    <ActionButton approve onClick={() => handleApprove(req.channelName)}>승인</ActionButton>
                    <ActionButton onClick={() => handleReject(req.channelName)}>거부</ActionButton>
                </Td>
                </tr>
            ))}
            </tbody>
        </Table>
        </Container>
    );
    }

    export default AdminPendingPage;
