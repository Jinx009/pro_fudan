package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.MaterialDao;
import database.common.OrderFilter.OrderType;
import database.common.PageDataList;
import database.common.QueryParam;
import database.models.active.Material;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService{

	@Autowired
	private MaterialDao materialDao; 
	

	public Material save(Material material) {
		return materialDao.save(material);
	}
	

	@Override
	public Material find(int id) {
		return materialDao.find(id);
	}
	
	public void update(Material material) {
		materialDao.update(material);
	}

	public List<Material> findByHql(String hql) {
		return materialDao.findByHql(hql);
	}

	public void delete(Integer id) {
		materialDao.delete(id);
	}
	
	public PageDataList<Material> loadDataByPage(int currentPage,int activeId){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addParam("activeId",activeId);
		queryParam.addOrder(OrderType.DESC,"addTime");
		return materialDao.findPageList(queryParam);
	}



}
