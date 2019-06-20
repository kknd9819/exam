drop table if EXISTS `t_user`;
create table `t_user`(
 `userId` int primary key AUTO_INCREMENT,
 `username` varchar(32) not null,
 `password` varchar(128) not null,
  UNIQUE key `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into t_user values (1,'admin','123456');