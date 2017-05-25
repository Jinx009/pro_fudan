package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Material;

public interface MaterialService {

	public Material save(Material material);
	
	public Material find(int id);
	
	public void update(Material material);
	
	public List<Material> findByHql(String hql);
	
	public void delete(Integer id);
	
	public PageDataList<Material> loadDataByPage(int currentPage,int activeId);
	
}
