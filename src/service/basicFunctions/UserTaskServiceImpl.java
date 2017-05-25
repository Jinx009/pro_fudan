package service.basicFunctions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.wechat.WechatData;
import common.wechat.WechatUtil;
import database.basicFunctions.dao.UserTaskDao;
import database.models.UserTask;

@Service
public class UserTaskServiceImpl implements UserTaskService{

	@Autowired
	private UserTaskDao userTaskDao;
	
	public UserTask insert(UserTask userTask) {
		return userTaskDao.save(userTask);
	}

	public void sendMsg() throws ClientProtocolException, IOException {
		System.out.println("Task is woring!");
		Date date = new Date();
		String hql = " from UserTask where status = 0 ";
		List<UserTask> list = userTaskDao.getByHql(hql);
		if(list!=null){
			String accessToken = WechatUtil.getAccessToken(WechatData.APP_ID,WechatData.APP_SECRET);
			for(UserTask userTask:list){
				Date addTime = userTask.getAddTime();
				if(addTime.before(date)){
					WechatUtil.sendMsg(WechatData.getSendMsgData(userTask.getOpenid(),userTask.getText()),accessToken);
					WechatUtil.sendMsg(WechatData.getSendMsgData(userTask.getLikeOpenid(),userTask.getText()),accessToken);
					userTask.setSendTime(date);
					userTask.setStatus(1);
					userTaskDao.update(userTask);
				}		
			}
		}
	}

	public UserTask getById(Integer id) {
		return userTaskDao.find(id);
	}

	public void update(UserTask userTask) {
		userTaskDao.update(userTask);
	}

	public int getNum(Date date,Date oldDate) {
		String hql = " from UserTask where status = 1 and addTime < '"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)+"'"
				+ "and addTime >'"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(oldDate)+"' "
						+ "and userGo = 1 and likeUserGo = 1";
		List<UserTask> list = userTaskDao.getByHql(hql);
		if(list!=null){
			return list.size();
		}
		return 0;
	}

}
