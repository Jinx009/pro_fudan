package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.ActiveUser;

public interface ActiveUserService {

	public List<ActiveUser> findByHql(String hql);
	
	public ActiveUser save(ActiveUser activeUser);
	
	public void update(ActiveUser activeUser);
	
	public void delete(Integer id);

	public ActiveUser find(Integer id); 
	
	public PageDataList<ActiveUser> loadDataByPage(int currentPage);
}
