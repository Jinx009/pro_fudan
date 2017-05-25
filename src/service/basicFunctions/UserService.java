package service.basicFunctions;

import java.util.List;

import database.models.User;

public interface UserService {

	public User getByOpenId(String openId);

	public User save(User user);
	
	public void update(User user);

	public User findById(Integer userId);
	
	public List<User> getByHql(String hql);

}
