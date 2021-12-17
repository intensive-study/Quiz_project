DROP TABLE IF EXISTS `USER_INFO`;

CREATE TABLE `USER_INFO` (
                             `user_id`	varchar	NOT NULL,
                             `user_name`	varchar	NULL,
                             `password`	varchar	NULL,
                             `resign`	boolean	NULL,
                             `rank_score`	int	NULL,
                             `register_date`	datetime	NULL
);

DROP TABLE IF EXISTS `QUIZ_LIST`;

CREATE TABLE `QUIZ_LIST` (
                             `quiz_num`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `category_num`	int	NOT NULL,
                             `user_id`	char	NOT NULL,
                             `quiz_score`	int	NULL,
                             `answer_rate`	float	NULL,
                             `trial_user_count`	int	NULL,
                             `answer_user_count`	int	NULL
);

DROP TABLE IF EXISTS `QUIZ_CATEGORY`;

CREATE TABLE `QUIZ_CATEGORY` (
                                 `category_num`	int	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `category_name` char NOT NULL
);

DROP TABLE IF EXISTS `QUIZ_DETAIL`;

CREATE TABLE `QUIZ_DETAIL` (
                               `quiz_num`	int	NOT NULL,
                               `quiz_contents`	varchar	NULL,
                               `choice1`	varchar	NULL,
                               `choice2`	varchar	NULL,
                               `choice3`	varchar	NULL,
                               `choice4`	varchar	NULL,
                               `choice5`	varchar	NULL,
                               `quiz_answer`	varchar	NULL
);

DROP TABLE IF EXISTS `USER_QUIZ_HISTORY`;

CREATE TABLE `USER_QUIZ_HISTORY` (
                                     `quiz_num`	int	NOT NULL,
                                     `user_id`	char	NOT NULL,
                                     `trial_count`	int	NULL,
                                     `solve_time`	datetime	NULL,
                                     `answer`	boolean	NULL
);

ALTER TABLE `USER_INFO` ADD CONSTRAINT `PK_USER_INFO` PRIMARY KEY (
                                                                   `user_id`
    );

ALTER TABLE `QUIZ_DETAIL` ADD CONSTRAINT `PK_QUIZ_DETAIL` PRIMARY KEY (
                                                                       `quiz_num`
    );

ALTER TABLE `USER_QUIZ_HISTORY` ADD CONSTRAINT `PK_USER_QUIZ_HISTORY` PRIMARY KEY (
                                                                                   `quiz_num`,
                                                                                   `user_id`
    );

ALTER TABLE `QUIZ_LIST` ADD CONSTRAINT `FK_QUIZ_CATEGORY_TO_QUIZ_LIST_1` FOREIGN KEY (
                                                                                      `category_num`
    )
    REFERENCES `QUIZ_CATEGORY` (
                                `category_num`
        );

ALTER TABLE `QUIZ_LIST` ADD CONSTRAINT `FK_USER_INFO_TO_QUIZ_LIST_1` FOREIGN KEY (
                                                                                  `user_id`
    )
    REFERENCES `USER_INFO` (
                            `user_id`
        );

ALTER TABLE `QUIZ_DETAIL` ADD CONSTRAINT `FK_QUIZ_LIST_TO_QUIZ_DETAIL_1` FOREIGN KEY (
                                                                                      `quiz_num`
    )
    REFERENCES `QUIZ_LIST` (
                            `quiz_num`
        );

ALTER TABLE `USER_QUIZ_HISTORY` ADD CONSTRAINT `FK_QUIZ_LIST_TO_USER_QUIZ_HISTORY_1` FOREIGN KEY (
                                                                                                  `quiz_num`
    )
    REFERENCES `QUIZ_LIST` (
                            `quiz_num`
        );

ALTER TABLE `USER_QUIZ_HISTORY` ADD CONSTRAINT `FK_USER_INFO_TO_USER_QUIZ_HISTORY_1` FOREIGN KEY (
                                                                                                  `user_id`
    )
    REFERENCES `USER_INFO` (
                            `user_id`
        );
