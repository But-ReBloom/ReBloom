import styled, { keyframes } from "styled-components";

/* ===============================
   공통
================================ */
export const Wrapper = styled.div`
  width: 100vw;
  height: 100vh;
  background: linear-gradient(135deg, #bbe4fc 0%, #fff 100%);
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Container = styled.div`
  position: relative;
  display: flex;
  align-items: flex-end;
`;

/* ===============================
   캐릭터
================================ */
export const square = styled.div<{
  $width: number;
  $height: number;
  $background: string;
}>`
  display: flex;
  justify-content: center;
  width: ${({ $width }) => $width}px;
  height: ${({ $height }) => $height}px;
  background: ${({ $background }) => $background};
`;

/* 파란 캐릭터 전체 눌림 */
const bluePress = keyframes`
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(6px); }
`;

export const BlueInner = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  animation: ${bluePress} 2.2s infinite ease-in-out;
`;

export const YellowInner = styled.div`
  width: 100%;
  display: flex;
  justify-content: center;
  animation: ${bluePress} 3s infinite ease-in-out;
`;

/* ===============================
   눈
================================ */
const sighEyes = keyframes`
  0%, 100% {
    transform: scaleY(1);
    opacity: 1;
  }
  50% {
    transform: scaleY(0.6);
    opacity: 0.6;
  }
`;

export const WhiteEyes = styled.div`
  margin-top: 32px;
  display: flex;
  align-items: flex-end;
  width: 80px;
  height: 40px;
  background: white;
  border-radius: 200px 200px 0 0;

  &.sigh-eyes {
    animation: ${sighEyes} 2.2s infinite ease-in-out;
  }
`;

export const BlackEyes = styled.div`
  width: 40px;
  height: 20px;
  background: black;
  border-radius: 200px 200px 0 0;
`;

/* ===============================
   손 애니메이션 (톡톡)
================================ */
const tap = keyframes`
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(14px); }
`;

export const Hand = styled.div`
  position: absolute;
  width: 30px;
  height: 30px;
  background: #ffb800;
  border-radius: 30px;

  margin-left: 80px;
  margin-bottom: 240px;

  animation: ${tap} 1.1s infinite ease-in-out;
`;

const sighFloat = keyframes`
  0% {
    opacity: 0;
    transform: translateY(10px) scale(0.6);
  }
  20% {
    opacity: 0.5;
  }
  60% {
    opacity: 0.35;
    transform: translateY(-20px) scale(1);
  }
  100% {
    opacity: 0;
    transform: translateY(-40px) scale(1.1);
  }
`;

export const Sigh = styled.div`
  position: absolute;
  left: 20px;          /* 파란 캐릭터 쪽 */
  bottom: 260px;       /* 머리 위 위치 */
  width: 36px;
  height: 22px;

  border-radius: 50%;
  background: rgba(200, 200, 200, 0.86);
  filter: blur(1px);

  animation: ${sighFloat} 2.2s infinite ease-out;

  /* 한숨 구름을 2~3개처럼 보이게 */
  &::before,
  &::after {
    content: "";
    position: absolute;
    background: rgba(200, 200, 200, 0.6);
    border-radius: 50%;
  }

  &::before {
    width: 18px;
    height: 18px;
    left: -10px;
    top: 6px;
  }

  &::after {
    width: 14px;
    height: 14px;
    right: -8px;
    top: 10px;
  }
`;