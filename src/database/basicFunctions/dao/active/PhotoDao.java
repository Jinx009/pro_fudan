package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Photo;

public interface PhotoDao extends BaseDao<Photo>{

	public List<Photo> findByHql(String hql);
	
}
