package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.ActiveUserDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.models.active.ActiveUser;

@Service("activeUserService")
public class ActiveUserServiceImpl implements ActiveUserService{

	@Autowired
	private ActiveUserDao activeUserDao;
	
	public List<ActiveUser> findByHql(String hql) {
		return activeUserDao.findByHql(hql);
	}

	public ActiveUser save(ActiveUser activeUser) {
		return activeUserDao.save(activeUser);
	}

	public void update(ActiveUser activeUser) {
		activeUserDao.update(activeUser);
	}

	public void delete(Integer id) {
		activeUserDao.delete(id);
	}

	public ActiveUser find(Integer id) {
		return activeUserDao.find(id);
	}
	
	
	public PageDataList<ActiveUser> loadDataByPage(int currentPage){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.DESC,"addTime");
		return activeUserDao.findPageList(queryParam);
	}

}
