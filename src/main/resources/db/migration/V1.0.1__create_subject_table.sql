use `forum`;

create table `subjects` (
    `id` int auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `content` varchar(500) NOT NULL,
    `date_time` TIMESTAMP NOT NULL,
    `msg_number` float NOT NULL,
    `user_id` int NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    PRIMARY KEY (`id`)
)