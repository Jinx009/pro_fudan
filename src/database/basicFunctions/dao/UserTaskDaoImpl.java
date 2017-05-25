package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.UserTask;

@Repository("userTaskDao")
public class UserTaskDaoImpl extends BaseDaoImpl<UserTask> implements UserTaskDao{

	@SuppressWarnings("unchecked")
	public List<UserTask> getByHql(String hql) {
		Query query = em.createQuery(hql);
		List<UserTask> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
