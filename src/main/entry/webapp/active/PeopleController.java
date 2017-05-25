package main.entry.webapp.active;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import common.helper.AjaxJson;
import common.helper.HttpWebIOHelper;
import common.helper.MyBeanUtils;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.active.Active;
import database.models.active.Msg;
import database.models.active.People;
import service.basicFunctions.active.ActiveService;
import service.basicFunctions.active.MsgService;
import service.basicFunctions.active.PeopleService;

/**
 * 
 * @Description: 活动用戶信息
 * @author 高雄辉
 * @date 2017年1月16日 上午1:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/peopleController")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private ActiveService activeService;

	/**
	 * 
	 * @Description: 活动用戶信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list(HttpServletRequest req, HttpServletResponse res) {
		Integer activeId=Integer.parseInt(req.getParameter("activeId"));
		req.setAttribute("activeId",activeId);
		return "active/peopleList";
	}

	/**
	 * 
	 * @Description: 活动用戶信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		Integer activeId=Integer.parseInt(req.getParameter("activeId"));
		PageDataList<People> pageDataList = peopleService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1,activeId);
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}

	public static Random random = new Random();

	/**
	 * 
	 * @Description: 添加活动用戶
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, People people) throws IOException {
		if (null!=people.getId()) {
			People peopleOld=peopleService.find(people.getId());
			try {
				MyBeanUtils.copyBean2Bean(peopleOld,people);
			} catch (Exception e) {
				e.printStackTrace();
			}
			peopleService.update(people);
		} else {
			people.setAddTime(new Date());
			peopleService.save(people);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}
	

	/**
	 * 
	 * @Description: 发送短信
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/sendMsg")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res,String phones,String activeId) throws IOException {
		String[] phoneArray=phones.split("_");
		Active active = activeService.find(Integer.valueOf(activeId));
		for(String phone:phoneArray){
			Msg msg = new Msg();
			msg.setAddTime(new Date());
			msg.setMobilePhone(phone);
			msg.setSendStatus(0);
			msg.setCode("M5813068=q33r9Vpeb");
			msg.setSendContent("【复旦教工】您已经成功报名["+active.getName()+"]活动，请保留此短信至活动结束。退订回复T。");
			msgService.save(msg);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("发送成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除活动用戶
	 *
	 * @param req
	 * @param res
	 * @param peopleId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int peopleId) throws IOException {
		peopleService.delete(peopleId);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("删除成功!"), res);
	}

}
