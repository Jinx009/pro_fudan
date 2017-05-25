package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Category;

public interface CategoryDao extends BaseDao<Category>{

	public List<Category> findByHql(String hql);
	
}
