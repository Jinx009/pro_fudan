package common.wechat;

import common.helper.MD5Util;

public class WechatData {
	
	public static final String APP_ID = "wx363951969cd36e51";
	public static final String APP_SECRET = "c93f64984555059f2d93e614b97b2c19";
	public static final String TOKEN = "test";
	public static final String CHAR_SET = "utf-8";
	public static final String OAUTH_URL_ONE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APP_ID+"&redirect_uri=";
	public static final String OAUTH_URL_TWO = "http://www.jinxq.club";
	public static final String OAUTH_URL_THREE = "&response_type=code&scope=snsapi_base&state=state#wechat_redirect";
	
	
	public static final String SEND_MSG_NAME = "Acoount_Change";//第一个模板消息名称
	public static final String SEND_MSG_ID = "djQaizDLkvoGv3jFUFsWWVhcM8XyBoIKJL52rbYacJY";//第一个模板消息id
	
	/**
	 * 获取第一个模板消息要发送的内容
	 * @param value
	 * @return
	 */
	public static String getSendMsgData(String...value){
		String msg = " { "+
			      "\"touser\":\""+value[0]+"\","+
			      "\"template_id\":\""+value[1]+"\", "+
			      "\"url\":\""+value[2]+"\","+
			      "\"topcolor\":\"#FF0000\","+
			      "\"data\":{ "+
			            "\"first\": {"+
			                 "\"value\":\""+value[3]+"\","+
			                 "\"color\":\"#173177\" "+
			                 "},"+
				             "\"publicproductname\": {"+
				             "\"value\":\""+value[4]+"\","+
				             "\"color\":\"#173177\" "+
				             "},"+
					          "\"expectedyield\": {"+
				             "\"value\":\""+value[5]+"\","+
				             "\"color\":\"#173177\" "+
				             "},"+
				             "\"term\": {"+
				             "\"value\":\""+value[6]+"\","+
				             "\"color\":\"#173177\" "+
				             "},"+
					         "\"remark\":{"+
					         "\"value\":\""+value[7]+"\","+
					         "\"color\":\"#000000\" "+
					         "}"+
					       "}"+
			         "}";
			return msg; 
	}
	
	
	
	/**
	 * 重定向链接
	 * @return
	 */
	public static String getTeacherOauthUrl(){
		StringBuffer buffer = new StringBuffer();
		return buffer.toString();
	}
	public static void main(String[] args) {
		String pwd = MD5Util.toMD5("admin");
		System.out.println(pwd);
	}
}
