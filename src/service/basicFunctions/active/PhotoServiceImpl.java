package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.PhotoDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.models.active.People;
import database.models.active.Photo;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService{

	@Autowired
	private PhotoDao photoDao;
	
	public void update(Photo photo) {
		photoDao.update(photo);
	}

	public Photo save(Photo photo) {
		return photoDao.save(photo);
	}

	public List<Photo> findByHql(String hql) {
		return photoDao.findByHql(hql);
	}

	public void delete(Integer id) {
		photoDao.delete(id);
	}

	public Photo find(Integer id) {
		return photoDao.find(id);
	}
	
	public PageDataList<Photo> loadDataByPage(int currentPage){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.DESC,"id");
		return photoDao.findPageList(queryParam);
	}

}
