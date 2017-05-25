package main.entry.webapp.page;


import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.wechat.WechatData;
import common.wechat.WechatJSSign;
import common.wechat.WechatUtil;
import database.models.WechatCache;
import service.basicFunctions.WechatCacheService;

@Controller
public class UserPageController {

	@Autowired
	private WechatCacheService wechatCacheService;
	
	/**
	 * 进入身份认证页面
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@RequestMapping(value = "/info")
	public String info(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, IOException{
		// js api处理
		String url = request.getRequestURL().toString();
		String queryString = request.getQueryString();

		if (null != queryString && !"".equals(queryString)) {
			url = url + "?" + queryString;
		}

		String appId = WechatData.APP_ID;
		String appSecret = WechatData.APP_SECRET;
		WechatCache wechatCache = wechatCacheService.getByAppId(appId,
				"JsApi");
		String jsapi_ticket = this.checkWechatCache(appId,appSecret, wechatCache);

		Map<String, String> ret = WechatJSSign.createSign(jsapi_ticket,url, appId, appSecret);

		request.setAttribute("appId", appId);
		request.setAttribute("timestamp", ret.get("timestamp").toString());
		request.setAttribute("nonceStr", ret.get("nonceStr").toString());
		request.setAttribute("signature", ret.get("signature").toString());
		String userId = request.getParameter("userId");
		request.setAttribute("userId",userId);
		return "/info";
	}
	
	/**
	 * 选择去不去
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/go")
	public String go(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String userId = request.getParameter("userId");
		request.setAttribute("userId",userId);
		request.setAttribute("id",id);
		return "/go";
	}
	
	/**
	 * 选择对象页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/select")
	public String select(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		request.setAttribute("userId",userId);
		return "/select";
	}
	
	/**
	 * 互相心仪
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		request.setAttribute("userId",userId);
		return "/list";
	}
	
	/**
	 * 互相心仪选择去否
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listGo")
	public String listGo(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		request.setAttribute("userId",userId);
		return "/go";
	}
	
	/**
	 * 个人详情页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/person")
	public String person(HttpServletRequest request,HttpServletResponse response){
		String userId = request.getParameter("userId");
		String id = request.getParameter("id");
		request.setAttribute("userId",userId);
		request.setAttribute("id",id);
		return "/person";
	}
	
	/**
	 * 校验数据库微信信息
	 * @param wechatCache
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String checkWechatCache(String appId, String appSecret, WechatCache wechatCache) throws ClientProtocolException, IOException{
		//WechatCacheService wechatCacheService = (WechatCacheService) BeanUtil.getBean("wechatCacheService");
		String jsapi_ticket = null;
		
		if (getTimestamp(wechatCache.getLastTimestamp())){
			jsapi_ticket = wechatCache.getCacheValue();
		} 
		else{
			int currentTimestamp = (int) (System.currentTimeMillis() / 1000);
			jsapi_ticket = WechatUtil.getJSApiTicket(appId, appSecret);

			WechatCache wechatCache2 = wechatCacheService.getById(wechatCache.getId());
			wechatCache2.setCacheValue(jsapi_ticket);
			wechatCache2.setLastTimestamp(currentTimestamp);

			wechatCacheService.update(wechatCache2);
		}
		
		return jsapi_ticket;
	}
	
	public static boolean getTimestamp(int timestamp){
		int currentTimestamp = (int) (System.currentTimeMillis() / 1000);

		if ((currentTimestamp - timestamp) >= 7200){
			return false;
		}
		return true;
	}
	
}
