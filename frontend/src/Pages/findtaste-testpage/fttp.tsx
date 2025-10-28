import * as S from './style.ts'
import FT_Header from "../../components/findtaste-Header/fth.tsx";
import Select_Box from '../../components/findtaste-selectbox/ftsb.tsx';
import { useState } from 'react';


function FT_TestPage() {
    const [page, setPage] = useState<number>(1);
    return (
        <S.Background>
            <title>Rebloom - 취향테스트</title>
            <S.Header>
                <FT_Header/>
            </S.Header>
            <S.MainContainer>
                <S.RowContainer>
                    <S.Select_Box>
                        <S.Page_num>{page}/10</S.Page_num>
                        <Select_Box pro='당신의 이름은 인가요?'/>
                        <S.Button_Box>
                            <S.Before_button onClick={() => {setPage(page-1)}}>{'<'}</S.Before_button>
                            <Select_Box pro='당신은 귀엽나요?'/>
                            <S.After_button onClick={() => {setPage(page+1)}}>{'>'}</S.After_button>
                        </S.Button_Box>
                        <Select_Box pro='당신은 사랑받기 위해 태어났나요?'/>
                    </S.Select_Box>
                </S.RowContainer>
            </S.MainContainer>
        </S.Background>
        )
}

// export const Proms() {
//     const [page, setPage] = useState<number>(1);
//     return (
//         <>
//             <S.Page_num>{page}/10</S.Page_num>
//             <Select_Box pro='질문1'/>
//             <S.Button_Box>
//                 <S.Before_button onClick={() => {setPage(page-1)}}>{'<'}</S.Before_button> 
//                 <Select_Box pro='질문2'/>
//                 <S.After_button onClick={() => {setPage(page+1)}}>{'>'}</S.After_button>
//             </S.Button_Box>
//             <Select_Box pro='질문3'/>
//         </>
//     )
// }

export default FT_TestPage;


