import styled from "styled-components";

export const DesBox = styled.div`
    width: 800px;
    height: 170px;

    gap: 0px;

    border-radius: 30px;
    background: rgba(255, 255, 255, 0.30);
    box-shadow: 0 4px 100px 0 rgba(0, 0, 0, 0.25);

    display: flex;
    align-items: center;
    justify-content: center;

    flex-direction: column;

    padding: 100px;
`;

export const Text = styled.p`
    color: rgba(0, 0, 0, 0.80);
    text-align: center;
    font-family: Pretendard;
    font-size: 20px;
    font-style: normal;
    font-weight: 500;
`;

export const Select_Button_Box = styled.div`

    width: 300px;
    display: flex;
    align-items: center;
    justify-content: center;

    gap: 20px;
`

export const Select_Box = styled.div`
    width: 700px;
    height: 100px;


    display: flex;
    align-items: center;
    justify-content: center;

    gap: 20px;
`

export const L_Text = styled.p`
    color: #185EA9;
    text-align: center;
    font-family: Pretendard;
    font-size: 15px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
`

export const R_Text = styled.p`
    color: #9A0B0E;
    text-align: center;
    font-family: Pretendard;
    font-size: 15px;
    font-style: normal;
    font-weight: 600;
    line-height: normal;
`

export const LL_CheckBox = styled.input`
    -webkit-appearance: none; // 웹킷 브라우저에서 기본 스타일 제거
    -moz-appearance: none; // 모질라 브라우저에서 기본 스타일 제거 
    appearance: none; // 기본 브라우저에서 기본 스타일 제거
    border: 2px solid #185EA9;
    border-radius: 100%;
    aspect-ratio: 1 / 1; // 원 모양 비율
    outline: none; // focus 시에 나타나는 기본 스타일 제거

    width: 50px;
    height: 50px;
    transition: transform 0.4s ease, background-color 0.4s ease;
    &:hover {
        background-color: #ffffffff; /* hover 시 색상 변화 */
        transform: scale(1.2); /* 살짝 커지는 효과 */
        cursor: pointer;
    }
    &.action {
        background-color: #185EA9; // 체크 시 내부 원 색상
        border: 3px solid #185EA9; // 라인이 아닌, 라인과 원 사이 색상
        box-shadow: 0 0 0 1px #185EA9; // 라인
    }
`

export const L_CheckBox = styled.input`
    -webkit-appearance: none; // 웹킷 브라우저에서 기본 스타일 제거
    -moz-appearance: none; // 모질라 브라우저에서 기본 스타일 제거 
    appearance: none; // 기본 브라우저에서 기본 스타일 제거
    border: 2px solid #7897B7;
    border-radius: 100%;
    aspect-ratio: 1 / 1; // 원 모양 비율
    outline: none; // focus 시에 나타나는 기본 스타일 제거

    width: 35px;
    height: 35px;
    transition: transform 0.4s ease, background-color 0.4s ease;
    &:hover {
        background-color: #ffffffff; /* hover 시 색상 변화 */
        transform: scale(1.2); /* 살짝 커지는 효과 */
        cursor: pointer;
    }

    &.action {
        background-color: #7897B7; // 체크 시 내부 원 색상
        border: 3px solid #7897B7; // 라인이 아닌, 라인과 원 사이 색상
        box-shadow: 0 0 0 1px #7897B7; // 라인
    }
    
`

export const M_CheckBox = styled.input`
    -webkit-appearance: none; // 웹킷 브라우저에서 기본 스타일 제거
    -moz-appearance: none; // 모질라 브라우저에서 기본 스타일 제거 
    appearance: none; // 기본 브라우저에서 기본 스타일 제거
    border: 2px solid #BBBBBB;
    border-radius: 100%;
    aspect-ratio: 1 / 1; // 원 모양 비율
    outline: none; // focus 시에 나타나는 기본 스타일 제거

    width: 25px;
    height: 25px;
    transition: transform 0.4s ease, background-color 0.4s ease;
    &:hover {
        background-color: #ffffffff; /* hover 시 색상 변화 */
        transform: scale(1.2); /* 살짝 커지는 효과 */
        cursor: pointer;
    }

    &.action {
        background-color: #BBBBBB; // 체크 시 내부 원 색상
        border: 3px solid #BBBBBB; // 라인이 아닌, 라인과 원 사이 색상
        box-shadow: 0 0 0 1px #BBBBBB; // 라인
    }
    `

export const R_CheckBox = styled.input`
    -webkit-appearance: none; // 웹킷 브라우저에서 기본 스타일 제거
    -moz-appearance: none; // 모질라 브라우저에서 기본 스타일 제거 
    appearance: none; // 기본 브라우저에서 기본 스타일 제거
    border: 2px solid #BB8D8D;
    border-radius: 100%;
    aspect-ratio: 1 / 1; // 원 모양 비율
    outline: none; // focus 시에 나타나는 기본 스타일 제거

    width: 35px;
    height: 35px;
    transition: transform 0.4s ease, background-color 0.4s ease;
    &:hover {
        background-color: #ffffffff; /* hover 시 색상 변화 */
        transform: scale(1.2); /* 살짝 커지는 효과 */
        cursor: pointer;
    }

    &.action {
        background-color: #BB8D8D; // 체크 시 내부 원 색상
        border: 3px solid #BB8D8D; // 라인이 아닌, 라인과 원 사이 색상
        box-shadow: 0 0 0 1px #BB8D8D; // 라인
    }
`

export const RR_CheckBox = styled.input`
    -webkit-appearance: none; // 웹킷 브라우저에서 기본 스타일 제거
    -moz-appearance: none; // 모질라 브라우저에서 기본 스타일 제거 
    appearance: none; // 기본 브라우저에서 기본 스타일 제거
    border: 2px solid #9A0000;
    border-radius: 100%;
    aspect-ratio: 1 / 1; // 원 모양 비율
    outline: none; // focus 시에 나타나는 기본 스타일 제거

    width: 50px;
    height: 50px;
    transition: transform 0.4s ease, background-color 0.4s ease;
    &:hover {
        background-color: #ffffffff; /* hover 시 색상 변화 */
        transform: scale(1.2); /* 살짝 커지는 효과 */
        cursor: pointer;
    }
    &.action {
        background-color: #9A0000; // 체크 시 내부 원 색상
        border: 3px solid #9A0000; // 라인이 아닌, 라인과 원 사이 색상
        box-shadow: 0 0 0 1px #9A0000; // 라인
    }


`