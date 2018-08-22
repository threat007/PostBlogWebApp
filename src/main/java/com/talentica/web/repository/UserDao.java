package com.talentica.web.repository;

import com.talentica.web.model.User;
import com.talentica.web.util.AppUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDao {

	public void createUser(User user) {
		EntityManager entityManager = AppUtil.beginTransaction();
		entityManager.persist(user);
		AppUtil.commitTransaction();
	}

	public List<User> findAll(String searchCriteria) {
		EntityManager entityManager = AppUtil.beginTransaction();
		String query = "select * From User";
		if(searchCriteria != null){
			query = query.concat(" where role = '"+searchCriteria+"'");
		}
		List<User> users = entityManager.createNamedQuery(query,User.class).getResultList();
		AppUtil.commitTransaction();
		return users;
	}

	public User findUser(long userId) {
		EntityManager entityManager = AppUtil.beginTransaction();
		User user = entityManager.find(User.class, userId);
		AppUtil.commitTransaction();
		return user;
	}
}
