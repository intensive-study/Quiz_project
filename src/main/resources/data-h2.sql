-- DELETE FROM USER_INFO ;
--
-- INSERT INTO USER_INFO
-- (user_id, name, password, activation, total_score, register_date)
-- VALUES
--     ('user1', 'test', '12345', 'F', '100', '2021-12-17');
--
-- DELETE FROM QUIZ_CATEGORY;
--
-- INSERT INTO QUIZ_CATEGORY
-- (category_name)
-- VALUES
--     ('운영체제'), ('자료구조'), ('컴퓨터 구조'), ('네트워크'), ('알고리즘');
--
-- DELETE FROM QUIZ_LIST;
--
-- INSERT INTO QUIZ_LIST
-- (`category_num`,user_id,quiz_score,quiz_contents,quiz_answer,choice1, choice2,choice3,choice4)
-- VALUES
--     ('4','user1', 5, 'question', 'choice2', '1번 보기내용', '2번 보기내용', '3번 보기내용', '4번 보기내용');
INSERT INTO USER_INFO (USER_ID, USERNAME, PASSWORD, ACTIVATED) values (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 1);
INSERT INTO USER_INFO (USER_ID, USERNAME, PASSWORD, ACTIVATED) values (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 1);

INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_USER');
INSERT INTO AUTHORITY (AUTHORITY_NAME) values ('ROLE_ADMIN');

INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values ('1', 'ROLE_USER');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values ('1', 'ROLE_ADMIN');
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_NAME) values ('2', 'ROLE_USER');

INSERT INTO QUIZ_CATEGORY (CATEGORY_NUM, CATEGORY_NAME) values ('1', 'OS');
INSERT INTO QUIZ_LIST (QUIZ_NUM, CHOICE1, CHOICE2, CHOICE3, CHOICE4, CHOICE5, CATEGORY_NUM, USER_ID, QUIZ_SCORE, QUIZ_CONTENTS, QUIZ_ANSWER)
 values ('1', 'FALSE', 'FALSE', 'FALSE', 'TRUE', 'FALSE', '1', '1', '10', 'HELLO CONTENTS', '4');

INSERT INTO QUIZ_DETAIL (QUIZ_NUM, ANSWER_RATE, ANSWER_USER_COUNT, TRIAL_USER_COUNT) values (1, 0, 0, 0);