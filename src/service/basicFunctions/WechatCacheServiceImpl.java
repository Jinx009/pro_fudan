package service.basicFunctions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.WechatCacheDao;
import database.models.WechatCache;

@Service("wechatCacheService")
public class WechatCacheServiceImpl implements WechatCacheService{

	@Autowired
	private WechatCacheDao wechatCacheDao;
	
	public WechatCache save(WechatCache wechatCache) {
		return wechatCacheDao.save(wechatCache);
	}

	public void update(WechatCache wechatCache) {
		wechatCacheDao.update(wechatCache);
	}

	public WechatCache getByAppId(String appId, String type) {
		String hql = " from WechatCache where appId = '"+appId+"' and cacheName = '"+type+"' ";
		return wechatCacheDao.getByAppIdAndType(hql);
	}

	public WechatCache getById(Integer id) {
		return wechatCacheDao.find(id);
	}

}
