package service.basicFunctions;

import database.models.WechatCache;

public interface WechatCacheService {
	
	public WechatCache save(WechatCache wechatCache);
	
	public void update(WechatCache wechatCache);
	
	public WechatCache getByAppId(String appId, String type);

	public WechatCache getById(Integer id);
}
