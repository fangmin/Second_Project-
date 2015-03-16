package com.zy.service;

import java.util.List;

import com.zy.bean.Plant;
public interface PlantService {
	
	@SuppressWarnings("rawtypes")
	public List get_Sell();
	
	public List<Plant> get();

	public void update(Plant model);
	
	public void delete(Plant model);
	
	public void delete(String ids);
	
	public void save(Plant model);
	
	public int exist(Plant model);
	
	public Plant getById(int id);
	
	public List<Plant> get(Plant model);
	
	public int count(Plant model);
	
	public List<Plant> get(Plant model, int start, int rows);
}
