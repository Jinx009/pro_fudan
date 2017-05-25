package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Photo;

public interface PhotoService {

	public void update(Photo photo);
	
	public Photo save(Photo photo);
	
	public List<Photo> findByHql(String hql);
	
	public void delete(Integer id);
	
	public Photo find(Integer id);
	
	public PageDataList<Photo> loadDataByPage(int currentPage);
	
}
