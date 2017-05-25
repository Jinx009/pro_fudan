package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Banner;

public interface BannerDao extends BaseDao<Banner>{

	public List<Banner> findByHql(String hql);
	
}
