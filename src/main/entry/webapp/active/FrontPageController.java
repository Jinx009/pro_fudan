package main.entry.webapp.active;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.utils.UnicodeUtil;
import common.wechat.WechatData;
import common.wechat.WechatUtil;
import database.models.active.ActiveUser;
import net.sf.json.JSONObject;
import service.basicFunctions.active.ActiveUserService;

@Controller
@RequestMapping("/active/page")
public class FrontPageController {
	
	@Autowired
	private ActiveUserService activeUserService;

	private Map<String,Object> data;

	@RequestMapping(path = "/show")
	public String show(HttpServletRequest request,HttpServletResponse response){
		return "/active/front/show";
	}
	
	/**
	 * 修改个人资料
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/update")
	public String update(HttpServletRequest request,HttpServletResponse response){
		return checkSession(request,response,"/active/page/update");
	}
	
	/**
	 * 绑定手机号
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/bind")
	public String bind(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("url",request.getParameter("url"));
		return "/active/front/bind";
	}
	
	/**
	 * 首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/index")
	public String activeIndex(HttpServletRequest request,HttpServletResponse response){
		request.setAttribute("type",request.getParameter("type"));
//		return checkSession(request,response,"/active/page/index");
		return "/active/front/index";
	}
	
	/**
	 * 活动详情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/detail")
	public String detail(HttpServletRequest request,HttpServletResponse response){
		return checkSession(request,response,"/active/page/detail");
//		return "/active/front/detail";
	}
	
	/**
	 * 我参与的
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/join")
	public String join(HttpServletRequest request,HttpServletResponse response){
		return checkSession(request, response, "/active/page/join");
	}
	
	/**
	 * 我收藏的
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/like")
	public String like(HttpServletRequest request,HttpServletResponse response){
		return checkSession(request, response,"/active/page/like");
	}
	
	/**
	 * 反馈
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/feedback")
	public String feedback(HttpServletRequest request,HttpServletResponse response){
		return checkSession(request,response,"/active/page/feedback");
	}
	
	/**
	 * 微相册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/photo")
	public String photo(HttpServletRequest request,HttpServletResponse response){
//		return checkSession(request,response,"/active/page/photo");
		return "/active/front/photo";
	}
	
	/**
	 * 我的
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/my")
	public String my(HttpServletRequest request,HttpServletResponse response){
		return checkSession(request,response,"/active/page/my");
	}
	
	/**
	 * 微信重定向
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(path = "/status")
	public String status(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String id = request.getParameter("id");
		String url = request.getParameter("url");
		System.out.println(url+"--"+id);
		ActiveUser activeUser = null;
		if (null != code && !"".equals(code)) {
			JSONObject result = WechatUtil.getUserInfoFirst(code,WechatData.APP_ID, WechatData.APP_SECRET);
			if(null!=result){
				System.out.println("第一次微信授权获取信息："+result.toString());
				String accessToken = result.get("access_token").toString();
				String openid = result.get("openid").toString();
				List<ActiveUser> list = activeUserService.findByHql(" FROM ActiveUser WHERE openId = '"+openid+"' ");
				if(list==null){
					JSONObject json = WechatUtil.getRealUserInfo(accessToken, openid);
					System.out.println("userInfo:" + json.toString());
					activeUser = new ActiveUser();
					activeUser.setAddTime(new Date());
					activeUser.setBindstatus(0);
					activeUser.setNickName(UnicodeUtil.stringToUnicode(json.getString("nickname").toString()));
					activeUser.setImg(json.getString("headimgurl").toString());
					activeUser.setOpenId(openid);
					activeUserService.save(activeUser);
					
				}else{
					activeUser = list.get(0);
				}
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("sessionUser",activeUser);
		if(0==activeUser.getBindstatus()){
			return "redirect:http://edu.wishworks.cn/active/page/bind.html?url="+url+"&id="+id;
		}
		return "redirect:"+url+"?id="+id;
	}
	
	/**
	 * session过滤器
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 */
	private String checkSession(HttpServletRequest request,HttpServletResponse response,String url){
		String id = request.getParameter("id");
		request.setAttribute("id",id);
		HttpSession session = request.getSession();
//		ActiveUser activeUser = activeUserService.find(1);
//		session.setAttribute("sessionUser",activeUser);
		ActiveUser user = null;
		System.out.println(session.getAttribute("sessionUser"));
		if(null!=session.getAttribute("sessionUser")&&!"".equals(session.getAttribute("sessionUser"))){
			user = (ActiveUser) session.getAttribute("sessionUser");
		}
		String[] arr = url.split("/");
		if(user!=null){
			return arr[1]+"/front/"+arr[3];
		}else{
			return "redirect:/active/page/bind.html?url="+url+".html?id="+id;
//			return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WechatData.APP_ID+"&redirect_uri=http://edu.wishworks.cn/active/page/status.html?url="+url+".html%26id="+id+"&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		}
	}
	
	
	
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
