package com.zy.service;

import java.util.List;
import com.zy.bean.Water;



public interface WaterService {
	
	public void update(Water model);
	
	public Water getById(int id);
	
	public void delete(String ids);
	
	public void delete(Water model);
	
	public void save(Water model);
	
	public List<Water> get();
	
	public int exist(Water model);
	
	public int count(Water model);
	
	public List<Water> get(Water model, int start, int rows);
}
