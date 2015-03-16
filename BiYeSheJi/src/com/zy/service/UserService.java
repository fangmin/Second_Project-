package com.zy.service;

import java.util.List;

import com.zy.bean.User;

public interface UserService {
	
	@SuppressWarnings("rawtypes")
	public List cnt(String start_time, String end_time);
	
	public int exist(User model);
	
	public User check(User model);
	
	public void delete(User model);
	
	public void update(User model);
	
	public User getById(int id);
	
	public int count(User model);
	
	public void save(User model);
	
	public void delete(String ids);
	
	public List<User> get(User model, int start, int rows);
}
