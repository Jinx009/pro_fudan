package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Active;

@Repository("activeDao")
public class ActiveDaoImpl extends BaseDaoImpl<Active> implements ActiveDao{

	@SuppressWarnings("unchecked")
	public List<Active> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Active> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
