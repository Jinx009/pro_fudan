package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Active;

public interface ActiveService {

	public Active save(Active active);
	
	public Active find(int id);
	
	public void update(Active active);
	
	public List<Active> findByHql(String hql);
	
	public void delete(Integer id);
	
	public PageDataList<Active> loadDataByPage(int currentPage);
	
}
