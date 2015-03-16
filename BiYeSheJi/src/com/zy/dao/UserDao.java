package com.zy.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import com.zy.bean.User;
import com.zy.service.UserService;

@Transactional
public class UserDao implements UserService {
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/******************** methods *************************/

	// 收入统计情况
	@SuppressWarnings("rawtypes")
	public List cnt(String start_time, String end_time) {
		StringBuilder sb = new StringBuilder();
		sb.append("select kind,sum(number),sum(total_number) ,time from (");
		sb.append("select kind,time,number,price,number*price as total_number from(");
		sb.append("select p.kind as kind,s.add_time as time,s.number as number,s.price as price from t_sell s,t_plant p,t_food f");
		sb.append(" where s.add_time>='").append(start_time).append("'");
		sb.append(" and s.add_time<='").append(end_time).append("'");
		sb.append(" and s.food_id=f.id and f.plant_id=p.id").append(")m)n");
		sb.append(" group by kind order by number desc");
		/*System.out.println("----sql:"+sb.toString());*/
 
		Query query = entityManager.createNativeQuery(sb.toString());
		List list = query.getResultList();
		/*if(list!=null&&list.size()>0){
			System.out.println("-----------个数："+list.size());
		}else{
			System.out.println("-----------个数：0");
		}*/
		return list;
	}

	public void delete(String ids) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from User m where m.id in (").append(ids).append(")");
		entityManager.createQuery(sb.toString()).executeUpdate();
	}

	public void delete(User model) {
		model = entityManager.getReference(User.class, model.getId());
		entityManager.remove(model);
	}

	public User getById(int id) {
		return entityManager.find(User.class, id);
	}

	public void update(User model) {
		entityManager.merge(model);
	}

	public int exist(User model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from t_user where name ='")
				.append(model.getName()).append("'");
		sb.append(" and idCard ='").append(model.getIdCard()).append("'");
		if (model.getId() > 0) {
			sb.append(" and id<>").append(model.getId());
			// System.out.println("....................."+sb.toString());
		}
		// System.out.println("....................."+sb.toString());
		Query query = getEntityManager().createNativeQuery(sb.toString());
		// System.out.println("....................."+Integer.parseInt(query.getSingleResult().toString()));
		return Integer.parseInt(query.getSingleResult().toString());// 添加成功返回1，失败0
	}

	public void save(User model) {
		entityManager.persist(model);
	}

	@SuppressWarnings("unchecked")
	public List<User> get(User model, int start, int rows) {
		StringBuilder sb = new StringBuilder();
		sb.append("select * FROM t_user where id>1");
		if (model != null) {
			if (model.getName() != null && !"".equals(model.getName())) {
				sb.append(" and name like '%")
						.append(model.getName().replace("'", "")).append("%'");
			}
		}
		sb.append(" order by id desc");

		Query query = entityManager
				.createNativeQuery(sb.toString(), User.class);
		query.setFirstResult(start);
		query.setMaxResults(rows);
		return query.getResultList();
	}

	public int count(User model) {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) as num FROM t_user where id>1");
		if (model != null) {
			if (model.getName() != null && !"".equals(model.getName())) {
				sb.append(" and name like '%")
						.append(model.getName().replace("'", "")).append("%'");
			}
		}

		Query query = entityManager.createNativeQuery(sb.toString());
		return Integer.parseInt(query.getSingleResult().toString());
	}

	@SuppressWarnings("unchecked")
	public User check(User model) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("select * from t_user where account =? and password=?");
		Query query = getEntityManager().createNativeQuery(sb.toString(),
				User.class);
		query.setParameter(1, model.getAccount());
		query.setParameter(2, model.getPassword());
		List<User> list = query.getResultList();
		if (list.size() > 0) {
			User user = list.get(0);
			return user;
		} else {
			return null;
		}
	}
}
