package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Photo;

@Repository("photoDao")
public class PhotoDaoImpl extends BaseDaoImpl<Photo> implements PhotoDao{

	@SuppressWarnings("unchecked")
	public List<Photo> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Photo> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
