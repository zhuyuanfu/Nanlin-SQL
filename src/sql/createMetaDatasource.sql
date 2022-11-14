create table meta_datasource (
	id bigint primary key not null auto_increment,
	name varchar(32),
	chinese_name varchar(32),
	comment varchar(128),
	datasource_type varchar(32),
	host varchar(32),
	port varchar(32),
	driver_class varchar(128),
	connection_account varchar(32),
	connection_password varchar(32),
	created_time timestamp default now(),
	updated_time timestamp default now(),
	is_deleted tinyint default false
) engine=INNODB default charset=utf8 collate utf8_general_ci;