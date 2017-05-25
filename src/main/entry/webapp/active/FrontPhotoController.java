package main.entry.webapp.active;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.models.active.Photo;
import service.basicFunctions.active.PhotoService;

@Controller
@RequestMapping("/active/front")
public class FrontPhotoController {

	@Autowired
	private PhotoService photoService;
	private Map<String,Object> data;
	
	@RequestMapping(path = "/photoList")
	public void findByKey(HttpServletRequest request,HttpServletResponse response)throws IOException{
		String hql = " FROM Photo WHERE key like '%"+request.getParameter("key")+"%' ";
		List<Photo> list = photoService.findByHql(hql);
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
