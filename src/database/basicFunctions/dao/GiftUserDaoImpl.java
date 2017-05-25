package database.basicFunctions.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.GiftUser;

@Repository("giftUserDao")
public class GiftUserDaoImpl extends BaseDaoImpl<GiftUser> implements GiftUserDao{

	@SuppressWarnings("unchecked")
	public List<GiftUser> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<GiftUser> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
