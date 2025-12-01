// src/data/posts.ts
export interface Comment {
    author: string;
    text: string;
}

export interface PostItem {
    id: number;
    tag: string;
    title: string;
    notice: boolean;
    category: string;
    favorite: boolean;
    content: string;      
    comments: Comment[]; 
}

export const posts: PostItem[] = [
    {
        id: 1,
        tag: '[공지]',
        title: '이용 안내',
        notice: true,
        category: '공지사항',
        favorite: false,
        content: '이용 시 지켜야 할 간단한 규칙들을 안내드립니다.',
        comments: [
            { author: '관리자', text: '확인했습니다' },
            { author: '관리자1', text: '감사합니다' },
        ],
    },
    {
        id: 2,
        tag: '[함께해요]',
        title: '참여자 모집',
        notice: false,
        category: '함께해요',
        favorite: true,
        content: '관심 있으신 분들은 댓글 남겨주세요',
        comments: [
            { author: '시민1', text: '저요' },
            { author: '시민2', text: '저요' },
        ],
    },
    {
        id: 3,
        tag: '[소통해요]',
        title: '오늘',
        notice: false,
        category: '소통',
        favorite: false,
        content: '오늘은 .....',
        comments: [
            { author: '시민', text: '우와' },
        ],
    },
    {
        id: 4,
        tag: '[함께해요]',
        title: '후기 공유',
        notice: false,
        category: '함께해요',
        favorite: false,
        content: '후기입니다 ',
        comments: [
            { author: '시민', text: '우와우와우와우와' },
        ],
    },
    {
        id: 5,
        tag: '[공지]',
        title: '새로운 게시판 추가',
        notice: true,
        category: '공지사항',
        favorite: false,
        content: '새로운 게시판이 추가되었습니다',
        comments: [],
    },
    {
        id: 6,
        tag: '[소통해요]',
        title: '이번 주',
        notice: false,
        category: '소통',
        favorite: false,
        content: '응원해주세요',
        comments: [
            { author: '시민', text: '화이팅이에요!' },
        ],
    },
    {
        id: 7,
        tag: '[함께해요]',
        title: '후기 공유',
        notice: false,
        category: '함께해요',
        favorite: false,
        content: '후기 나눠요',
        comments: [],
    },
    {
        id: 8,
        tag: '[공지]',
        title: '점검 안내',
        notice: true,
        category: '공지사항',
        favorite: false,
        content: '사이트 점검 안내드립니다 ',
        comments: [
            { author: '운영자', text: '다시 공지' },
        ],
    },
    {
        id: 9,
        tag: '[함께해요]',
        title: '다음 일정',
        notice: false,
        category: '함께해요',
        favorite: false,
        content: '모임 일정',
        comments: [
            { author: '시민', text: '신청합니다' },
        ],
    },
    {
        id: 10,
        tag: '[소통해요]',
        title: '좋은 하루 보내세요!',
        notice: false,
        category: '소통',
        favorite: false,
        content: '하하',
        comments: [],
    },
    {
        id: 11,
        tag: '[소통]',
        title: '이용 안내',
        notice: false,
        category: '소통',
        favorite: false,
        content: '이용 시 지켜야 할 간단한 규칙들을 안내드립니다.',
        comments: [
            { author: '관리자', text: '확인했습니다' },
            { author: '관리자1', text: '감사합니다' },
        ],
    }
];
