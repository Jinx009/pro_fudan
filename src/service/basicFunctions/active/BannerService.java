package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Banner;

public interface BannerService {

	public Banner save(Banner banner);
	
	public void update(Banner banner);
	
	public void delete(Integer id);
	
	public List<Banner> findByHql(String hql);
	
	public Banner find(Integer id); 
	
	public PageDataList<Banner> loadDataByPage(int currentPage);
	
}
