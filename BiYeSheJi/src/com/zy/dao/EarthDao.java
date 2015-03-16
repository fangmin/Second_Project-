package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.zy.bean.Earth;
import com.zy.service.EarthService;

@Transactional
public class EarthDao implements EarthService {
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/************************** method **********************/
	
	@SuppressWarnings("unchecked")
	public List get_Earth(){
		Query query = entityManager.createNativeQuery("select e.id,e.earth_num from t_earth e,t_plant p where p.earth_id=e.id");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Earth> get() {
		Query query = entityManager.createQuery("select m FROM Earth m");
		return query.getResultList();
	}

	public void update(Earth model) {
		entityManager.merge(model);
	}

	public Earth getById(int id) {
		return entityManager.find(Earth.class, id);
	}

	public void delete(Earth model) {
		model = entityManager.getReference(Earth.class, model.getId());
		entityManager.remove(model);
	}

	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Earth m where m.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}

	public void save(Earth model) {
		entityManager.persist(model);
	}

	public int exist(Earth model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from t_earth where earth_num ='")
				.append(model.getEarth_num()).append("'");
		System.out.println("++++++++++++++++++++"+model.getId());
		if (model.getId() > 0) {
			sb.append(" and id<>").append(model.getId());
			// System.out.println("....................."+sb.toString());
		}
		// System.out.println("....................."+sb.toString());
		Query query = getEntityManager().createNativeQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}

	@SuppressWarnings("unchecked")
	public List<Earth> get(Earth model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * FROM t_earth where id>0");
		if (model != null) {
			if (model.getEarth_num() != null
					&& !"".equals(model.getEarth_num())) {
				sb.append(" and earth_num like '%")
						.append(model.getEarth_num().replace("'", ""))
						.append("%'");
			}
		}
		sb.append(" order by id desc");
		Query query = entityManager.createNativeQuery(sb.toString(),
				Earth.class);
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}

	public int count(Earth model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) as num FROM t_earth where id>0");
		if (model != null) {
			if (model.getEarth_num() != null
					&& !"".equals(model.getEarth_num())) {
				sb.append(" and earth_num like '%")
						.append(model.getEarth_num().replace("'", ""))
						.append("%'");
			}
		}
		Query query = entityManager.createNativeQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}
}
