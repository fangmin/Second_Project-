package com.zy.service;

import java.util.List;

import com.zy.bean.Earth;

public interface EarthService {
	
	public List get_Earth();
	
	public void update(Earth model);
	
	public Earth getById(int id);
	
	public void delete(Earth model);
	
	public void delete(String ids);
	
	public void save(Earth model);
	
	public int exist(Earth model);
	
	public int count(Earth model);
	
	public List<Earth> get(Earth model, int start, int rows);
	
	public List<Earth> get();
}
