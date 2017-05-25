package main.entry.webapp.active;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import common.helper.AjaxJson;
import common.helper.HttpWebIOHelper;
import common.helper.StringUtil;
import database.common.PageDataList;
import database.models.active.Feedback;
import service.basicFunctions.active.FeedbackService;

/**
 * 
 * @Description: 用户反馈
 * @author 高雄辉
 * @date 2017年1月17日 下午22:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/feedbackController")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	/**
	 * 
	 * @Description: 用户反馈信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list() {
		return "active/feedbackList";
	}

	/**
	 * 
	 * @Description: 用户反馈信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		PageDataList<Feedback> pageDataList = feedbackService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1);
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}

	/**
	 * 
	 * @Description: 添加用户反馈
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, Feedback feedback) throws IOException {
		feedbackService.save(feedback);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除用户反馈
	 *
	 * @param req
	 * @param res
	 * @param activeId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int feedbackId) throws IOException {
		feedbackService.delete(feedbackId);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("删除成功!"), res);
	}

}
