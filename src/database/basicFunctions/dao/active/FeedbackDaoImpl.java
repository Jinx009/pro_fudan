package database.basicFunctions.dao.active;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import database.common.BaseDaoImpl;
import database.models.active.Feedback;

@Repository("feedbackDao")
public class FeedbackDaoImpl extends BaseDaoImpl<Feedback> implements FeedbackDao{

	@SuppressWarnings("unchecked")
	public List<Feedback> findByHql(String hql) {
		Query query = em.createQuery(hql);
		List<Feedback> list = query.getResultList();
		if(list!=null&&!list.isEmpty()){
			return list;
		}
		return null;
	}

}
