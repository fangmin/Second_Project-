package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.zy.bean.Water;
import com.zy.service.WaterService;


@Transactional
public class WaterDao implements WaterService{
	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/***************method ******************/
	
	public void update(Water model) {
		entityManager.merge(model);
	}
	
	public Water getById(int id) {
		return entityManager.find(Water.class, id);
	}
	
	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Water w where w.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}
	
	public void delete(Water model) {
		model = entityManager.getReference(Water.class, model.getId());
		entityManager.remove(model);
	}
	
	public void save(Water model) {
		entityManager.persist(model);
	}
	
	@SuppressWarnings("unchecked")
	public List<Water> get() {
		Query query = entityManager.createQuery("select w FROM Water w");
		return query.getResultList();
	}
	
	public int exist(Water model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(w) from Water w  where w.id > 0")
		       .append("and w.plant.earth.earth_num='")
				.append(model.getPlant().getEarth().getEarth_num()).append("'");
		//System.out.println("+++++++++++_________________++++++"+sb.toString());
		System.out.println("_______++++++++++++++_______"+model.getId());
		if (model.getId() > 0) {
			sb.append(" and w.id<>").append(model.getId());
		}
		//System.out.println("+++++++++++_________________++++++"+sb.toString());
		Query query = getEntityManager().createQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}
	@SuppressWarnings("unchecked")
	public List<Water> get(Water model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select w FROM Water w where 1=1");
		if (model != null) {
			if (model.getPlant().getEarth() != null) {
				if (model.getPlant().getEarth().getEarth_num() != null
						&& !"".equals(model.getPlant().getEarth().getEarth_num())) {
					sb.append(" and w.plant.earth.earth_num like '%")
							.append(model.getPlant().getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		sb.append(" order by w.id desc");
		Query query = entityManager.createQuery(sb.toString());//createQuery查找的对象是JPA中的属性，而createNativeQuery用的是sql语句查询的是数据库中的字段
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}
	
	public int count(Water model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(w) as num FROM Water w where 1=1");
		if (model != null) {
			if (model.getPlant().getEarth() != null) {
				if (model.getPlant().getEarth().getEarth_num() != null
						&& !"".equals(model.getPlant().getEarth().getEarth_num())) {
					sb.append(" and w.plant.earth.earth_num like '%")
							.append(model.getPlant().getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		Query query = entityManager.createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}
}
