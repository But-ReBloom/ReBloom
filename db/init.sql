drop schema ReBloom;
create database if not exists ReBloom character set utf8mb4 collate utf8mb4_unicode_ci;
use ReBloom;

create table users (
                       u_email varchar(100) not null primary key,
                       u_id varchar(255) not null unique,
                       u_current_act bigint null,
                       u_password varchar(255) not null,
                       u_name varchar(20) not null,
                       u_role varchar(255) not null default 'USER',
                       u_tier_point int not null default 0,
                       u_point int not null default 0,
                       u_provider varchar(255) default 'SELF',
                       u_tier varchar(20) not null default 'IRON',
                       u_recent_date date not null,
                       u_streak int not null default 1,
                       u_score_updated_at date,
                       u_social_score double not null default 0.0,
                       u_learning_score double not null default 0.0,
                       u_planning_score double not null default 0.0,
                       u_focus_score double not null default 0.0,
                       u_creativity_score double not null default 0.0
);

create table hobbies (
                         h_id bigint not null auto_increment primary key,
                         h_name varchar(255) not null,
                         h_w_social double not null,
                         h_w_learning double not null,
                         h_w_planning double not null,
                         h_w_focus double not null,
                         h_w_creativity double not null
);

create table activities (
                            act_id bigint not null auto_increment primary key,
                            fk_u_email varchar(100) not null,
                            fk_h_id bigint not null,
                            act_start date not null,
                            act_recent date not null,
                            foreign key (fk_u_email) references users(u_email) on delete cascade,
                            foreign key (fk_h_id) references hobbies(h_id) on delete cascade
);

create table activity_reviews (
                                  actr_id bigint not null auto_increment primary key,
                                  actr_question varchar(1000),
                                  actr_answer varchar(1000),
                                  actr_created_at date,
                                  fk_u_email varchar(100) not null,
                                  fk_h_id bigint not null,
                                  foreign key (fk_h_id) references hobbies(h_id) on delete cascade,
                                  foreign key (fk_u_email) references users(u_email) on delete cascade
);

create table channels (
                          ch_id bigint not null auto_increment primary key,
                          fk_u_email varchar(100) not null,
                          ch_title varchar(50) not null unique,
                          ch_intro varchar(100) not null,
                          ch_lk_h_1_id bigint not null,
                          ch_lk_h_2_id bigint,
                          ch_lk_h_3_id bigint,
                          ch_description text not null,
                          ch_created_at datetime not null default current_timestamp,
                          ch_status varchar(20) not null default 'PENDING',
                          foreign key (fk_u_email) references users(u_email) on delete cascade,
                          foreign key (ch_lk_h_1_id) references hobbies(h_id) on delete cascade,
                          foreign key (ch_lk_h_2_id) references hobbies(h_id) on delete cascade,
                          foreign key (ch_lk_h_3_id) references hobbies(h_id) on delete cascade
);

create table user_to_channels (
                                  fk_ch_id bigint not null,
                                  fk_u_email varchar(100) not null,
                                  uc_status varchar(20) not null default 'WAITING',
                                  uc_message mediumtext not null,
                                  primary key (fk_ch_id, fk_u_email),
                                  foreign key (fk_ch_id) references channels(ch_id) on delete cascade,
                                  foreign key (fk_u_email) references users(u_email) on delete cascade
);

create table posts (
                       p_id bigint not null auto_increment primary key,
                       fk_ch_id bigint not null,
                       fk_u_email varchar(100) not null,
                       p_title varchar(50) not null,
                       p_content mediumtext not null,
                       p_image varchar(255) default null,
                       p_type varchar(255) not null default 'NORMAL',
                       p_status varchar(255) default null,
                       p_created_at datetime not null default current_timestamp,
                       p_viewers int not null default 0,
                       foreign key (fk_ch_id) references channels(ch_id) on delete cascade,
                       foreign key (fk_u_email) references users(u_email) on delete cascade
);

create table comments (
                          co_id bigint not null auto_increment primary key,
                          fk_p_id bigint not null,
                          fk_u_email varchar(100) not null,
                          co_content varchar(500) not null,
                          co_created_at datetime not null default current_timestamp,
                          foreign key (fk_p_id) references posts(p_id) on delete cascade,
                          foreign key (fk_u_email) references users(u_email) on delete cascade
);

