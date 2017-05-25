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
import database.models.active.Category;
import service.basicFunctions.active.CategoryService;

@Controller
@RequestMapping("/active/front")
public class FrontCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	private Map<String,Object> data;

	@RequestMapping(path = "/categoryList")
	public void getIndexCategory(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String hql = " FROM Category WHERE fatherName = '活动类型'  ";
		List<Category> list = categoryService.findByHql(hql);
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
