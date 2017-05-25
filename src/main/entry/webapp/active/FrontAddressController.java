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
import database.models.active.Address;
import service.basicFunctions.active.AddressService;

@Controller
@RequestMapping("/active/front")
public class FrontAddressController {

	@Autowired
	private AddressService addressService;
	
	private Map<String,Object> data;

	@RequestMapping(path = "/fatherAddress")
	public void getFather(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String hql = " FROM Address WHERE type = 0 ";
		List<Address> list = addressService.findByHql(hql);
		
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "address")
	public void getAddress(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String hql = " FROM Address WHERE fatherId = "+request.getParameter("fatherId")+" ";
		List<Address> list = addressService.findByHql(hql);
		
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