create table hearts (
                        h_id bigint not null auto_increment primary key,
                        fk_p_id bigint not null,
                        fk_u_email varchar(100) not null,
                        foreign key (fk_u_email) references users(u_email) on delete cascade,
                        foreign key (fk_p_id) references posts(p_id) on delete cascade
);

create table achieves (
                          ach_id bigint not null auto_increment primary key,
                          ach_title varchar(100) not null unique,
                          ach_description text not null,
                          ach_reward_point int not null,
                          ach_reward_tier_point int not null
);

create table users_to_achieves (
                                   fk_u_email varchar(100) not null,
                                   fk_ach_id bigint not null,
                                   uach_progress float not null,
                                   uach_is_success tinyint not null,
                                   primary key (fk_u_email, fk_ach_id),
                                   foreign key (fk_u_email) references users(u_email) on delete cascade,
                                   foreign key (fk_ach_id) references achieves(ach_id) on delete cascade
);

create table initial_tests (
                               it_question_id bigint not null auto_increment primary key,
                               it_set_no bigint not null,
                               it_category varchar(255) not null,
                               it_question varchar(255) not null,
                               it_w_social double not null,
                               it_w_learning double not null,
                               it_w_planning double not null,
                               it_w_focus double not null,
                               it_w_creativity double not null
);

create table tier_rank (
                           tr_name varchar(20) not null primary key,
                           tr_min_point int not null,
                           tr_max_point int not null
);

alter table users
    add constraint fk_u_current_act
        foreign key (u_current_act)
            references activities(act_id)
            on update cascade
            on delete set null;

insert into initial_tests
(it_set_no, it_category, it_question,
 it_w_social, it_w_learning, it_w_planning, it_w_focus, it_w_creativity)
values
-- set 0
(0,'Social','사람들과 어울릴 때 에너지가 생기나요?', 2, 1, -1, -1, 1),
(0,'Learning','새로운 아이디어를 떠올리는 걸 좋아하나요?', -1, 2, -1, 1, 2),
(0,'Planning','계획대로 움직이는 게 편한가요?', -1, -1, 2, 1, -1),
(0,'Focus','경쟁이 있을 때 더 집중되나요?', -1, 1, 1, 2, -1),
(0,'Creativity','직접 무언가를 만들어보는 게 즐겁나요?', 1, 1, -1, -1, 2),

-- set 1
(1,'Social','낯선 사람과 대화할 때 즐거움을 느끼나요?', 2, 1, -1, -1, 1),
(1,'Learning','구체적인 사실보다 개념적인 사고가 더 잘 맞나요?', -1, 2, -1, 1, 2),
(1,'Planning','계획이 틀어지면 불편한가요?', -1, -1, 2, 1, -1),
(1,'Focus','다른 사람과 비교하면 더 의욕이 생기나요?', -1, 1, 1, 2, -1),
(1,'Creativity','작품을 만들 때 자기만의 색을 담으려 하나요?', 1, 1, -1, -1, 2),

-- set 2
(2,'Social','모임이 끝난 후에도 에너지가 남아있나요?', 2, 1, -1, -1, 1),
(2,'Learning','배우는 이유가 가능성을 탐색하기 위해서인가요?', -1, 2, -1, 1, 2),
(2,'Planning','해야 할 일을 미리 끝내두는 편인가요?', -1, -1, 2, 1, -1),
(2,'Focus','순위나 평가가 있으면 더 동기부여가 되나요?', -1, 1, 1, 2, -1),
(2,'Creativity','음악을 들을 때 작곡을 상상하나요?', 1, 1, -1, -1, 2),

-- set 3
(3,'Social','새로운 모임에서 먼저 말을 거는 편인가요?', 2, 1, -1, -1, 1),
(3,'Learning','배울 때 원리를 먼저 알고 싶나요?', -1, 2, -1, 1, 1),
(3,'Planning','계획표를 세우면 마음이 안정되나요?', -1, -1, 2, 1, -1),
(3,'Focus','경쟁 상황에서 승부욕이 생기나요?', -1, 1, 1, 2, -1),
(3,'Creativity','사진을 찍을 때 구도를 신경쓰나요?', 1, 1, -1, -1, 2),

