package common.utils.msg;

import java.util.HashMap;

public class MsgUtil {

	public static void main(String[] args) {
		sendMsg("Jinx么么哒");
	}
	
	public static void sendMsg(String data){
		HashMap<String, Object> result = null;
		CCPRestSDK restAPI = new CCPRestSDK();
		restAPI.init("app.cloopen.com", "8883");// 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		restAPI.setAccount("aaf98f8952f7367a015307ac60871df4","b494706766d44bdf86c60c53cf10d30f");// 初始化主帐号和主帐号TOKEN
		restAPI.setAppId("aaf98f8952f7367a01530843412b2018");// 初始化应用ID
		String[] datas = new String []{data};
		result = restAPI.sendTemplateSMS("18217700275","77675", datas);
		System.out.println("sendTemplateSMS result=" + result);
	}
	
}
