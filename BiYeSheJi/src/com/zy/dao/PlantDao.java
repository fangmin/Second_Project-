package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;
import com.zy.bean.Plant;
import com.zy.service.PlantService;

@Transactional
public class PlantDao implements PlantService {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/************* method *****************/

	@SuppressWarnings("unchecked")
	public List<Plant> get() {
		Query query = entityManager.createQuery("select p FROM Plant p");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List get_Sell() {
		Query query = entityManager
				.createNativeQuery("select f.id,p.kind,f.number from t_food f,t_plant p where p.id=f.plant_id");
		return query.getResultList();
	}

	public void update(Plant model) {
		entityManager.merge(model);
	}

	public void delete(Plant model) {
		model = entityManager.getReference(Plant.class, model.getId());
		entityManager.remove(model);
	}

	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Plant p where p.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}

	public void save(Plant model) {
		entityManager.persist(model);
	}

	public int exist(Plant model) {
		/*
		 * Earth earth = new Earth(); earth.getEarth_num();
		 */
		StringBuilder sb = new StringBuilder();
		sb.append(
				"select count(1) from t_earth e,t_plant p where e.id=p.earth_id and e.earth_num ='")
				.append(model.getEarth().getEarth_num()).append("'");
		if (model.getId() > 0) {
			sb.append(" and p.id<>").append(model.getId());
		}
		Query query = getEntityManager().createNativeQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}

	public Plant getById(int id) {
		return entityManager.find(Plant.class, id);
	}

	public int count(Plant model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(p) as num FROM Plant p where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getEarth_num() != null
						&& !"".equals(model.getEarth().getEarth_num())) {
					sb.append(" and p.earth.earth_num like '%")
							.append(model.getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		Query query = entityManager.createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<Plant> get(Plant model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select p FROM Plant p where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getEarth_num() != null
						&& !"".equals(model.getEarth().getEarth_num())) {
					sb.append(" and p.earth.earth_num like '%")
							.append(model.getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		sb.append(" order by p.id desc");
		Query query = entityManager.createQuery(sb.toString());// createQuery查找的对象是JPA中的属性，而createNativeQuery用的是sql语句查询的是数据库中的字段
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Plant> get(Plant model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select p FROM Plant p where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getId() > 0) {
					sb.append(" and p.earth.id=").append(
							model.getEarth().getId());
				}

			}
		}
		Query query = entityManager.createQuery(sb.toString());// createQuery查找的对象是JPA中的属性，而createNativeQuery用的是sql语句查询的是数据库中的字段
		return query.getResultList();
	}
}
