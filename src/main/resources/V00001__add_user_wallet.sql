drop table IF EXISTS wallet;
drop table IF EXISTS _user;


drop sequence IF EXISTS wallet_seq;
drop sequence IF EXISTS _user_seq;


create table _user
(
    id       integer not null,
    email    varchar(255),
    password varchar(255),
    role     varchar(255),
    primary key (id)
);



create table wallet
(
    id       integer   not null,
    count    float(53) not null,
    currency varchar(255),
    user_id  integer,
    primary key (id)
);

create sequence _user_seq start with 1 increment by 50;
create sequence wallet_seq start with 1 increment by 50;
alter table if exists wallet add constraint wallet_fk_constraint foreign key (user_id) references _user;

insert into _user values (nextval('_user_seq'), 'admin@mail.ru', 'password', 'ADMIN');
