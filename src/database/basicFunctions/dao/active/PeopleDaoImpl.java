package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.People;

@Repository("peopleDao")
public class PeopleDaoImpl extends BaseDaoImpl<People> implements PeopleDao{

	@SuppressWarnings("unchecked")
	public List<People> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<People> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
