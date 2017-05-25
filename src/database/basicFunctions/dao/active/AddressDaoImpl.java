package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Address;

@Repository("addressDao")
public class AddressDaoImpl extends BaseDaoImpl<Address> implements AddressDao{

	@SuppressWarnings("unchecked")
	public List<Address> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Address> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
