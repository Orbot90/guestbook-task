create table roles
(
    role_name varchar(20) primary key,
    constraint roles_role_name_uindex
        unique (role_name)
);

create table users
(
    id        bigint auto_increment
        primary key,
    user_name varchar(10) not null,
    password  varchar(50) not null,
    constraint users_username_uindex
        unique (user_name)
);

create table users_roles
(
    user_id bigint not null,
    role_name varchar(20)    not null,
    primary key (user_id, role_name),
    constraint users_roles_roles_id_fk
        foreign key (role_name) references roles (role_name)
            on update cascade on delete cascade,
    constraint users_roles_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
);

insert into roles (role_name) values ('ROLE_USER'), ('ROLE_ADMIN');

alter table posts
    add constraint posts_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade;

alter table posts
    add constraint posts_users_id_fk_2
        foreign key (edited_by) references users (id);

alter table posts
    add approved boolean default false null;
