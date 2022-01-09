DELETE FROM USER_INFO ;

INSERT INTO USER_INFO
(user_id, user_name, password, activation, total_score, register_date)
VALUES
    ('user1', 'test', '12345', 'F', '100', '2021-12-17');

DELETE FROM QUIZ_CATEGORY;

INSERT INTO QUIZ_CATEGORY
(category_name)
VALUES
    ('운영체제'), ('자료구조'), ('컴퓨터 구조'), ('네트워크'), ('알고리즘');

DELETE FROM QUIZ_LIST;

INSERT INTO QUIZ_LIST
(`category_num`,user_id,quiz_score,quiz_contents,quiz_answer,choice1, choice2,choice3,choice4)
VALUES
    ('4','user1', 5, 'question', 'choice2', '1번 보기내용', '2번 보기내용', '3번 보기내용', '4번 보기내용');