-- set 4
(4,'Social','파티나 행사 초대에 기대가 되나요?', 2, 1, -1, -1, 1),
(4,'Learning','미래를 상상하며 배우는 편인가요?', -1, 2, -1, 1, 2),
(4,'Planning','정해진 스케줄을 따르는 걸 선호하나요?', -1, -1, 2, 1, -1),
(4,'Focus','누군가 나보다 잘하면 자극을 받나요?', -1, 1, 1, 2, -1),
(4,'Creativity','그림을 보면 나도 그려보고 싶다고 느끼나요?', 1, 1, -1, -1, 2),

-- set 5
(5,'Social','단체 프로젝트가 개인 프로젝트보다 즐겁나요?', 2, 1, -1, -1, 1),
(5,'Learning','큰 그림부터 이해하는 편인가요?', -1, 2, -1, 1, 1),
(5,'Planning','계획에 따라 움직이는 걸 선호하나요?', -1, -1, 2, 1, -1),
(5,'Focus','게임할 때 승패가 중요한가요?', -1, 1, 1, 2, -1),
(5,'Creativity','글을 읽을 때 나도 써보고 싶다는 생각이 드나요?', 1, 1, -1, -1, 2),

-- set 6
(6,'Social','대화 중 침묵이 어색한가요?', 1, 1, -1, -1, 1),
(6,'Learning','새로운 시도와 실험을 즐기나요?', -1, 2, -1, 1, 2),
(6,'Planning','할 일 목록을 꼼꼼히 관리하나요?', -1, -1, 2, 1, -1),
(6,'Focus','명확한 목표가 있을 때 더 잘 몰입하나요?', -1, 1, 1, 2, -1),
(6,'Creativity','음악을 들을 때 리듬에 먼저 반응하나요?', 1, 1, -1, -1, 2),

-- set 7
(7,'Social','여러 사람 앞에서 말하는 게 즐거운가요?', 2, 1, -1, -1, 1),
(7,'Learning','새로운 개념을 추상적으로 이해하나요?', -1, 2, -1, 1, 1),
(7,'Planning','약속 시간보다 미리 도착하는 편인가요?', -1, -1, 2, 1, -1),
(7,'Focus','경쟁이 없으면 의욕이 줄어드나요?', 1, -1, -1, -2, 1),
(7,'Creativity','영화를 볼 때 제작 과정을 상상하나요?', 1, 1, -1, -1, 2),

-- set 8
(8,'Social','새로운 친구를 쉽게 사귀는 편인가요?', 2, 1, -1, -1, 1),
(8,'Learning','정답보다 다양한 해석이 더 흥미롭나요?', -1, 2, -1, 1, 2),
(8,'Planning','계획이 틀어지면 스트레스를 받나요?', -1, -1, 2, 1, -1),
(8,'Focus','다른 사람의 평가가 동기부여가 되나요?', -1, 1, 1, 2, -1),
(8,'Creativity','전시회에서 직접 체험해보고 싶나요?', 1, 1, -1, -1, 2),

-- set 9
(9,'Social','즉흥적인 약속 제안에 긍정적인가요?', 2, 1, -2, -1, 1),
(9,'Learning','새로운 분야에 쉽게 호기심이 생기나요?', -1, 2, -1, 1, 2),
(9,'Planning','계획 없이 여행하면 불안한가요?', -1, -1, 2, 1, -1),
(9,'Focus','개인 목표가 공동 목표보다 더 중요하다고 느끼나요?', -1, -1, 1, 2, -1),
(9,'Creativity','영화나 드라마를 볼 때 연출이 더 눈에 들어오나요?', 1, 1, -1, -1, 2),

-- set 10
(10,'Social','주말에 약속이 없으면 아쉽나요?', 2, 1, -1, -1, 1),
(10,'Learning','새로운 이론을 배우는 게 흥미로운가요?', -1, 2, -1, 1, 1),
(10,'Planning','계획 없이 움직이면 불안한가요?', -1, -1, 2, 1, -1),
(10,'Focus','누군가 나보다 앞서면 불안한가요?', -1, -1, 1, 1, -1),
(10,'Creativity','그림을 볼 때 기법이 먼저 눈에 들어오나요?', 1, 1, -1, -1, 2);


