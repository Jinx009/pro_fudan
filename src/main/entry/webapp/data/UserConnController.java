package main.entry.webapp.data;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.models.User;
import database.models.UserConn;
import service.basicFunctions.UserConnService;
import service.basicFunctions.UserService;
import database.models.UserConnModel;

@Controller
public class UserConnController{

	@Autowired
	private UserConnService userConnService;
	@Autowired
	private UserService userService;
	
	private Map<String,Object> data;

	/**
	 * 后台获取匹配成功的对象
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getConnSuccess")
	public void getSuccess(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		String hql = " FROM UserConn WHERE status = 1 ";
		List<UserConn> list = userConnService.getByHql(hql);
		List<UserConnModel> res = new ArrayList<UserConnModel>();
		if(list!=null){
			for(UserConn userConn : list){
				UserConnModel userConnModel = new UserConnModel();
				userConnModel.setAddTime(userConn.getAddTime());
				userConnModel.setLikeUserId(userConn.getLikeUser().getId());
				userConnModel.setLikeUserName(userConn.getLikeUser().getRealName());
				userConnModel.setUserId(userConn.getUser().getId());
				userConnModel.setUserName(userConn.getUser().getRealName());
				userConnModel.setMeetingDate(userConn.getMeetingDate());
				res.add(userConnModel);
			}
		}
		Date showTime = new Date();
		int hour = showTime.getHours();
		int showHour = hour+1;
		Date date = new Date();
		date.setHours(showHour);
		date.setMinutes(10);
		Date date2 = new Date();
		date2.setHours(hour);
		date.setMinutes(10);
		int num = userConnService.getNum(date,date2);
		data.put("status","success");
		data.put("data",res);
		data.put("nextTime",showHour);
		data.put("num",num);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 获取某个人互相喜欢列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPersonConnSuccess")
	public void getPersonSuccess(HttpServletRequest request,HttpServletResponse response) throws IOException{
		data = new HashMap<String,Object>();
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer status = Integer.valueOf(request.getParameter("status"));
		String hql = " FROM UserConn WHERE status = 1 AND (user.id = "+id+" or likeUser.id = "+id+") ";
		if(1==status){
			hql = " FROM UserConn WHERE (status = 1 AND (user.id = "+id+" or likeUser.id = "+id+")) or (status = 0 AND first="+id+") ";
		}
		
		List<UserConn> list = userConnService.getByHql(hql);
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	/**
	 * 喜欢我的列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	 @RequestMapping(value = "/getPersonConnLikeMe")
	public void likeMe(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 data = new HashMap<String,Object>();
		Integer id = Integer.valueOf(request.getParameter("id"));
		String hql = " FROM UserConn WHERE status = 0 AND likeUser.id = "+id+" ";
		List<UserConn> list = userConnService.getByHql(hql);
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
  }
  
  	/**
	 * 我喜欢的列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	 @RequestMapping(value = "/getPersonConnILike")
	public void iLike(HttpServletRequest request,HttpServletResponse response) throws IOException{
		 data = new HashMap<String,Object>();
		Integer id = Integer.valueOf(request.getParameter("id"));
		String hql = " FROM UserConn WHERE status = 0 AND user.id = "+id+" ";
		List<UserConn> list = userConnService.getByHql(hql);
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
  }
	
	/**
	 * 添加一条喜欢记录
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/insertConn")
	public void insertUserConn(HttpServletRequest request,HttpServletResponse response) throws Exception{
		data = new HashMap<String,Object>();
		data.put("code","0");
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		Integer likeUserId = Integer.valueOf(request.getParameter("likeUserId"));
		User user = userService.findById(userId);
		User likeUser = userService.findById(likeUserId);
		String hql = " FROM UserConn WHERE ((likeUser.id = "+likeUserId+") and (user.id = "+userId+")) or ((likeUser.id = "+userId+") and (user.id = "+likeUserId+")) ";
		List<UserConn> list = userConnService.getByHql(hql);
		if(null!=list){//互相喜欢
			UserConn userConn = list.get(0);
			userConn.setStatus(1);
			userConn.setUserGo(1);
			userConn.setLikeGo(1);
			if((userConn.getLikeUser().getId().equals(userId)&&userConn.getUser().getId().equals(likeUserId))||
				(userConn.getLikeUser().getId().equals(likeUserId)&&userConn.getUser().getId().equals(userId))){
				data.put("code","1");
				Date date = new Date();
				userConn.setAddTime(date);
				userConn.setMeetingDate(getMeetingDate(date));
				userConnService.update(userConn);
			}
		}else{//单向喜欢
			UserConn userConn = new UserConn();
			userConn.setAddTime(new Date());
			userConn.setLikeUser(likeUser);
			userConn.setUser(user);
			userConn.setStatus(0);
			userConn.setFirst(user.getId());
			userConn.setLikeGo(0);
			userConn.setUserGo(0);
			
			userConnService.insert(userConn);
		}
		
		
		data.put("status","success");
		
		HttpWebIOHelper._printWebJson(data, response);
		
	}
	
	/**
	 * 添加一个不喜欢的对象
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/insertDisConn")
	public void insertDisConn(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		Integer likeUserId = Integer.valueOf(request.getParameter("likeUserId"));
		User user = userService.findById(userId);
		User likeUser = userService.findById(likeUserId);
		
		UserConn userConn = new UserConn();
		userConn.setAddTime(new Date());
		userConn.setLikeUser(likeUser);
		userConn.setUser(user);
		userConn.setStatus(4);//4表示不喜欢
		userConn.setFirst(user.getId());
		
		data = new HashMap<String,Object>();
		userConnService.insert(userConn);
		data.put("status","success");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	/**
	 * 选择不去了
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/connDoGo")
	public void donotgo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer userId = Integer.valueOf(request.getParameter("userId"));
		UserConn userConn = userConnService.find(id);
		if(userId==userConn.getUser().getId()){
			userConn.setUserGo(0);
		}else{
			userConn.setLikeGo(0);
		}
		userConnService.update(userConn);
		data = new HashMap<>();
		data.put("status","success");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	public static String getMeetingDate(Date date) throws Exception{
//		11月26日10:50以前	11月26日11:00
//		11月26日10:51-12:50	11月26日13:00
//		11月26日12:51-14:55	11月26日15:00
//		11月27日9:50之前	11月27日10:00
//		11月27日9:51-10:50	11月27日11:00
//		11月27日10:51-13:00	11月27日13:00
		String[] strs = {"2016-11-26 10:50:00","2016-11-26 12:50:00","2016-11-26 14:55:00","2016-11-27 9:50:00","2016-11-27 10:50:00","2016-11-27 13:00:00"};
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = new Date();
		date2 = simpleDateFormat.parse(strs[0]);
		if(date2.after(date)){
			return "11月26日11:00";
		}
		date2 = simpleDateFormat.parse(strs[1]);
		if(date2.after(date)){
			return "11月26日13:00";
		}
		date2 = simpleDateFormat.parse(strs[2]);
		if(date2.after(date)){
			return "11月26日15:00";
		}
		date2 = simpleDateFormat.parse(strs[3]);
		if(date2.after(date)){
			return "11月27日10:00";
		}
		date2 = simpleDateFormat.parse(strs[4]);
		if(date2.after(date)){
			return "11月27日11:00";
		}
		date2 = simpleDateFormat.parse(strs[5]);
		if(date2.after(date)){
			return "11月27日13:00";
		}
			
		return "约会活动已结束";	
	}
	
	
	
	
	
	
	
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
