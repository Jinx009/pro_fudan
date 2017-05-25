package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.BannerDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.models.active.Address;
import database.models.active.Banner;

@Service("bannerService")
public class BannerServiceImpl implements BannerService{

	@Autowired
	private BannerDao bannerDao;
	
	public Banner save(Banner banner) {
		return bannerDao.save(banner);
	}

	public void update(Banner banner) {
		bannerDao.update(banner);
	}

	public void delete(Integer id) {
		bannerDao.delete(id);
	}

	public List<Banner> findByHql(String hql) {
		return bannerDao.findByHql(hql);
	}
	
	public Banner find(Integer id) {
		return bannerDao.find(id);
	}
	
	
	public PageDataList<Banner> loadDataByPage(int currentPage){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.ASC,"updateTime");
		return bannerDao.findPageList(queryParam);
	}



}
