package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.ActiveUser;

@Repository("activeUserDao")
public class ActiveUserDaoImpl extends BaseDaoImpl<ActiveUser> implements ActiveUserDao{

	@SuppressWarnings("unchecked")
	public List<ActiveUser> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<ActiveUser> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
