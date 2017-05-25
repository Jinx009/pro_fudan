package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.PeopleDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.models.active.People;

@Service("peopleService")
public class PeopleServiceImpl implements PeopleService{

	@Autowired
	private PeopleDao peopleDao;
	
	public List<People> findByHql(String hql) {
		return peopleDao.findByHql(hql);
	}

	public void update(People people) {
		peopleDao.update(people);
	}

	public People save(People people) {
		return peopleDao.save(people);
	}

	public void delete(Integer id) {
		peopleDao.delete(id);
	}
	
	@Override
	public People find(int id) {
		return peopleDao.find(id);
	}
	
	
	public PageDataList<People> loadDataByPage(int currentPage,int activeId){
		QueryParam queryParam=QueryParam.getInstance();
		
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.DESC,"addTime");
		queryParam.addParam("activeId",activeId);
		queryParam.addParam("type",0);
		queryParam.page.setPernum(100000);
		return peopleDao.findPageList(queryParam);
	}


}
