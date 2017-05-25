package main.entry.webapp.page;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import service.basicFunctions.UserService;

import common.wechat.WechatData;
import common.wechat.WechatUtil;

import database.models.User;

@Controller
public class PageController {
	
	@Autowired
	private UserService userService;

	
	/**
	 * 微信授权获取昵称
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/rights")
	public String rights(HttpServletRequest request){
		String code = request.getParameter("code");;
		User user = (User) request.getSession().getAttribute("user");
		if(null==user){
			if (null != code && !"".equals(code)) {
				JSONObject result = WechatUtil.getUserInfoFirst(code,WechatData.APP_ID, WechatData.APP_SECRET);
				if(null!=result){
					System.out.println("第一次微信授权获取信息："+result.toString());
					String accessToken = result.get("access_token").toString();
					String openid = result.get("openid").toString();
					System.out.println(accessToken+"---"+openid);
					user = userService.getByOpenId(openid);
					JSONObject json = WechatUtil.getRealUserInfo(accessToken, openid);
					System.out.println("userInfo:" + json);
					System.out.println("userInfo:" + json.toString());
					if(null==user){
						user = new User();
						user.setAddTime(new Date());
						user.setHeadImg(json.getString("headimgurl").toString());
						user.setLoginTime(new Date());
						user.setNickName(getRealNickName(json.getString("nickname").toString()));
						user.setLoginTimes(1);
						user.setOpenid(openid);
						user.setStatus(1);
						
						user =  userService.save(user);
						request.getSession().setAttribute("user",user);
					}else{
						user.setNickName(getRealNickName(json.getString("nickname").toString()));
						user.setHeadImg(json.getString("headimgurl").toString());
						user.setLoginTimes(user.getLoginTimes()+1);
						
						userService.update(user);
						request.getSession().setAttribute("user",user);
					}
				}
			} 
		}
		return "/rights";
	}
	
	/**
	 * 去除微信昵称中的表情
	 * @param nick
	 * @return
	 */
	public static String getRealNickName(String nick){
		StringBuilder nicksb = new StringBuilder();
		int l = nick.length();
		for (int i = 0; i < l; i++) {
			char _s = nick.charAt(i);
			if (isEmojiCharacter(_s)) {
				nicksb.append(_s);
			}
		}
		return nicksb.toString();
	}
	
	/**
	 * 判断昵称包不包含表情
	 * @param codePoint
	 * @return
	 */
	public static boolean isEmojiCharacter(char codePoint){
	     return (codePoint==0x0)||(codePoint==0x9)||
	    		(codePoint==0xA)||(codePoint==0xD)||
	    		((codePoint>=0x20)&&(codePoint<= 0xD7FF))||
	    		((codePoint>=0xE000)&&(codePoint<= 0xFFFD))||
	    		((codePoint>=0x10000)&&(codePoint<=0x10FFFF));
	 }
	
	/**
	 * 地图页
	 * @return
	 */
	@RequestMapping(value = "/map")
	public String map(){
		return "/map";
	}
	
	/**
	 * 说明页
	 * @return
	 */
	@RequestMapping(value = "/res")
	public String res(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("status",request.getParameter("status"));
		request.setAttribute("userId",request.getParameter("userId"));
		return "/res";
	}
}
