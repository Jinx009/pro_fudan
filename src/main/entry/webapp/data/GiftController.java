package main.entry.webapp.data;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.models.GiftUser;
import service.basicFunctions.GiftUserService;

/**
 * 抽奖活动
 * @author jinx
 *
 */
@Controller
public class GiftController {

	@Autowired
	private GiftUserService giftUserService;
	
	private Map<String,Object> data;

	/**
	 * 更新抽奖状态
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/gift/update")
	public void update(HttpServletRequest req,HttpServletResponse res) throws IOException{
		data = new HashMap<>();
		Integer id = Integer.valueOf(req.getParameter("id"));
		String giftValue = req.getParameter("giftValue");
		GiftUser giftUser = giftUserService.find(id);
		giftUser.setGiftStatus(1);
		giftUser.setGiftTime(new Date());
		giftUser.setGiftValue(giftValue);
		giftUserService.update(giftUser);
		data.put("status","success");
		HttpWebIOHelper._printWebJson(data, res);
		
	}
	
	/**
	 * 获取可抽奖用户
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/gift/list")
	public void getGiftUser(HttpServletRequest req,HttpServletResponse res) throws IOException{
		data = new HashMap<>();
		String hql = "FROM GiftUser WHERE giftStatus = 0 ";
		List<GiftUser> list = giftUserService.findByHql(hql);
		data.put("status","success");
		data.put("data",list);
		HttpWebIOHelper._printWebJson(data, res);
	}
	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
