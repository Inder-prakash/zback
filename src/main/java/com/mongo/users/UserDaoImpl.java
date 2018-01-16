package com.mongo.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongo.users.UserDao;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
	@Autowired
    MongoTemplate mongoTemplate;
	
	final String COLLECTION = "users";

	public void create(User user) {
		mongoTemplate.insert(user);
	}

	public void update(User user) {
		mongoTemplate.save(user);
	}

	public void delete(User user) {
		mongoTemplate.remove(user);
	}

	public void deleteAll() {
		 mongoTemplate.remove(new Query(), COLLECTION);
	}

	public User find(String id) {
		User u = mongoTemplate.findById(id, User.class, COLLECTION); 
		return u;
	}

	public List<User> findAll() {
		return (List <User> ) mongoTemplate.findAll(User.class);
	}
}
