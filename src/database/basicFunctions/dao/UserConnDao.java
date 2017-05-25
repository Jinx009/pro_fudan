package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.UserConn;

public interface UserConnDao extends BaseDao<UserConn>{

	public List<UserConn> getByHql(String hql);
	
}
