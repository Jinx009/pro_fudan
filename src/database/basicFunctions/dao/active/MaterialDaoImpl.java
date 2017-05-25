package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Material;

@Repository("materialDao")
public class MaterialDaoImpl extends BaseDaoImpl<Material> implements MaterialDao{

	@SuppressWarnings("unchecked")
	public List<Material> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Material> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
