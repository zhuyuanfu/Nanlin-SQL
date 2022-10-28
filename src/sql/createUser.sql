create table user (
	id bigint primary key not null auto_increment,
	user_name varchar(16),
	password varchar(32),
	email varchar(32),
	employee_number varchar(32),
	created_time timestamp default now(),
	updated_time timestamp default now()
) engine=INNODB default charset=utf8 collate utf8_general_ci;