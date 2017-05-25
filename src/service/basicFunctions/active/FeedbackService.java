package service.basicFunctions.active;

import java.util.List;

import database.common.PageDataList;
import database.models.active.Feedback;

public interface FeedbackService {

	public void save(Feedback feedback);
	
	public List<Feedback> findByHQL(String hql);
	
	public void delete(Integer id);

	public PageDataList<Feedback> loadDataByPage(int currentPage);
	
}
