package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.zy.bean.Food;
import com.zy.service.FoodService;


@Transactional
public class FoodDao implements FoodService{
	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/***************method ******************/
	
	public void update(Food model) {
		entityManager.merge(model);
	}
	
	public Food getById(int id) {
		return entityManager.find(Food.class, id);
	}
	
	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Food f where f.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}
	
	public void delete(Food model) {
		model = entityManager.getReference(Food.class, model.getId());
		entityManager.remove(model);
	}
	
	public void save(Food model) {
		entityManager.persist(model);
	}
	
	@SuppressWarnings("unchecked")
	public List<Food> get() {
		Query query = entityManager.createQuery("select f FROM Food f");
		return query.getResultList();
	}
	
	public int exist(Food model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(f) from Food f where f.plant.kind ='")
				.append(model.getPlant().getKind()).append("'");
		if (model.getId() > 0) {
			sb.append(" and id<>").append(model.getId());
		}
		Query query = getEntityManager().createQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}
	@SuppressWarnings("unchecked")
	public List<Food> get(Food model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select f FROM Food f where f.id>0");
		if (model != null) {
			if (model.getPlant().getEarth() != null) {
				if (model.getPlant().getEarth().getEarth_num() != null
						&& !"".equals(model.getPlant().getEarth().getEarth_num())) {
					sb.append(" and f.plant.earth.earth_num like '%")
							.append(model.getPlant().getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		sb.append(" order by f.id desc");
		Query query = entityManager.createQuery(sb.toString());//createQuery查找的对象是JPA中的属性，而createNativeQuery用的是sql语句查询的是数据库中的字段
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}
	
	public int count(Food model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(f) as num FROM Food f where f.id>0");
		if (model != null) {
			if (model.getPlant().getEarth() != null) {
				if (model.getPlant().getEarth().getEarth_num() != null
						&& !"".equals(model.getPlant().getEarth().getEarth_num())) {
					sb.append(" and f.plant.earth.earth_num like '%")
							.append(model.getPlant().getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		Query query = entityManager.createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}
}
