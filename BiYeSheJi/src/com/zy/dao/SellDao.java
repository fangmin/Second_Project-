package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.zy.bean.Sell;
import com.zy.service.SellService;


@Transactional
public class SellDao implements SellService{
	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/***************method ******************/
	
	public void update(Sell model) {
		entityManager.merge(model);
	}
	
	public Sell getById(int id) {
		return entityManager.find(Sell.class, id);
	}
	
	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Sell s where s.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}
	
	public void delete(Sell model) {
		model = entityManager.getReference(Sell.class, model.getId());
		entityManager.remove(model);
	}
	
	public void save(Sell model) {
		entityManager.persist(model);
	}
	
	@SuppressWarnings("unchecked")
	public List<Sell> get() {
		Query query = entityManager.createQuery("select s FROM Sell s");
		return query.getResultList();
	}
	
	public int exist(Sell model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(s.id) from Sell s  where s.food.plant.kind ='")
				.append(model.getFood().getPlant().getKind()).append("'and s.add_time='")
				.append(model.getAdd_time()).append("'");
		if (model.getId() > 0) {
			sb.append(" and id<>").append(model.getId());
		}

		Query query = getEntityManager().createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}
	@SuppressWarnings("unchecked")
	public List<Sell> get(Sell model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select s FROM Sell s where 1=1");
		if (model != null) {
			if (model.getFood()!= null) {
				if (model.getFood().getPlant() != null)
					if(model.getFood().getPlant().getKind()!=null
						&& !"".equals(model.getFood().getPlant().getKind())) {
					sb.append(" and s.food.plant.kind like '%")
							.append(model.getFood().getPlant().getKind())
							.append("%'");
				}
			}
		}
		sb.append(" order by s.id desc");
		Query query = entityManager.createQuery(sb.toString());//createQuery查找的对象是JPA中的属性，而createNativeQuery用的是sql语句查询的是数据库中的字段
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}
	
	public int count(Sell model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(s) as num FROM Sell s where 1=1");
		if (model != null) {
			if (model.getFood()!= null) {
				if (model.getFood().getPlant() != null)
					if(model.getFood().getPlant().getKind()!=null
						&& !"".equals(model.getFood().getPlant().getKind())) {
					sb.append(" and s.food.plant.kind like '%")
							.append(model.getFood().getPlant().getKind())
							.append("%'");
				}
			}
		}
		Query query = entityManager.createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}
}
