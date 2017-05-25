package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Msg;

public interface MsgDao extends BaseDao<Msg>{

	public List<Msg> findByHql(String hql);
	
}
