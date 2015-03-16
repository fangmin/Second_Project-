package com.zy.service;

import java.util.List;

import com.zy.bean.Sell;

public interface SellService {
	
	public void update(Sell model);
	
	public Sell getById(int id);
	
	public void delete(String ids);
	
	public void delete(Sell model);
	
	public void save(Sell model);
	
	public List<Sell> get();
	
	public int exist(Sell model);
	
	public int count(Sell model);
	
	public List<Sell> get(Sell model, int start, int rows);
}
