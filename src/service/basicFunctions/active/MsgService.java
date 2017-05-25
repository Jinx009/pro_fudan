package service.basicFunctions.active;

import database.models.active.Msg;

public interface MsgService {

	public void send();
	
	public void save(Msg msg);
	
}
