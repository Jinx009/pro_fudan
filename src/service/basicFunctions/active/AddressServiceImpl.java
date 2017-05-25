package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.AddressDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.models.active.ActiveUser;
import database.models.active.Address;


@Service("addressService")
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDao addressDao;
	
	public void update(Address address) {
		addressDao.update(address);
	}

	public Address save(Address address) {
		return addressDao.save(address);
	}

	public List<Address> findByHql(String hql) {
		return addressDao.findByHql(hql);
	}

	public void delete(Integer id) {
		addressDao.delete(id);
	}
	
	public Address find(Integer id) {
		return addressDao.find(id);
	}
	
	
	public PageDataList<Address> loadDataByPage(int currentPage){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.ASC,"type");
		return addressDao.findPageList(queryParam);
	}


}
