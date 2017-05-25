package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Address;

public interface AddressService {
	
	public void update(Address address);
	
	public Address save(Address address);
	
	public List<Address> findByHql(String hql);
	
	public void delete(Integer id);
	
	public Address find(Integer id); 
	
	public PageDataList<Address> loadDataByPage(int currentPage);
	
}
