create table if not exists `student`
(
    `id`    bigint(20) not null auto_increment,
    `email` varchar(255) null,
    `name`  varchar(255) null,
    primary key (`id`)
) engine = MyISAM
 DEFAULT CHARSET = utf8mb4;


create table if not exists `attendance_record`
(
    `id`   bigint(20) not null auto_increment,
    `date`     date         null,
    `reason`     varchar(255) null,
    `status`     varchar(255) null,
    `student_id` bigint(20) not null,
    primary key (`id`),
    foreign key (`student_id`) references student (`id`)
) engine = MyISAM
 DEFAULT CHARSET = utf8mb4;

