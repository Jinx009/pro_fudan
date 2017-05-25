package service.basicFunctions.active;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.active.FeedbackDao;
import database.common.PageDataList;
import database.common.QueryParam;
import database.common.OrderFilter.OrderType;
import database.models.active.Feedback;

@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackDao feedbackDao;
	
	public void save(Feedback feedback) {
		feedbackDao.save(feedback);
	}

	public List<Feedback> findByHQL(String hql) {
		return feedbackDao.findByHql(hql);
	}

	public void delete(Integer id) {
		feedbackDao.delete(id);
	}

	
	public PageDataList<Feedback> loadDataByPage(int currentPage){
		QueryParam queryParam=QueryParam.getInstance();
		queryParam.addPage(currentPage);
		queryParam.addOrder(OrderType.DESC,"addTime");
		return feedbackDao.findPageList(queryParam);
	}

	
}
