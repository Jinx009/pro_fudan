package database.basicFunctions.dao;

import java.util.List;

import database.common.BaseDao;
import database.models.GiftUser;

public interface GiftUserDao extends BaseDao<GiftUser>{

	public List<GiftUser> findByHql(String hql);
	
}
