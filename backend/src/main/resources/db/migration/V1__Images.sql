create table images
(
    id bigint auto_increment
        primary key,
    name varchar(100) not null,
    image mediumblob null
);