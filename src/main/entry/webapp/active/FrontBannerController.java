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
import database.models.active.Banner;
import service.basicFunctions.active.BannerService;

/**
 * 首页banner
 * @author jinx
 *
 */
@Controller
@RequestMapping("/active/front")
public class FrontBannerController {
	
	@Autowired
	private BannerService bannerService;
	
	private Map<String,Object> data;
	
	@RequestMapping(path = "/bannerList")
	public void getIndexBanner(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String hql = "  FROM Banner ORDER BY updateTime DESC  ";
		List<Banner> list = bannerService.findByHql(hql);
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}

	
	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
