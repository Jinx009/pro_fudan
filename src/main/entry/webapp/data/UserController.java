package main.entry.webapp.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.ConstantUtil;
import common.helper.HttpWebIOHelper;
import database.models.User;
import database.models.UserConn;
import service.basicFunctions.UserConnService;
import service.basicFunctions.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserConnService userConnService;
	
	private Map<String,Object> data;

	/*
	 * 根据用户id获取用户信息
	 */
	@RequestMapping(value = "/data/user/byId")
	public void userList(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		User user = userService.findById(id);
		
		data = new HashMap<String, Object>();
		data.put(ConstantUtil.CODE,ConstantUtil.CODE_RIGHT);
		data.put(ConstantUtil.MSG,"根据id取用户");
		data.put(ConstantUtil.DATA,user);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/*
	 * 登录
	 */
	@RequestMapping(value = "/doLogin")
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
	
	/**
	 * 获取全部用户
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllUserList")
	public void allUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
			String hql = " FROM User ";
			List<User> list = userService.getByHql(hql);
			
			data = new HashMap<String,Object>();
			data.put("status","success");
			data.put("data",list);
			
			HttpWebIOHelper._printWebJson(data, response);
	}
	/**
	 * 获取可以筛选用户列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getUserList")
	public void getUserList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		User nowUser = userService.findById(id);
		String hql2 = " FROM  UserConn WHERE first = "+id+" or (likeUser.id = "+id+" and ((status = 1) or (status = 4) ) ) ";
		List<UserConn> list2 = userConnService.getByHql(hql2);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String sex = "男";
		if("男".equals(nowUser.getSex())){
			sex = "女";
		}else{
			sex = "男";
		}
		ids.add(id);
		if(null!=list2){
			for(UserConn userConn:list2){
				User user = userConn.getUser();
				User likeUser = userConn.getLikeUser();
				if(user.getId().equals(id)){
					ids.add(likeUser.getId());
				}else
					ids.add(user.getId());
			}
		}
		String idss = "";
		if(ids!=null&&ids.size()>0){
			for(int i = 0;i<ids.size();i++){
				idss += ids.get(i)+",";
			}
			idss = idss.substring(0,idss.length()-1);
		}
		String hql = " FROM User WHERE id not in("+idss+") and sex = '"+sex+"' and status =1 ";
		List<User> list = userService.getByHql(hql);
		List<User> list3 = new ArrayList<User>();
		if(list!=null&&list.size()>20){
			for(int i =0 ;i<20;i++){
				list3.add(list.get(i));
			}
		}else{
			list3 = list;
		}
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list3);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 上传个人信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/saveInfo")
	public void saveInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
//		realName searcher num age address job likes request zodiac edu tag info 
		data= new HashMap<String,Object>();
		System.out.println("进入者姓名："+request.getParameter("realName"));
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		String hql = " from User where num = '"+request.getParameter("num")+"' ";
		List<User> list = userService.getByHql(hql);
//		if(list!=null){
//			data.put("status","false");
//			data.put("data","信息上传成功");
//		}else{
			User user = userService.findById(userId);
			user.setRealName(request.getParameter("realName"));
			user.setSex(request.getParameter("sex"));
			user.setNum("");
			user.setAge(request.getParameter("age"));
			user.setAddress(request.getParameter("address"));
			user.setJobInfo(request.getParameter("job"));
			user.setLikes(request.getParameter("likes"));
			user.setRequest(request.getParameter("request"));
			user.setZodiac(request.getParameter("zodiac"));
			user.setEdu(request.getParameter("edu"));
			user.setTag(request.getParameter("tag"));
			user.setInfo(request.getParameter("info"));
			user.setPic1(request.getParameter("pic1"));
			user.setPic2(request.getParameter("pic2"));
			user.setPic3(request.getParameter("pic3"));
			user.setMobilePhone(request.getParameter("mobilePhone"));
			user.setStatus(1);
			
			userService.update(user);
			request.getSession().setAttribute("name",user.getRealName());
			request.getSession().setAttribute("pic",user.getPic1());
			data.put("status","success");
			data.put("data","信息上传成功");
//		}
		
		HttpWebIOHelper._printWebJson(data, response);
		
	}
	
	
	
	
	
	
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
