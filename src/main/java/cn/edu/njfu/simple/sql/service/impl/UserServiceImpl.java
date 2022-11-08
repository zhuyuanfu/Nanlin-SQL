package cn.edu.njfu.simple.sql.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.njfu.simple.sql.dao.UserRepository;
import cn.edu.njfu.simple.sql.model.User;
import cn.edu.njfu.simple.sql.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Override
	public List<User> listUsers() {
		List<User> result = new ArrayList<User>();
		
		Iterable<User> users = userRepository.findAll();
		Iterator<User> userIterator = users.iterator();
		
		while (userIterator.hasNext()) {
			result.add(userIterator.next());
		}
		return result;
	}

    @Override
	public User getProjectAuthor() {
		return userRepository.getProjectAuthor();
	}

    @Override
	public User getUserById(Long id) {
		return userRepository.getUserById(id);
	}
}
