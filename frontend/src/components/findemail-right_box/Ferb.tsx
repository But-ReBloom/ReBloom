import * as S from "./style";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function Ferb() {
    const navigate = useNavigate();

    const users = [
    { id: "testuser", password: "1234", email: "testuser@example.com" },
    { id: "yongjun", password: "abcd", email: "yongjun@example.com" },
    { id: "guest", password: "guest", email: "guest@sample.com" },
    ];

    const [userID, setUserid] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = () => {
    if (userID.trim() && password) {
        const user = users.find(
        (u) => u.id === userID && u.password === password
    );

    if (user) {
        toast.success(`당신의 이메일은 ${user.email} 입니다.`);
        } else {
        toast.error("이메일 또는 아이디가 올바르지 않습니다.");
        }
    } else {
        toast.error("빈 칸이 있어선 안됩니다.");
    }
};

    return (
    <>
    <S.LoginContainer>
        <S.LoginTextBox>
        <S.Title>Find my E-mail</S.Title>
        <S.Subtitle>Please take a moment to find email</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
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
            <S.InputLabel>Password</S.InputLabel>

            <S.PasswordWrapper>
                <S.PasswordInput
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Enter your password"
                />

                <S.Send type="button" onClick={handleSubmit}>
                Send
                </S.Send>
            </S.PasswordWrapper>
            </S.InputID>
        </S.InputBox>

        <S.gotoLogin onClick={() => navigate("/login")}>
            Go to Login
        </S.gotoLogin>
        </S.LoginContainer>

        <ToastContainer position="top-right" autoClose={3000} />
    </>
);
}
