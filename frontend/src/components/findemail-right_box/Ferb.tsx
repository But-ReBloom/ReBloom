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

    const [userid, setUserid] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = (event?: React.FormEvent) => {
        if (event) event.preventDefault();
        
        const user = users.find(
            (u) => u.id === userid && u.password === password
        );

        if (user) {
            toast.success(`환영합니다! ${user.id}님!`);
  
            setTimeout(() => {
                navigate("/login", { replace: true });
            }, 4500);
  
        } else  {
            toast.error("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
    };

    return (
        <>
        <S.LoginContainer>
            <S.LoginTextBox>
            <S.Title>Find my E-mail</S.Title>
            <S.Subtitle>Please take a moment to find ID</S.Subtitle>
            </S.LoginTextBox>

            <S.InputBox>
            <S.InputID>
                <S.InputLabel>ID</S.InputLabel>
                <S.Input
                type="text"
                value={userid}
                onChange={(e) => setUserid(e.target.value)}
                placeholder="Enter your ID"
                autoFocus
                />
            </S.InputID>
            <S.InputID>
                <S.InputLabel>Password</S.InputLabel>
                <S.Input
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Enter your password"
                />
            </S.InputID>
            </S.InputBox>

            <S.Send onClick={handleSubmit} type="button">
            Send
            </S.Send>
        </S.LoginContainer>
        <ToastContainer position="top-right" autoClose={3000} />
        </>
    );
}
