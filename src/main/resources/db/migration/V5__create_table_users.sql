CREATE TABLE users(
	id int auto_increment primary key,
    status varchar(100) not null,
    email varchar(255) not null unique,
    password varchar(255),
    first_name varchar(255),
    cpf varchar(255),
    company varchar(255),
    fantasy_name varchar(255),
    cnpj varchar(255),
    phone varchar(255)
);