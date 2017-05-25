package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Active;

public interface ActiveDao extends BaseDao<Active>{

	public List<Active> findByHql(String hql);
	
}
