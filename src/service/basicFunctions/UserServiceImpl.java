package service.basicFunctions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.UserDao;
import database.models.User;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	
	public User getByOpenId(String openId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" FROM User WHERE  openid ='");
		buffer.append(openId);
		buffer.append("'  ");
		List<User> list = userDao.getByHql(buffer.toString());
		if(null!=list&&!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public User save(User user) {
		return userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public User findById(Integer userId) {
		return userDao.find(userId);
	}

	public List<User> getByHql(String hql) {
		return userDao.getByHql(hql);
	}
}
