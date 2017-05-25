package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.ActiveUser;

public interface ActiveUserDao extends BaseDao<ActiveUser>{

	public List<ActiveUser> findByHql(String hql);
	
}
