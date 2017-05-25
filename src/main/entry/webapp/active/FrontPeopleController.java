package main.entry.webapp.active;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.models.active.Active;
import database.models.active.ActiveUser;
import database.models.active.People;
import service.basicFunctions.active.ActiveService;
import service.basicFunctions.active.PeopleService;

@Controller
@RequestMapping("/active/front")
public class FrontPeopleController {

	@Autowired
	private PeopleService peopleService;
	@Autowired
	private ActiveService activeService;
	
	private Map<String,Object> data;
	
	@RequestMapping(path = "/likeOrJoin")
	public void getLike(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		ActiveUser activeUser = (ActiveUser) session.getAttribute("sessionUser");
		String hql = " FROM People WHERE userId = "+activeUser.getId()+" AND type = "+type+" ";
		List<People> list = peopleService.findByHql(hql);
		
		data = new HashMap<String,Object>();
		data.put("status","success");
		if(list!=null){
			String ids = "";
			for(int i = 0;i<list.size();i++){
				ids += list.get(i).getActiveId()+",";
			}
			ids = ids.substring(0,ids.length()-1);
			hql = " FROM Active WHERE id in ("+ids+") ";
			List<Active> actives = activeService.findByHql(hql);
			data.put("data",actives);
		}else{
			data.put("msg", "无数据");
		}
		HttpWebIOHelper._printWebJson(data, response);
	}

	@RequestMapping(path = "/updateLikeOrJoin")
	public void saveOrUpdate(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		String activeId = request.getParameter("activeId");
		ActiveUser activeUser = (ActiveUser) session.getAttribute("sessionUser");
		String hql = " FROM People WHERE activeId = "+activeId+" AND userId = "+activeUser.getId()+" ";
		List<People> list = peopleService.findByHql(hql);
		String result = "success";
		int status = 0;
		if(list!=null){
			for(People people:list){
				if("0".equals(String.valueOf(people.getType()))&&"1".equals(type)){
					people.setType(Integer.valueOf(type));
					peopleService.update(people);
					status = 1;
				}
				if("0".equals(String.valueOf(people.getType()))&&"0".equals(type)){
					people.setType(Integer.valueOf(type));
					peopleService.update(people);
					status = 1;
				}
				if("2".equals(String.valueOf(people.getType()))&&"3".equals(type)){
					people.setType(Integer.valueOf(type));
					peopleService.update(people);
					status = 1;
				}
				if("3".equals(String.valueOf(people.getType()))&&"2".equals(type)){
					people.setType(Integer.valueOf(type));
					peopleService.update(people);
					status = 1;
				}
				if("1".equals(String.valueOf(people.getType()))&&"0".equals(type)){
					people.setType(Integer.valueOf(type));
					peopleService.update(people);
					status = 1;
				}
			}
		}
		if(status==0){
			if(Integer.valueOf(type)==0||"0".equals(type)){
				if(activeUser.getName()==null||"".equals(activeUser.getName())){
					result = "fail";
				}else{
					People people = new People();
					Active active = activeService.find(Integer.valueOf(activeId));
					people.setActiveId(Integer.valueOf(activeId));
					people.setActiveName(active.getName());
					people.setAddTime(new Date());
					people.setMobiePhone(activeUser.getMobilePhone());
					people.setNickName(activeUser.getEmail());
					people.setType(Integer.valueOf(type));
					people.setUserId(activeUser.getId());
					people.setDanwei(activeUser.getOpenId());
					people.setName(activeUser.getName());
					peopleService.save(people);
					
					active.setNum(active.getNum()+1);
					activeService.update(active);
				}
			}else{
				People people = new People();
				Active active = activeService.find(Integer.valueOf(activeId));
				people.setActiveId(Integer.valueOf(activeId));
				people.setActiveName(active.getName());
				people.setAddTime(new Date());
				people.setMobiePhone(activeUser.getMobilePhone());
				people.setNickName(activeUser.getEmail());
				people.setDanwei(activeUser.getOpenId());
				people.setType(Integer.valueOf(type));
				people.setName(activeUser.getName());
				people.setUserId(activeUser.getId());
				peopleService.save(people);
			}
		}
		
		data = new HashMap<String,Object>();
		data.put("status",result);

		HttpWebIOHelper._printWebJson(data, response);
	}
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
