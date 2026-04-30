drop table if exists customers;

create table customers (
    id integer primary key,
    full_name text not null,
    email text not null unique,
    signup_date date not null,
    account_balance numeric(12, 2) not null
);

