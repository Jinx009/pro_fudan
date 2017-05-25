package service.basicFunctions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import database.basicFunctions.dao.UserConnDao;
import database.models.UserConn;

@Service("userConnService")
public class UserConnServiceImpl implements UserConnService{

	@Autowired
	private UserConnDao userConnDao;
	
	public UserConn insert(UserConn userConn) {
		return userConnDao.save(userConn);
	}

	public void update(UserConn userConn) {
		userConnDao.update(userConn);
	}

	public List<UserConn> getByHql(String hql) {
		return userConnDao.getByHql(hql);
	}

	public UserConn find(Integer id) {
		return userConnDao.find(id);
	}

	public int getNum(Date date, Date date2) {
		String hql = " from UserConn where status = 1 and addTime < '"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+"'"
				+ "and addTime >'"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2)+"' "
						+ "and userGo = 1 and likeGo = 1";
		List<UserConn> list = userConnDao.getByHql(hql);
		if(list!=null){
			return list.size();
		}
		return 0;
	}

}
