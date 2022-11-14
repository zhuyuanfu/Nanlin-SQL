create table `meta_field` (
	id bigint primary key not null auto_increment,
	table_id bigint not null,
	data_type varchar(32),
	name varchar(64),
	chinese_name varchar(32),
	comment varchar(128),
	created_time timestamp default now(),
	updated_time timestamp default now(),
	is_deleted tinyint default false
) engine=INNODB default charset=utf8 collate utf8_general_ci;