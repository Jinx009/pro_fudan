package database.basicFunctions.dao;

import database.common.BaseDao;
import database.models.WechatCache;

public interface WechatCacheDao extends BaseDao<WechatCache>{

	public WechatCache getByAppIdAndType(String hql);
	
}
