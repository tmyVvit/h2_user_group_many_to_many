CREATE TABLE `user_group`(
`user_id` BIGINT NOT NULL,
`group_id` BIGINT NOT NULL,
PRIMARY KEY(`user_id`, `group_id`)
)