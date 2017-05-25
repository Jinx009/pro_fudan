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
import service.basicFunctions.active.ActiveService;

/**
 * 
 * @Description: 活动信息
 * @author 高雄辉
 * @date 2017年1月16日 上午1:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/activeController")
public class ActiveController {

	@Autowired
	private ActiveService activeService;

	/**
	 * 
	 * @Description: 活动信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list() {
		return "active/activeList";
	}

	/**
	 * 
	 * @Description: 活动信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		PageDataList<Active> pageDataList = activeService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1);
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}

	public static Random random = new Random();

	/**
	 * 
	 * @Description: 添加活动
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, Active active) throws IOException {
		if (null!=active.getId()) {
			Active activeOld=activeService.find(active.getId());
			try {
				MyBeanUtils.copyBean2Bean(activeOld,active);
			} catch (Exception e) {
				e.printStackTrace();
			}
			active.setNum(activeOld.getNum());
			active.setAddUser("0");
			activeService.update(active);
		} else {
			active.setAddTime(new Date());
			active.setAddUser("0");
			active.setNum(0);
			activeService.save(active);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除活动
	 *
	 * @param req
	 * @param res
	 * @param activeId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int activeId) throws IOException {
		activeService.delete(activeId);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("删除成功!"), res);
	}

}
