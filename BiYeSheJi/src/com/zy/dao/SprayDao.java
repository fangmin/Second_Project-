package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.zy.bean.Spray;
import com.zy.service.SprayService;


@Transactional
public class SprayDao implements SprayService{
	
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/***************method ******************/
	
	public void update(Spray model) {
		entityManager.merge(model);
	}
	
	public Spray getById(int id) {
		return entityManager.find(Spray.class, id);
	}
	
	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Spray s where s.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}
	
	public void delete(Spray model) {
		model = entityManager.getReference(Spray.class, model.getId());
		entityManager.remove(model);
	}
	
	public void save(Spray model) {
		entityManager.persist(model);
	}
	
	@SuppressWarnings("unchecked")
	public List<Spray> get() {
		Query query = entityManager.createQuery("select s FROM Spray s");
		return query.getResultList();
	}
	
	public int exist(Spray model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(s) from Spray s  where s.add_time ='")
				.append(model.getAdd_time()).append("' and s.earth.earth_num='")
				.append(model.getEarth().getEarth_num()).append("'");
		if (model.getId() > 0) {
			sb.append(" and id<>").append(model.getId());
		}
		Query query = getEntityManager().createQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}
	@SuppressWarnings("unchecked")
	public List<Spray> get(Spray model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select s FROM Spray s where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getEarth_num() != null
						&& !"".equals(model.getEarth().getEarth_num())) {
					sb.append(" and s.earth.earth_num like '%")
							.append(model.getEarth().getEarth_num())
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
	
	public int count(Spray model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(s) as num FROM Spray s where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getEarth_num() != null
						&& !"".equals(model.getEarth().getEarth_num())) {
					sb.append(" and s.earth.earth_num like '%")
							.append(model.getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		Query query = entityManager.createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}
}
