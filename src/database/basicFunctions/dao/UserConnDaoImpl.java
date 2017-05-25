package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.UserConn;

@Repository("userConnDao")
public class UserConnDaoImpl extends BaseDaoImpl<UserConn> implements UserConnDao{

	@SuppressWarnings("unchecked")
	public List<UserConn> getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<UserConn> list = query.getResultList();
		if(null!=list&&!list.isEmpty()){
			return list;
		}
		return null;
	}
	
}
