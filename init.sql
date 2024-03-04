--DROP SCHEMA public CASCADE;
--CREATE SCHEMA public;

create table if not exists workplaces (
	w_id smallserial primary key,
	w_name text
);

create table if not exists clients (
	client_id serial primary key,
	client_name text,
	contacts text,
	address text,
	phone text[],
	email text
);

create table if not exists employees (
	emp_id serial primary key,
	emp_name text,
	address text,
	phone text,
	email text,
	education text,
	workplace integer references workplaces (w_id)
);

create table if not exists services (
	serv_id smallserial primary key,
	serv_name text,
	price numeric constraint positive_price check (price > 0)
);

create table if not exists contracts (
	contract_id serial primary key,
	client_id integer references clients (client_id),
	emp_id integer references employees (emp_id),
	serv_id integer references services (serv_id)
)