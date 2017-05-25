package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Banner;

@Repository("bannerDao")
public class BannerDaoImpl extends BaseDaoImpl<Banner> implements BannerDao{

	@SuppressWarnings("unchecked")
	public List<Banner> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Banner> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
