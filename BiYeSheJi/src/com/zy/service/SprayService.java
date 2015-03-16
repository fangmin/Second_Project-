package com.zy.service;

import java.util.List;

import com.zy.bean.Spray;



public interface SprayService {
	
	public void update(Spray model);
	
	public Spray getById(int id);
	
	public void delete(String ids);
	
	public void delete(Spray model);
	
	public void save(Spray model);
	
	public List<Spray> get();
	
	public int exist(Spray model);
	
	public int count(Spray model);
	
	public List<Spray> get(Spray model, int start, int rows);
}
