package main.entry.webapp.active;

import java.io.IOException;
import java.util.ArrayList;
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
import database.models.active.Material;
import database.models.active.People;
import service.basicFunctions.active.ActiveService;
import service.basicFunctions.active.MaterialService;
import service.basicFunctions.active.PeopleService;

@Controller
@RequestMapping("/active/front")
public class FrontActiveController {

	@Autowired
	private ActiveService activeService;
	@Autowired
	private PeopleService peopleService;
	@Autowired
	private MaterialService materialService;
	
	private Map<String,Object> data;

	@RequestMapping(path = "/activeList")
	public void getIndexActive(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String type = request.getParameter("type");
		String address = request.getParameter("address");
		String hql = " FROM Active WHERE addUser ='0' ORDER BY openTime DESC ";
		if(type!=null&&!type.isEmpty()){
			hql = " FROM Active WHERE addUser ='0' AND type= '"+type+"' ORDER BY openTime DESC ";
		}
		if(address!=null&&!address.isEmpty()){
			hql = " FROM Active WHERE addUser ='0' AND address= '"+address+"' ORDER BY openTime DESC ";
		}
		if(address!=null&&!address.isEmpty()&&type!=null&&!type.isEmpty()){
			hql = " FROM Active WHERE addUser ='0' AND type= '"+address+"'  AND address= '"+address+"' ORDER BY openTime DESC ";
		}
		List<Active> list = activeService.findByHql(hql);
		if(list!=null){
			for(Active active:list){
				Date date = new Date();
				if(active.getEndTime().after(date)){
					active.setStatus("报名中");
				}else if(active.getEndTime().before(date)&&active.getCloseTime().after(date)){
					active.setStatus("报名已满");
				}else if(active.getCloseTime().before(date)){
					active.setStatus("活动结束");
				}
			}
		}
		List<Active> actives = new ArrayList<Active>();
		List<Active> olds = new ArrayList<Active>();
		List<Active> news = new ArrayList<Active>();
		if(list!=null){
			Date date = new Date();
			for(Active active : list){
				if(active.getOpenTime().before(date)){
					olds.add(active);
				}else{
					news.add(active);
				}
			}
		}
		if(news.size()!=0){
			actives.addAll(news);
		}
		if(olds.size()!=0){
			actives.addAll(olds);
		}
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",actives);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "/activeDetail")
	public void getActiveDetail(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Integer id = Integer.valueOf(request.getParameter("id"));
		Active active = activeService.find(id);
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("sessionUser");
		String hql = " FROM People WHERE activeId = "+id+" AND userId = "+activeUser.getId()+" ";
		List<People> list = peopleService.findByHql(hql);
		active.setDetailImg("3");//可收藏
		active.setAddUser("0");//可申请报名
		if(list!=null){
			for(People people:list){
				if(0==people.getType()){
					active.setAddUser("1");//可取消报名
				}
				if(2==people.getType()){
					active.setDetailImg("2");//可取消收藏
				}
			}
		}
		Date date = new Date();
		if(date.before(active.getStartTime())){
			active.setAddUser("2");//报名未开始
		}
		if(date.after(active.getEndTime())){
			active.setAddUser("3");//报名已结束
		}
		List<Material> materials = materialService.findByHql(" FROM  Material WHERE activeId = "+id+" ORDER BY addTime  ");
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",active);
		data.put("materials", materials);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
