create table images
(
    id bigint auto_increment
        primary key,
    name varchar(100) not null,
    image mediumblob null
);

create table posts
(
    id          bigint auto_increment
        primary key,
    user_id     bigint        not null,
    data        varchar(1000) not null,
    date        timestamp      null,
    edited_by   bigint        null,
    edited_date timestamp      null
);
