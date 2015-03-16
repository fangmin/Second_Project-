package com.zy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.zy.bean.Fertilize;
import com.zy.service.FertilizeService;

@Transactional
public class FertilizeDao implements FertilizeService {

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
	public List<Fertilize> get() {
		Query query = entityManager.createQuery("select f FROM Fertilize f");
		return query.getResultList();
	}

	public void update(Fertilize model) {
		entityManager.merge(model);
	}

	public void delete(Fertilize model) {
		model = entityManager.getReference(Fertilize.class, model.getId());
		entityManager.remove(model);
	}

	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from Fertilize f where f.id in (").append(ids)
				.append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}

	public void save(Fertilize model) {
		entityManager.persist(model);
	}

	public int exist(Fertilize model) {
		/*Earth earth = new Earth();
		earth.getEarth_num();*/
		StringBuilder sb = new StringBuilder();
			sb.append("select count(f.id) from t_fertilize f ,t_earth e where e.id=f.earth_id and e.earth_num ='")
				.append(model.getEarth().getEarth_num()).append("' and f.add_time='")
				.append(model.getAdd_time()).append("'");
		if (model.getId() > 0) {
			sb.append(" and f.id<>").append(model.getId());
		}
		Query query = getEntityManager().createNativeQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}

	public Fertilize getById(int id) {
		return entityManager.find(Fertilize.class, id);
	}

	public int count(Fertilize model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(f) as num FROM Fertilize f where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getEarth_num() != null
						&& !"".equals(model.getEarth().getEarth_num())) {
					sb.append(" and f.earth.earth_num like '%")
							.append(model.getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		Query query = entityManager.createQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	public List<Fertilize> get(Fertilize model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select f FROM Fertilize f where 1=1");
		if (model != null) {
			if (model.getEarth() != null) {
				if (model.getEarth().getEarth_num() != null
						&& !"".equals(model.getEarth().getEarth_num())) {
					sb.append(" and f.earth.earth_num like '%")
							.append(model.getEarth().getEarth_num())
							.append("%'");
				}
			}
		}
		sb.append(" order by f.id desc");
		Query query = entityManager.createQuery(sb.toString());// createQuery查找的对象是JPA中的属性，而createNativeQuery用的是sql语句查询的是数据库中的字段
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}
}
