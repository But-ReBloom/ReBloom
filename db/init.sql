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
                                  actr_answecr varchar(1000),
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

insert into initial_tests (it_set_no, it_category, it_question, it_w_social, it_w_learning, it_w_planning, it_w_focus, it_w_creativity) values
                                                                                                                                            (0,'Social','사람들과 어울릴 때 에너지가 생기나요?',-2,-1,0,1,2),
                                                                                                                                            (0,'Learning','새로운 아이디어를 떠올리는 걸 좋아하나요?',-2,-1,0,1,2),
                                                                                                                                            (0,'Planning','계획대로 움직이는 게 편한가요?',-2,-1,0,1,2),
                                                                                                                                            (0,'Focus','경쟁이 있을 때 더 집중되나요?',-2,-1,0,1,2),
                                                                                                                                            (0,'Creativity','직접 무언가를 만들어보는 게 즐겁나요?',-2,-1,0,1,2),
                                                                                                                                            (1,'Social','낯선 사람과 대화할 때 즐거움을 느끼나요?',-2,-1,0,1,2),
                                                                                                                                            (1,'Learning','구체적인 사실보다 개념적인 사고가 더 잘 맞나요?',-2,-1,0,1,2),
                                                                                                                                            (1,'Planning','계획이 틀어지면 불편한가요?',-2,-1,0,1,2),
                                                                                                                                            (1,'Focus','다른 사람과 비교하면 더 의욕이 생기나요?',-2,-1,0,1,2),
                                                                                                                                            (1,'Creativity','작품을 만들 때 자기만의 색을 담으려 하나요?',-2,-1,0,1,2),
                                                                                                                                            (2,'Social','모임이 끝난 후에도 에너지가 남아있나요?',-2,-1,0,1,2),
                                                                                                                                            (2,'Learning','배우는 이유가 가능성을 탐색하기 위해서인가요?',-2,-1,0,1,2),
                                                                                                                                            (2,'Planning','해야 할 일을 미리 끝내두는 편인가요?',-2,-1,0,1,2),
                                                                                                                                            (2,'Focus','순위나 평가가 있으면 더 동기부여가 되나요?',-2,-1,0,1,2),
                                                                                                                                            (2,'Creativity','음악을 들을 때 작곡을 상상하나요?',-2,-1,0,1,2),
                                                                                                                                            (3,'Social','새로운 모임에서 먼저 말을 거는 편인가요?',-2,-1,0,1,2),
                                                                                                                                            (3,'Learning','배울 때 원리를 먼저 알고 싶나요?',-2,-1,0,1,2),
                                                                                                                                            (3,'Planning','계획표를 세우면 마음이 안정되나요?',-2,-1,0,1,2),
                                                                                                                                            (3,'Focus','경쟁 상황에서 승부욕이 생기나요?',-2,-1,0,1,2),
                                                                                                                                            (3,'Creativity','사진을 찍을 때 구도를 신경쓰나요?',-2,-1,0,1,2),
                                                                                                                                            (4,'Social','파티나 행사 초대에 기대가 되나요?',-2,-1,0,1,2),
                                                                                                                                            (4,'Learning','미래를 상상하며 배우는 편인가요?',-2,-1,0,1,2),
                                                                                                                                            (4,'Planning','정해진 스케줄을 따르는 걸 선호하나요?',-2,-1,0,1,2),
                                                                                                                                            (4,'Focus','누군가 나보다 잘하면 자극을 받나요?',-2,-1,0,1,2),
                                                                                                                                            (4,'Creativity','그림을 보면 ''나도 그려보고 싶다''고 느끼나요?',-2,-1,0,1,2),
                                                                                                                                            (5,'Social','단체 프로젝트가 개인 프로젝트보다 즐겁나요?',-2,-1,0,1,2),
                                                                                                                                            (5,'Learning','큰 그림부터 이해하는 편인가요?',-2,-1,0,1,2),
                                                                                                                                            (5,'Planning','계획에 따라 움직이는 걸 선호하나요?',-2,-1,0,1,2),
                                                                                                                                            (5,'Focus','게임할 때 승패가 중요한가요?',-2,-1,0,1,2),
                                                                                                                                            (5,'Creativity','글을 읽을 때 ''나도 써보고 싶다''는 생각이 드나요?',-2,-1,0,1,2),
                                                                                                                                            (6,'Social','대화 중 침묵이 어색한가요?',-2,-1,0,1,2),
                                                                                                                                            (6,'Learning','새로운 시도와 실험을 즐기나요?',-2,-1,0,1,2),
                                                                                                                                            (6,'Planning','할 일 목록을 꼼꼼히 관리하나요?',-2,-1,0,1,2),
                                                                                                                                            (6,'Focus','명확한 목표가 있을 때 더 잘 몰입하나요?',-2,-1,0,1,2),
                                                                                                                                            (6,'Creativity','음악을 들을 때 리듬에 먼저 반응하나요?',-2,-1,0,1,2),
                                                                                                                                            (7,'Social','여러 사람 앞에서 말하는 게 즐거운가요?',-2,-1,0,1,2),
                                                                                                                                            (7,'Learning','새로운 개념을 추상적으로 이해하나요?',-2,-1,0,1,2),
                                                                                                                                            (7,'Planning','약속 시간보다 미리 도착하는 편인가요?',-2,-1,0,1,2),
                                                                                                                                            (7,'Focus','경쟁이 없으면 의욕이 줄어드나요?',-2,-1,0,1,2),
                                                                                                                                            (7,'Creativity','영화를 볼 때 제작 과정을 상상하나요?',-2,-1,0,1,2),
                                                                                                                                            (8,'Social','새로운 친구를 쉽게 사귀는 편인가요?',-2,-1,0,1,2),
                                                                                                                                            (8,'Learning','정답보다 다양한 해석이 더 흥미롭나요?',-2,-1,0,1,2),
                                                                                                                                            (8,'Planning','계획이 틀어지면 스트레스를 받나요?',-2,-1,0,1,2),
                                                                                                                                            (8,'Focus','다른 사람의 평가가 동기부여가 되나요?',-2,-1,0,1,2),
                                                                                                                                            (8,'Creativity','전시회에서 직접 체험해보고 싶나요?',-2,-1,0,1,2),
                                                                                                                                            (9,'Social','즉흥적인 약속 제안에 긍정적인가요?',-2,-1,0,1,2),
                                                                                                                                            (9,'Learning','새로운 분야에 쉽게 호기심이 생기나요?',-2,-1,0,1,2),
                                                                                                                                            (9,'Planning','계획 없이 여행하면 불안한가요?',-2,-1,0,1,2),
                                                                                                                                            (9,'Focus','개인 목표가 공동 목표보다 더 중요하다고 느끼나요?',-2,-1,0,1,2),
                                                                                                                                            (9,'Creativity','영화나 드라마를 볼 때 연출이 더 눈에 들어오나요?',-2,-1,0,1,2),
                                                                                                                                            (10,'Social','주말에 약속이 없으면 아쉽나요?',-2,-1,0,1,2),
                                                                                                                                            (10,'Learning','새로운 이론을 배우는 게 흥미로운가요?',-2,-1,0,1,2),
                                                                                                                                            (10,'Planning','계획 없이 움직이면 불안한가요?',-2,-1,0,1,2),
                                                                                                                                            (10,'Focus','누군가 나보다 앞서면 불안한가요?',-2,-1,0,1,2),
                                                                                                                                            (10,'Creativity','그림을 볼 때 기법이 먼저 눈에 들어오나요?',-2,-1,0,1,2);

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

