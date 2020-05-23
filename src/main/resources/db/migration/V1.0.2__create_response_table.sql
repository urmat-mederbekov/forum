use `forum`;

create table `responses` (
    `id` int auto_increment NOT NULL,
    `text` varchar(128) NOT NULL,
    `date_time` TIMESTAMP NOT NULL,
    `subject_id` int NOT NULL,
    `user_id` int NOT NULL,
    FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    PRIMARY KEY (`id`)
)