insert into achieves (ach_title, ach_description, ach_reward_point, ach_reward_tier_point) values
                                                                                               ('첫 설문조사!','설문조사 1회 달성',100,100),
                                                                                               ('설문 조사의 신!','설문조사 10회 달성',800,800),
                                                                                               ('시작이 반이다.','1회 접속 후 회원가입',100,100),
                                                                                               ('계획적으로!','2일 연속 접속',200,200),
                                                                                               ('5연속 접속!','5일 연속 접속',500,500),
                                                                                               ('연속 접속의 신','365일 연속 접속',9000,9000),
                                                                                               ('첫 댓글','1회 댓글 달기',100,100),
                                                                                               ('좋은 댓글','5회 댓글 달기',500,500),
                                                                                               ('첫 리뷰','1회 활동 리뷰 달기',500,500),
                                                                                               ('리뷰의 달인','5회 활동 리뷰 달기',3500,2500),
                                                                                               ('커뮤니티 시작!','1회 커뮤니티 글 쓰기',100,100);

insert into tier_rank (tr_name, tr_min_point, tr_max_point) values
                                                                ('IRON',0,999),
                                                                ('BRONZE',1000,1999),
                                                                ('SILVER',2000,2999),
                                                                ('GOLD',3000,3999),
                                                                ('DIAMOND',4000,4999),
                                                                ('MASTER',5000,5999),
                                                                ('CHALLENGER',6000,999999);

INSERT INTO hobbies
(h_name, h_w_social, h_w_learning, h_w_planning, h_w_focus, h_w_creativity)
VALUES
-- 지적 / 몰입형
('코딩', -2, 2, 2, 2, 1),
('독서', -2, 2, 1, 2, 0),
('필사', -2, 1, 1, 2, 0),
('퍼즐 맞추기', -2, 1, 1, 2, -1),
('모의 투자', -1, 2, 2, 2, -1),

-- 창작 중심
('글쓰기', -1, 2, 1, 1, 2),
('그림 그리기', -2, 1, 1, 1, 2),
('디자인', -1, 2, 1, 1, 2),
('영상 편집', -1, 2, 1, 2, 2),
('작곡/작사', -2, 2, 1, 1, 2),
('캘리그라피', -2, 1, 1, 1, 2),
('공예(뜨개질/자수)', -2, 1, 1, 2, 2),
('다이어리 꾸미기', -2, 1, 1, 1, 2),

-- 감상 / 영감형
('영화감상', -1, 1, 0, 0, 2),
('사진 촬영', -1, 1, 1, 1, 2),
('악기 연주', -1, 2, 1, 2, 1),

-- 활동 / 신체 몰입형
('러닝', 0, 0, 0, 2, -1),
('자전거', 0, 0, 0, 2, -1),
('산책', -1, 0, 0, 1, -1),
('요가/명상', -2, 1, 1, 2, 0),

-- 스포츠 / 경쟁형
('구기 스포츠', 2, 0, 0, 2, -1),
('e스포츠', 1, 1, 0, 2, -1),
('댄스', 2, 1, 0, 1, 2),

-- 협업 / 외향형
('봉사활동', 2, 1, 1, 0, 0),
('보드게임', 2, 1, 1, 1, 0),

-- 라이프스타일 / 계획형
('요리/베이킹', 1, 1, 2, 1, 2),
('인테리어', 0, 1, 2, 1, 2),
('가드닝', 0, 1, 1, 1, 1),

-- 이동 / 경험형
('여행', 2, 1, 2, -1, 2),
('캠핑', 2, 1, 2, -1, 1),
('드라이브', 0, 0, 1, -1, -1),

-- 제작 / 조립
('조립식 모형 만들기', -2, 1, 2, 2, 1),
('커스텀 키보드 만들기', -2, 1, 2, 2, 1);

