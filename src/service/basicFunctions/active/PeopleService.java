package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.People;

public interface PeopleService {

	public List<People> findByHql(String hql);
	
	public void update(People people);
	
	public People save(People people);
	
	public void delete(Integer id);
	public People find(int id);

	public PageDataList<People> loadDataByPage(int currentPage,int activeId);
	
}
