create table if not exists users
(
    id       serial primary key,
    email    varchar(45),
    name     varchar(100),
    password varchar(45)
);

create table if not exists task
(
    id      serial primary key,
    title   varchar(255),
    content varchar(255),
    user_id bigint references users (id)
);
