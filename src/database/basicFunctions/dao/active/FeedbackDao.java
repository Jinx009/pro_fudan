package database.basicFunctions.dao.active;

import java.util.List;

import database.common.BaseDao;
import database.models.active.Feedback;

public interface FeedbackDao extends BaseDao<Feedback>{
	
	public List<Feedback> findByHql(String hql);
	

}
