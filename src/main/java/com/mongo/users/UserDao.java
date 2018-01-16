package com.mongo.users;

import java.util.List;

import com.mongo.users.User;

public interface UserDao {
	
	public void create(User user);
	 
    public void update(User user);
 
    public void delete(User user);
 
    public void deleteAll();
 
    public User find(String id);
 
    public List<User> findAll();
}