INSERT INTO hobbies (h_name, h_w_social, h_w_learning, h_w_planning, h_w_focus, h_w_creativity) VALUES
                                                                                                    ('코딩', -1, 2, 1, 2, 1),
                                                                                                    ('구기 스포츠', 2, 0, 0, 1, 0),
                                                                                                    ('수중운동', 1, 0, 0, 2, 0),
                                                                                                    ('육상운동', 1, 0, 0, 1, 0),
                                                                                                    ('봉사활동', 2, 1, 1, 0, 0),
                                                                                                    ('e스포츠', 1, 1, 0, 2, 0),
                                                                                                    ('댄스', 1, 1, 0, 1, 2),
                                                                                                    ('여행', 2, 1, 2, 0, 2),
                                                                                                    ('독서', -1, 2, 1, 2, 1),
                                                                                                    ('글쓰기', 0, 2, 1, 2, 2),
                                                                                                    ('그림 그리기', -1, 1, 0, 2, 2),
                                                                                                    ('인테리어', 0, 1, 2, 1, 2),
                                                                                                    ('가드닝', 0, 1, 1, 1, 1),
                                                                                                    ('산책', 0, 0, 0, 1, 0),
                                                                                                    ('악기 연주', 0, 2, 1, 2, 1),
                                                                                                    ('조립식 모형 만들기', -1, 1, 2, 2, 1),
                                                                                                    ('퍼즐 맞추기', -2, 1, 1, 2, 0),
                                                                                                    ('영화감상', 0, 1, 0, 0, 1),
                                                                                                    ('요리/베이킹', 1, 1, 2, 1, 2),
                                                                                                    ('사진 촬영', 0, 1, 1, 1, 2),
                                                                                                    ('자전거', 0, 0, 0, 1, 0),
                                                                                                    ('캠핑', 2, 1, 2, 0, 1),
                                                                                                    ('드라이브', 0, 0, 1, 0, 0),
                                                                                                    ('다이어리 꾸미기', -1, 1, 1, 1, 2),
                                                                                                    ('러닝', 0, 0, 0, 2, 0),
                                                                                                    ('보드게임', 2, 1, 1, 1, 1),
                                                                                                    ('모의 투자', -1, 2, 2, 2, 0),
                                                                                                    ('디자인', 0, 2, 1, 1, 2),
                                                                                                    ('요가/명상', -1, 1, 1, 2, 1),
                                                                                                    ('캘리그라피', -1, 1, 1, 2, 2),
                                                                                                    ('커스텀 키보드 만들기', -1, 1, 2, 2, 1),
                                                                                                    ('영상 편집', 0, 2, 1, 2, 2),
                                                                                                    ('작곡/작사', 0, 2, 1, 1, 2),
                                                                                                    ('공예(뜨개질/자수)', -1, 1, 1, 2, 2),
                                                                                                    ('필사', -1, 1, 1, 2, 1);