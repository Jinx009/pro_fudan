package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.UserTask;

public interface UserTaskDao extends BaseDao<UserTask>{

	public List<UserTask> getByHql(String hql);
	
}
