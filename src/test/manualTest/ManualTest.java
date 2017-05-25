package test.manualTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import common.helper.HttpWebIOHelper;
import common.helper.nbReturn;
import common.helper.nbStringUtil;


@Controller
public class ManualTest {

	 
	
	@RequestMapping(value = "/test/rantest") 
	public ModelAndView checkToken(){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("hello", "hellow world!");
		
		ModelAndView mav = new ModelAndView("home",data);
		return mav;
	}

	@RequestMapping(value = "/testMD5") 
	public ModelAndView index2() throws Exception{  
			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatedDate=dateformat1.format(new Date());
			String text = "a2345678901234567890123456789012a2345678901234567890123456789012a2345678901234567890123456789012"+formatedDate;
			String ret = nbStringUtil.encryptMD5(text);
			Map<String,Object> data = new HashMap<String,Object>();  
			data.put("helloWorld", ret );
			ModelAndView mav = new ModelAndView("home",data);
			return mav;  
		}
	
	@RequestMapping(value = "/testJsonOutput") 
	public void jsonOutput(HttpServletResponse response,HttpServletRequest request) throws Exception{  
		
		nbReturn ret = new nbReturn();
		Map<String, Object> test = new HashMap<String, Object>();
		String output = "asdf";
		test.put("1", output);
		test.put("2", output);
		ret.setObject(test);
		
		HttpWebIOHelper.printReturnJson(ret, response);
		}
}
