package com.talentica.web.service;

import com.talentica.web.model.User;
import com.talentica.web.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void createUser(User user) {
		userDao.createUser(user);
	}

	public List<User> findAll(long userId) {
		User user = userDao.findUser(userId);
		if(user.getRole().equals(User.UserRole.ADMIN)){
			return userDao.findAll(User.UserRole.BLOGGER.name());
		}else if(user.getRole().equals(User.UserRole.BLOGGER)){
			return null;
		}else {
			return userDao.findAll(null);
		}

	}
}
