package service.basicFunctions.active;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.wechat.MsgUtil;
import database.basicFunctions.dao.active.MsgDao;
import database.models.active.Msg;

@Service("msgService")
public class MsgServiceImpl implements MsgService{

	@Autowired
	private MsgDao msgDao;

	public void send() {
		List<Msg> list = msgDao.findByHql(" FROM Msg WHERE sendStatus = 0 ");
		if(list!=null){
			for(Msg msg : list){
//				HashMap<String, Object> result = null;
//				CCPRestSDK restAPI = new CCPRestSDK();
//				restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
//				restAPI.setAccount("aaf98f8952f7367a015307ac60871df4","b494706766d44bdf86c60c53cf10d30f");// 初始化主帐号和主帐号TOKEN
//				restAPI.setAppId("aaf98f8952f7367a01530843412b2018");// 初始化应用ID
//				String[] datas = new String []{msg.getMobilePhone(),msg.getSendContent()};
//				result = restAPI.sendTemplateSMS(msg.getMobilePhone(),msg.getCode(), datas);
//				System.out.println("sendTemplateSMS result=" + result+"-----"+msg.getAddTime().toLocaleString());
				try {
					MsgUtil.send(msg.getCode(), msg.getMobilePhone(), msg.getSendContent());
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				msg.setSendStatus(1);
				msgDao.update(msg);
			}
		}
	}

	public void save(Msg msg) {
		msgDao.save(msg);
	}
	
}