-- Sample users for channel creation (add if not exists)
INSERT IGNORE INTO users (u_email, u_id, u_password, u_name, u_role, u_tier_point, u_point, u_provider, u_tier, u_recent_date, u_streak) VALUES
('admin@rebloom.com', 'admin', '$2a$10$dummypasswordhash', '관리자', 'ADMIN', 5000, 1000, 'SELF', 'MASTER', CURDATE(), 100),
('user1@rebloom.com', 'coder_lover', '$2a$10$dummypasswordhash', '코딩마스터', 'USER', 3500, 500, 'SELF', 'GOLD', CURDATE(), 30),
('user2@rebloom.com', 'book_worm', '$2a$10$dummypasswordhash', '책벌레', 'USER', 2800, 400, 'SELF', 'SILVER', CURDATE(), 25),
('user3@rebloom.com', 'fitness_king', '$2a$10$dummypasswordhash', '헬스왕', 'USER', 4200, 600, 'SELF', 'DIAMOND', CURDATE(), 50),
('user4@rebloom.com', 'art_creator', '$2a$10$dummypasswordhash', '예술가', 'USER', 3000, 450, 'SELF', 'GOLD', CURDATE(), 35);

-- Channel dummy data
INSERT INTO channels (fk_u_email, ch_title, ch_intro, ch_lk_h_1_id, ch_lk_h_2_id, ch_lk_h_3_id, ch_description, ch_created_at, ch_status) VALUES
('user1@rebloom.com', '코딩 러버스', '프로그래밍을 사랑하는 사람들의 모임', 1, 32, 28, '다양한 프로그래밍 언어와 기술을 공유하고 함께 성장하는 커뮤니티입니다. 초보자부터 전문가까지 환영합니다!', '2025-11-01 10:00:00', 'ACCEPTED'),
('user2@rebloom.com', '북클럽 독서모임', '매달 한 권씩 함께 읽어요', 9, 10, NULL, '매달 다른 책을 선정하여 함께 읽고 토론하는 독서 모임입니다. 다양한 장르의 책을 접하며 시야를 넓혀보세요!', '2025-11-05 14:30:00', 'ACCEPTED'),
('user3@rebloom.com', '헬스&피트니스', '건강한 삶을 위한 운동 커뮤니티', 2, 25, 4, '운동 루틴 공유, 식단 정보, 운동 팁을 나누는 공간입니다. 함께 건강한 습관을 만들어가요!', '2025-11-10 09:00:00', 'ACCEPTED'),
('user4@rebloom.com', '아트 크리에이터', '창작의 즐거움을 나누는 공간', 11, 20, 28, '그림, 사진, 디자인 등 다양한 예술 작품을 공유하고 피드백을 주고받는 창작자들의 커뮤니티입니다.', '2025-11-15 16:00:00', 'ACCEPTED'),
('admin@rebloom.com', '요리&베이킹 클럽', '맛있는 레시피를 공유해요', 19, NULL, NULL, '집에서 만든 요리와 베이킹 레시피를 공유하고, 실패담과 성공담을 나누는 따뜻한 커뮤니티입니다.', '2025-11-20 13:00:00', 'ACCEPTED'),
('user1@rebloom.com', '여행 메이트', '함께 떠나는 여행의 추억', 8, 22, 23, '여행 정보 공유, 여행 메이트 찾기, 여행 후기 등을 나누는 여행 애호가들의 모임입니다.', '2025-11-25 11:00:00', 'ACCEPTED'),
('user2@rebloom.com', '악기 연주자 모임', '음악을 사랑하는 사람들', 15, 33, NULL, '다양한 악기를 연주하는 사람들이 모여 합주하고, 연주 팁을 공유하는 커뮤니티입니다.', '2025-12-01 15:00:00', 'ACCEPTED'),
('user3@rebloom.com', '게임 마니아', 'e스포츠와 게임을 즐기는 모임', 6, NULL, NULL, '다양한 게임 정보 공유, 게임 팁, 팀원 모집 등 게임을 즐기는 모든 분들을 환영합니다!', '2025-12-05 18:00:00', 'ACCEPTED'),
('user4@rebloom.com', '사진 동호회', '순간을 담는 사람들', 20, 8, NULL, '사진 촬영 기술, 편집 팁, 촬영 장소 추천 등을 공유하는 사진 애호가들의 커뮤니티입니다.', '2025-12-08 12:00:00', 'ACCEPTED'),
('admin@rebloom.com', '영상 크리에이터', 'YouTube와 영상 제작', 32, 28, 18, '영상 편집, 촬영 기법, 유튜브 운영 팁을 공유하는 영상 크리에이터들의 모임입니다.', '2025-12-10 14:00:00', 'ACCEPTED'),
('user1@rebloom.com', '캠핑 러버스', '자연과 함께하는 캠핑 라이프', 22, 8, 13, '캠핑 장소 추천, 캠핑 장비 리뷰, 캠핑 팁을 공유하는 캠핑 애호가들의 커뮤니티입니다.', '2025-12-12 10:00:00', 'ACCEPTED'),
('user2@rebloom.com', '봉사 활동 모임', '나눔의 가치를 실천해요', 5, NULL, NULL, '지역사회 봉사활동 정보를 공유하고 함께 참여하는 따뜻한 커뮤니티입니다.', '2025-12-14 09:00:00', 'ACCEPTED'),
('user3@rebloom.com', '댄스 크루', '춤으로 소통해요', 7, NULL, NULL, '다양한 장르의 댄스를 배우고 연습하며, 공연 정보를 공유하는 댄스 크루입니다.', '2025-12-15 17:00:00', 'PENDING'),
('user4@rebloom.com', '보드게임 카페', '전략과 재미의 보드게임', 26, NULL, NULL, '보드게임 리뷰, 모임 공지, 게임 전략을 공유하는 보드게임 애호가들의 모임입니다.', '2025-12-16 19:00:00', 'PENDING');

