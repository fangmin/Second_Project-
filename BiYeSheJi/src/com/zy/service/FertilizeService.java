package com.zy.service;

import java.util.List;

import com.zy.bean.Fertilize;

public interface FertilizeService {
	
	public List<Fertilize> get();

	public void update(Fertilize model);
	
	public void delete(Fertilize model);
	
	public void delete(String ids);
	
	public void save(Fertilize model);
	
	public int exist(Fertilize model);
	
	public Fertilize getById(int id);
	
	public int count(Fertilize model);
	
	public List<Fertilize> get(Fertilize model, int start, int rows);
}
