package main.entry.webapp.active;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
import database.models.active.ActiveUser;
import service.basicFunctions.active.ActiveUserService;

/**
 * 
 * @Description: 用户
 * @author 高雄辉
 * @date 2017年1月17日 下午22:30:56
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/active/activeUserController")
public class ActiveUserController {

	@Autowired
	private ActiveUserService activeUserService;

	/**
	 * 
	 * @Description: 活动信息页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/list")
	public String list() {
		return "active/activeUserList";
	}

	/**
	 * 
	 * @Description: 用户信息列表数据
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/loadData")
	public void loadData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String currentPage = req.getParameter("currentPage");
		PageDataList<ActiveUser> pageDataList = activeUserService
				.loadDataByPage(StringUtil.isNotBlank(currentPage) ? Integer.parseInt(currentPage) : 1);
		List<ActiveUser> list = pageDataList.getList();
		if(list!=null&&!list.isEmpty()){
			for(ActiveUser activeUser:list){
				activeUser.setNickName(activeUser.getAddress()!=null?activeUser.getAddress().getName():"");
			}
		}
		HttpWebIOHelper._printWebJson(pageDataList, res);
	}

	/**
	 * 
	 * @Description: 添加用户
	 *
	 * @param req
	 * @param res
	 * @throws IOException
	 */
	@RequestMapping(value = "/addOrUpdate")
	public void addOrUpdate(HttpServletRequest req, HttpServletResponse res, ActiveUser activeUser) throws IOException {
		if (null!=activeUser.getId()) {
			ActiveUser activeUserOld=activeUserService.find(activeUser.getId());
			try {
				MyBeanUtils.copyBean2Bean(activeUserOld,activeUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			activeUserService.update(activeUser);
		} else {
			activeUser.setAddTime(new Date());
			activeUserService.save(activeUser);
		}
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("修改成功!"), res);
	}

	/**
	 * 
	 * @Description: 删除用户
	 *
	 * @param req
	 * @param res
	 * @param activeId
	 */
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest req, HttpServletResponse res, int activeId) throws IOException {
		activeUserService.delete(activeId);
		HttpWebIOHelper._printWebJson(AjaxJson.successAjaxJson("删除成功!"), res);
	}

}
