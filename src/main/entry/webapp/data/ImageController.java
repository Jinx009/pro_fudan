package main.entry.webapp.data;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import common.wechat.WechatUtil;

@Controller
public class ImageController {

	private Map<String,Object> data;

	
	/**
	 * 上传微信图片
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@RequestMapping(value = "/saveImg")
	public void uploadImg(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String wechatMediaId = request.getParameter("wechatMediaId");
		String path = request.getSession().getServletContext().getRealPath("")+"/themes/data/"; 
		String url = WechatUtil.downToLocal(path, wechatMediaId);
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",url);
		HttpWebIOHelper._printWebJson(url, response);
	}
	
	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
