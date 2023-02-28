create table operation
(
    id      integer not null,
    date    timestamp(6),
    type    varchar(255),
    user_id integer,
    primary key (id)
)

alter table if exists operation add constraint operation_fk_constraint foreign key (user_id) references _user