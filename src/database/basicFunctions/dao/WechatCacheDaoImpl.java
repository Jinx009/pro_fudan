package database.basicFunctions.dao;


import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.WechatCache;

@Repository("wechatCacheDao")
public class WechatCacheDaoImpl extends BaseDaoImpl<WechatCache> implements WechatCacheDao{

	@SuppressWarnings("unchecked")
	public WechatCache getByAppIdAndType(String hql) {
		Query query = em.createQuery(hql);
		List<WechatCache> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

}
