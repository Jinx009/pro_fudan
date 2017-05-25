package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.CategoryDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.common.SearchFilter.Operators;
import database.models.active.Banner;
import database.models.active.Category;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDao categoryDao;
	
	public void update(Category category) {
		categoryDao.update(category);
	}

	public Category save(Category category) {
		return categoryDao.save(category);
	}

	public List<Category> findByHql(String hql) {
		return categoryDao.findByHql(hql);
	}

	public void delete(Integer id) {
		categoryDao.delete(id);
	}
	
	public Category find(Integer id) {
		return categoryDao.find(id);
	}
	
	
	public PageDataList<Category> loadDataByPage(int currentPage,String type){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.ASC,"fatherId");
		if("1".equals(type)){
		queryParam.addParam("fatherId",0);	
		}else if("2".equals(type)){
			queryParam.addParam("fatherId",Operators.NOTEQ,0);
		}
		return categoryDao.findPageList(queryParam);
	}


}
