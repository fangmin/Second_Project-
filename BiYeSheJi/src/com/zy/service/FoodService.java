package com.zy.service;

import java.util.List;

import com.zy.bean.Food;

public interface FoodService {
	
public void update(Food model);
	
	public Food getById(int id);
	
	public void delete(String ids);
	
	public void delete(Food model);
	
	public void save(Food model);
	
	public List<Food> get();
	
	public int exist(Food model);
	
	public int count(Food model);
	
	public List<Food> get(Food model, int start, int rows);
}