-- User to channel memberships
INSERT INTO user_to_channels (fk_ch_id, fk_u_email, uc_status, uc_message) VALUES
-- 코딩 러버스 (ch_id: 1)
(1, 'user1@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(1, 'user2@rebloom.com', 'APPROVED', '코딩 배우고 싶어서 가입합니다!'),
(1, 'admin@rebloom.com', 'APPROVED', '관리 목적으로 참여합니다.'),

-- 북클럽 독서모임 (ch_id: 2)
(2, 'user2@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(2, 'user4@rebloom.com', 'APPROVED', '책 읽는 걸 좋아합니다!'),
(2, 'admin@rebloom.com', 'WAITING', '함께 독서하고 싶습니다.'),

-- 헬스&피트니스 (ch_id: 3)
(3, 'user3@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(3, 'user1@rebloom.com', 'APPROVED', '운동 시작했습니다!'),
(3, 'user4@rebloom.com', 'WAITING', '건강 관리 시작하려고 합니다.'),

-- 아트 크리에이터 (ch_id: 4)
(4, 'user4@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(4, 'user2@rebloom.com', 'APPROVED', '그림 그리는 걸 좋아합니다!'),

-- 요리&베이킹 클럽 (ch_id: 5)
(5, 'admin@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(5, 'user1@rebloom.com', 'APPROVED', '요리 배우고 싶어요!'),
(5, 'user3@rebloom.com', 'APPROVED', '베이킹 좋아합니다!'),

-- 여행 메이트 (ch_id: 6)
(6, 'user1@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(6, 'user4@rebloom.com', 'APPROVED', '여행 정보 공유하고 싶어요!'),

-- 악기 연주자 모임 (ch_id: 7)
(7, 'user2@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(7, 'user3@rebloom.com', 'WAITING', '기타 배우고 있습니다!'),

-- 게임 마니아 (ch_id: 8)
(8, 'user3@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(8, 'user1@rebloom.com', 'APPROVED', '게임 좋아합니다!'),
(8, 'admin@rebloom.com', 'APPROVED', 'e스포츠 팬입니다!'),

-- 사진 동호회 (ch_id: 9)
(9, 'user4@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(9, 'user1@rebloom.com', 'WAITING', '사진 촬영 배우고 싶습니다!'),

-- 영상 크리에이터 (ch_id: 10)
(10, 'admin@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(10, 'user2@rebloom.com', 'APPROVED', '유튜브 시작했어요!'),

-- 캠핑 러버스 (ch_id: 11)
(11, 'user1@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(11, 'user3@rebloom.com', 'APPROVED', '캠핑 좋아합니다!'),

-- 봉사 활동 모임 (ch_id: 12)
(12, 'user2@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),
(12, 'admin@rebloom.com', 'APPROVED', '함께 나눔을 실천해요!'),

-- 댄스 크루 (ch_id: 13)
(13, 'user3@rebloom.com', 'APPROVED', '채널을 만들었습니다.'),

-- 보드게임 카페 (ch_id: 14)
(14, 'user4@rebloom.com', 'APPROVED', '채널을 만들었습니다.');