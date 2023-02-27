truncate table exchange_rate;

insert into exchange_rate values (nextval('exchange_rate_seq'), 'RUB', 'TON', 0.0055);
insert into exchange_rate values (nextval('exchange_rate_seq'), 'RUB', 'BTC', 0.000000568);
insert into exchange_rate values (nextval('exchange_rate_seq'), 'TON', 'RUB', 180.56);
insert into exchange_rate values (nextval('exchange_rate_seq'), 'TON', 'BTC', 0.00010338);
insert into exchange_rate values (nextval('exchange_rate_seq'), 'BTC', 'TON', 9672.73);
insert into exchange_rate values (nextval('exchange_rate_seq'), 'BTC', 'RUB', 1744251.08);