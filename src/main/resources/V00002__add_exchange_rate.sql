drop table IF EXISTS exchange_rate;

create table exchange_rate
(
    currency_from varchar(255) not null,
    currency_to   varchar(255) not null,
    rate          float(53)    not null,
    primary key (currency_from, currency_to)
);

insert into exchange_rate values ('RUB', 'TON', 0.0055);
insert into exchange_rate values ('RUB', 'BTC', 0.000000568);
insert into exchange_rate values ('TON', 'RUB', 180.56);
insert into exchange_rate values ('TON', 'BTC', 0.00010338);
insert into exchange_rate values ('BTC', 'TON', 9672.73);
insert into exchange_rate values ('BTC', 'RUB', 1744251.08);