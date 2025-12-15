import * as S from "./style.ts";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";

export default function Ferb() {
    const navigate = useNavigate();

    const users = [
        { id: "testuser", password: "1234", email: "testuser@example.com" },
        { id: "yongjun", password: "abcd", email: "yongjun@example.com" },
        { id: "guest", password: "guest", email: "guest@sample.com" },
    ];

    const [userID, setUserid] = useState("");
    const [userEmail, setEmail] = useState("");
    const [userChangedPassword_a, setChangedPassword_a] = useState("");
    const [userChangedPassword_b, setChangedPassword_b] = useState("");
    

    const handleSubmit = () => {
        if(userEmail.trim() && userChangedPassword_a.trim() && userID.trim() && userChangedPassword_b.trim()){
            const user = users.find(
                (u) => u.id === userID && u.email === userEmail
            );
    
            if (user && userChangedPassword_b === userChangedPassword_a) {
                toast.success(`비밀번호가 변경되었습니다!`);
            } else if (!user && userChangedPassword_b === userChangedPassword_a) {
                toast.error(<>기존 정보가 올바르지 않습니다.</>);
            } else if (user && userChangedPassword_b !== userChangedPassword_a) {
                toast.error(<>변경된 비밀번호가 서로 일치하지 않습니다!</>);
            } else {
                toast.error(<>알맞은 정보가 없습니다.</>);
            }
        }else{
            toast.error(<>빈 칸이 있어선 안됩니다.</>);
        }
    };


    // 회원가입_오른쪽박스
    return (
        <>
        <S.LoginContainer>
            <S.LoginTextBox>
            <S.Title>Find my password</S.Title>
            {/* <S.Subtitle>Please take a moment to find password</S.Subtitle> */}
            </S.LoginTextBox>

            <S.InputBox>
            <S.InputID>
                <S.InputLabel>Email</S.InputLabel>
                <S.Input
                type="text"
                value={userEmail}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="Enter your Email"
                />
            </S.InputID>
            <S.InputID>
                <S.InputLabel>ID</S.InputLabel>
                <S.Input
                type="text"
                value={userID}
                onChange={(e) => setUserid(e.target.value)}
                placeholder="Enter your ID"
                autoFocus
                />
            </S.InputID>
            <S.InputID>
                <S.InputLabel>Change password</S.InputLabel>
                <S.Input
                type="password"
                value={userChangedPassword_b}
                onChange={(e) => setChangedPassword_b(e.target.value)}
                placeholder="Enter your changed password"
                />
            </S.InputID>
            <S.InputID>
                <S.InputLabel>One more check</S.InputLabel>
                <S.Input
                type="password"
                value={userChangedPassword_a}
                onChange={(e) => setChangedPassword_a(e.target.value)}
                placeholder="Enter your changed password"
                />
            </S.InputID>
            </S.InputBox>

            <S.Send onClick={handleSubmit} type="button">
            Send
            </S.Send>
            <S.gotoLogin onClick={() => { navigate('/login'); }}>
                Go to Login
            </S.gotoLogin>
        </S.LoginContainer>
        <ToastContainer position="top-right" autoClose={3000} />
        </>
    );
}
