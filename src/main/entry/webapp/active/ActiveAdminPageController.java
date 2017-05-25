package main.entry.webapp.active;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;

/**
 * 
 * @Description: active 后台登录
 * @author 高雄辉
 * @date 2017年1月20日 上午12:52:34
 *
 */
@Controller
public class ActiveAdminPageController {

	private Map<String,Object> data;
	
	@RequestMapping(value = "/active/login")
	public String login(){
		return "active/login";
	}

	@RequestMapping(value = "/active/index")
	public String index(){
		return "active/index";
	}
	
	
	/*
	 * 登录
	 */
	@RequestMapping(value = "/active/doLogin")
	public void doLogin(HttpServletResponse response,HttpServletRequest request) throws IOException{
		data = new HashMap<String,Object>();
		data.put("status","fail");
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		if("admin".equals(userName)&&"admin".equals(password)){
				data.put("status","success");
				request.getSession().setAttribute("sessionUser","success");
		}
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	
	
	
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
