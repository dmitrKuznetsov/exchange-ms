drop table if exists operation;
drop sequence if exists operation_seq;

create table operation
(
    id      integer not null,
    type    varchar(255),
    date    date,
    primary key (id)
);

create sequence operation_seq start with 1 increment by 50;