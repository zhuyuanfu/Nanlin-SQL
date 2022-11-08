package cn.edu.njfu.simple.sql.service;

import java.util.List;

import cn.edu.njfu.simple.sql.model.User;

public interface UserService {
	List<User> listUsers();
	User getProjectAuthor();
	User getUserById(Long id);
}
