import * as S from "./style.ts";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { authApi } from "../../api/auth";

export default function Ferb() {
  const navigate = useNavigate();

  const [userID, setUserid] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async () => {
    if (userID.trim() && password) {
      try {
        const response = await authApi.findUserEmail({
          userId: userID,
          userPassword: password,
        });

        if (response.success) {
          toast.success(`당신의 이메일은 ${response.data.userEmail} 입니다.`);
        } else {
          toast.error("이메일 또는 아이디가 올바르지 않습니다.");
        }
      } catch (error) {
        console.error(error);
        toast.error("정보가 올바르지 않습니다.");
      }
    } else {
      toast.error(<>빈 칸인 있어선 안됩니다.</>);
    }
  };

  // 이메일찾기_오른쪽박스
  return (
    <>
      <S.LoginContainer>
        <S.LoginTextBox>
          <S.Title>이메일을 찾아보아요!</S.Title>
          <S.Subtitle>빈 칸을 입력해주세요</S.Subtitle>
        </S.LoginTextBox>

        <S.InputBox>
          <S.InputID>
            <S.InputLabel>아이디</S.InputLabel>
            <S.Input
              type="text"
              value={userID}
              onChange={(e) => setUserid(e.target.value)}
              placeholder="아이디를 입력해주세요"
              autoFocus
            />
          </S.InputID>
          <S.InputID>
            <S.InputLabel>비밀번호</S.InputLabel>
            <S.Input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              placeholder="비밀번호를 입력해주세요"
            />
          </S.InputID>
        </S.InputBox>

        <S.Send onClick={handleSubmit} type="button">
          요청하기
        </S.Send>
        <S.gotoLogin
          onClick={() => {
            navigate("/login");
          }}
        >
          로그인 하러가기
        </S.gotoLogin>
      </S.LoginContainer>
      <ToastContainer position="top-right" autoClose={3000} />
    </>
  );
}
