package service.basicFunctions;

import java.util.Date;
import java.util.List;

import database.models.UserConn;

public interface UserConnService {
	
	public UserConn insert(UserConn userConn);
	
	public void update(UserConn userConn);
	
	public List<UserConn> getByHql(String hql);

	public UserConn find(Integer id);

	public int getNum(Date date, Date date2);

}
