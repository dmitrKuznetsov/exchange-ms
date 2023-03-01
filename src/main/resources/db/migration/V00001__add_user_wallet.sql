drop table if exists wallet;
drop table if exists _user;


drop sequence if exists wallet_seq;
drop sequence if exists _user_seq;


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

insert into _user values (nextval('_user_seq'), 'admin@mail.ru', '$2a$10$0sTllaCgabS/FkuzEwDeeOsc9K8phK1lpGwEiEvKnCl3jqn1mj6FC', 'ADMIN');
