package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Category;

public interface CategoryService {

	public void update(Category category);
	
	public Category save(Category category);
	
	public List<Category> findByHql(String hql);
	
	public void delete(Integer id);
	
	
	public Category find(Integer id); 
	
	public PageDataList<Category> loadDataByPage(int currentPage,String type);
	
}
