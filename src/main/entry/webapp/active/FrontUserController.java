package main.entry.webapp.active;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.models.active.ActiveUser;
import database.models.active.Address;
import database.models.active.Msg;
import service.basicFunctions.active.ActiveUserService;
import service.basicFunctions.active.AddressService;
import service.basicFunctions.active.MsgService;

@Controller
@RequestMapping("/active/front")
public class FrontUserController {

	@Autowired
	private ActiveUserService activeUserService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private AddressService addressService;
	
	private Map<String,Object> data;
	
	@RequestMapping(path = "/_login")
	public void _login(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<ActiveUser> lists = activeUserService.findByHql(" FROM ActiveUser WHERE mobilePhone = '"+request.getParameter("mobilePhone")+"'");
		data = new HashMap<String,Object>();
		if(lists!=null&&!lists.isEmpty()){
			ActiveUser activeUser = lists.get(0); 
			HttpSession session = request.getSession();
			session.setAttribute("sessionUser",activeUser);
			data.put("status","success");
		}else{
			data.put("status","fail");
		}
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "/sendBindMsg")
	public void sendBindMsg(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Random rand = new Random();
		int randNum = rand.nextInt(899999)+100000;
		String phone = request.getParameter("mobilePhone");
		Msg msg = new Msg();
		msg.setAddTime(new Date());
		msg.setMobilePhone(phone);
		msg.setSendStatus(0);
		msg.setSendContent("【复旦教工】您的短信验证码是["+randNum+"]。");
		msg.setCode("N4061481=WE5FRbQHD19cfb");
		HttpSession session = request.getSession();
		session.setAttribute(phone,String.valueOf(randNum));
		msgService.save(msg);
		data = new HashMap<String,Object>();
		data.put("status","success");
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "/bind")
	public void bind(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String code = request.getParameter("code");
		String phone = request.getParameter("mobilePhone");
		HttpSession session = request.getSession();
		String sessionCode = (String) session.getAttribute(phone);
		data = new HashMap<String,Object>();
		System.out.println(code+"---"+phone+"---"+sessionCode);
		if(sessionCode.equals(code)){
			String hql = " FROM ActiveUser WHERE mobilePhone = '"+phone+"' ";
			List<ActiveUser> list = activeUserService.findByHql(hql);
			if(list!=null){
				session.setAttribute("sessionUser",list.get(0));
			}else{
				ActiveUser activeUser = new ActiveUser();
				activeUser.setMobilePhone(phone);
				activeUser.setAddTime(new Date());
				activeUser.setBindstatus(1);
				activeUser.setNickName("");
				activeUser.setImg("");
				activeUser = activeUserService.save(activeUser);
				session.setAttribute("sessionUser",activeUser);
			}
			data.put("status","success");
		}else{
			data.put("status","验证码错误");
		}
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "/activeUser")
	public void getUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("sessionUser");
		activeUser = activeUserService.find(activeUser.getId());
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",activeUser);
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "/updateUser")
	public void updateUser(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		ActiveUser activeUser2 = (ActiveUser) session.getAttribute("sessionUser");
		activeUser2.setName(request.getParameter("name"));
		activeUser2.setSex(request.getParameter("sex"));
		activeUser2.setEmail(request.getParameter("email"));
		activeUser2.setOpenId(request.getParameter("openId"));
		Integer id = Integer.valueOf(request.getParameter("id"));
		Address address = addressService.find(id);
		activeUser2.setAddress(address);
		activeUserService.update(activeUser2);
		session.setAttribute("sessionUser",activeUser2);
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",activeUser2);
		HttpWebIOHelper._printWebJson(data, response);
	}

	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
