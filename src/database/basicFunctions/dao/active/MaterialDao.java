package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Material;

public interface MaterialDao extends BaseDao<Material>{

	public List<Material> findByHql(String hql);
	
}
