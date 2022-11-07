create table `database` (
	id bigint primary key not null auto_increment,
	data_source_id bigint not null,
	name varchar(32),
	chinese_name varchar(32),
	comment varchar(128),
	created_time timestamp default now(),
	updated_time timestamp default now(),
	is_deleted tinyint default false
) engine=INNODB default charset=utf8 collate utf8_general_ci;