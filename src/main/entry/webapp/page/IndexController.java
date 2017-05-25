package main.entry.webapp.page;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.wechat.WechatData;
import common.wechat.WechatUtil;
import database.models.User;
import service.basicFunctions.UserService;

@Controller
public class IndexController {

	@Autowired
	private UserService userService;
	
	private Map<String,Object> data;
	
	/**
	 * 进入首页带动画特效
	 * @param request
	 * @param response
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		String code = request.getParameter("code");
		System.out.println("code:"+code);
		Integer userId = 0;
		Integer status = 0;
		if(null!=code&&!"".equals(code)){
			String openid = WechatUtil.getOauthOpenId(WechatData.APP_ID, WechatData.APP_SECRET,code);
			if(null!=openid){
				String hql = " FROM User WHERE openid = '"+openid+"' ";
				User user = null;
				List<User> list = userService.getByHql(hql);
				if(null!=list){
					user = list.get(0);
				}
				if(null!=user){
					status = user.getStatus();
					request.getSession().setAttribute("name", user.getRealName());
					request.getSession().setAttribute("pic",user.getPic1());
					request.setAttribute("name", user.getRealName());
					request.setAttribute("pic",user.getPic1());
				}else{
					user = new User();
					user.setAddTime(new Date());
					user.setOpenid(openid);
					user.setStatus(0);
					user.setLoginTime(new Date());
					user.setLoginTimes(1);
					user = userService.save(user);
				}
				status = user.getStatus();
				System.out.println(status);
				userId = user.getId();
			}
		}
		request.setAttribute("userId",userId);
		request.setAttribute("status",status);
		User user2 = userService.findById(3);
//		request.getSession().setAttribute("name", user2.getRealName());
//		request.getSession().setAttribute("pic",user2.getPic1());
		return "/index";
	}
	
	/**
	*登录页面
	*/
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request,HttpServletResponse response){
		return "/login";	
	}
	
	
	
	/**
	*用户列表页面
	*/
	@RequestMapping(value = "/adminIndex")
	public String adminIndex(HttpServletRequest request,HttpServletResponse response){
		String sessionStatus = (String) request.getSession().getAttribute("sessionUser");
		if(sessionStatus==null||!"success".equals(sessionStatus)){
			return "/login";	
		}
		return "/adminIndex";	
	}
	
	/**
	*成功匹配列表
	*/
	@RequestMapping(value = "/adminSecond")
	public String adminSecond(HttpServletRequest request,HttpServletResponse response){
		String sessionStatus = (String) request.getSession().getAttribute("sessionUser");
		if(sessionStatus==null||!"success".equals(sessionStatus)){
			return "/login";	
		}
		return "/adminSecond";	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 中文转Unicode编码
	 * @param string
	 * @return
     */
	public static String string2Unicode(String string) {
	    StringBuffer unicode = new StringBuffer();
	    for (int i = 0; i < string.length(); i++) {
	        // 取出每一个字符
	        char c = string.charAt(i);
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	    return unicode.toString();
	}
	
	  /**
     * Unicode编码转String
     * @param unicode
     * @return
     */
    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
