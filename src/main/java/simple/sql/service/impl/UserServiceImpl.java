package simple.sql.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import simple.sql.dao.UserRepository;
import simple.sql.model.User;
import simple.sql.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	public List<User> listUsers() {
		List<User> result = new ArrayList<User>();
		
		Iterable<User> users = userRepository.findAll();
		Iterator<User> userIterator = users.iterator();
		
		while (userIterator.hasNext()) {
			result.add(userIterator.next());
		}
		return result;
	}
}
