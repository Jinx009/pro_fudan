package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.GiftUserDao;
import database.models.GiftUser;

@Service("giftUserService")
public class GiftUserServiceImpl implements GiftUserService{

	@Autowired
	private GiftUserDao giftUserDao;
	
	public List<GiftUser> findByHql(String hql) {
		return giftUserDao.findByHql(hql);
	}

	public GiftUser insert(GiftUser giftUser) {
		return giftUserDao.save(giftUser);
	}

	public void update(GiftUser giftUser) {
		giftUserDao.update(giftUser);
	}

	public GiftUser find(Integer id) {
		return giftUserDao.find(id);
	}

}
