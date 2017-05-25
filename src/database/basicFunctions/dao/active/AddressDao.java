package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Address;

public interface AddressDao extends BaseDao<Address>{

	
	public List<Address> findByHql(String hql);
	
}
