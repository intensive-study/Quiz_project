DELETE FROM USER_INFO ;

INSERT INTO USER_INFO
(user_id, user_name, password, resign, rank_score, register_date)
VALUES
    ('user1', 'test', '12345', 'F', '100', '2021-12-17');

DELETE FROM QUIZ_CATEGORY;

INSERT INTO QUIZ_CATEGORY
(category_name)
VALUES
    ('운영체제'), ('자료구조'), ('컴퓨터 구조'), ('네트워크'), ('알고리즘');

DELETE FROM QUIZ_LIST;

INSERT INTO QUIZ_LIST
(category_num, user_id, quiz_score, answer_rate, trial_user_count, answer_user_count)
VALUES
    ('1', 'user1', 5, 0.5, 4, 2), ('1', 'user1', 3, 1, 2, 2);