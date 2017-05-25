package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.People;

public interface PeopleDao extends BaseDao<People>{

	public List<People> findByHql(String hql);
	
}
