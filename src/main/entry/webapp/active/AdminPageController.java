package main.entry.webapp.active;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPageController {

	private Map<String,Object> data;
	
	@RequestMapping(value = "/jgy/login")
	public String login(){
		return "login";
	}

	@RequestMapping(value = "/admin/index")
	public String index(){
		return "index";
	}
	
	
	
	
	
	
	
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
