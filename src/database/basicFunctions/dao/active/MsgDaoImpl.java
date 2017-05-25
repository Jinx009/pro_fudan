package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Msg;

@Repository("msgDao")
public class MsgDaoImpl extends BaseDaoImpl<Msg> implements MsgDao{

	@SuppressWarnings("unchecked")
	public List<Msg> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Msg> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
