package service.basicFunctions;

import java.io.IOException;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.springframework.stereotype.Service;

import database.models.UserTask;

@Service
public interface UserTaskService {
	
	public UserTask insert(UserTask userTask);
	
	public void sendMsg() throws ClientProtocolException, IOException;

	public UserTask getById(Integer id);
	
	public void update(UserTask userTask);

	public int getNum(Date date,Date oldDate);
	
}
