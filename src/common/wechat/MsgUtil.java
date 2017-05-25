package common.wechat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


@SuppressWarnings("deprecation")
public class MsgUtil {


	public static final String USER = "M5813068";
	public static final String PWD = "q33r9Vpeb";
	
	/**
	 * 发送短信
	 * @param appId
	 * @param appSecret
	 * @param code
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@SuppressWarnings({ "resource"})
	public static String send(String code,String phone,String msg) throws ClientProtocolException, IOException{
		StringBuffer buffer = new StringBuffer();
		buffer.append("https://sms.253.com/msg/send?un=");
		buffer.append(code.split("=")[0]);
		buffer.append("&pw=");
		buffer.append(code.split("=")[1]);
		buffer.append("&phone=");
		buffer.append(phone);
		buffer.append("&msg=");
		buffer.append(msg);
		buffer.append("&rd=1");
		
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(buffer.toString());
		HttpResponse httpResponse;
		httpResponse = client.execute(httpGet);
		String strResult = EntityUtils.toString(httpResponse.getEntity(),WechatData.CHAR_SET);
		System.out.println(buffer.toString()+phone+"短信内容："+msg+strResult);

		
		return null;
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		send("N4061481=WE5FRbQHD19cfb","18217700275","【巾帼园】亲爱的18217700275:您的绑定验证码是:[990328]");
	}
}
