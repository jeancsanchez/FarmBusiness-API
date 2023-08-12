CREATE TABLE user_roles(
	user_id int not null,
    role varchar(50) not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);