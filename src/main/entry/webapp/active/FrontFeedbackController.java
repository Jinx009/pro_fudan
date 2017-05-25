package main.entry.webapp.active;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import common.helper.HttpWebIOHelper;
import database.models.active.ActiveUser;
import database.models.active.Feedback;
import service.basicFunctions.active.FeedbackService;

@Controller
@RequestMapping("/active/front")
public class FrontFeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	private Map<String,Object> data;
	
	@RequestMapping(path = "addFeedback")
	public void add(HttpServletRequest request,@RequestBody Feedback feedback,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("sessionUser");
		feedback.setUserId(activeUser.getId());
		feedback.setNickName(activeUser.getName());
		feedback.setPhone(activeUser.getMobilePhone());
		feedback.setAddTime(new Date());
		feedbackService.save(feedback);
		data = new HashMap<String,Object>();
		data.put("status","success");
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	@RequestMapping(path = "feedbackList")
	public void feedback(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute("sessionUser");
		List<Feedback> list = feedbackService.findByHQL(" FROM Feedback WHERE userId = "+activeUser.getId()+" ORDER BY addTime DESC ");
		data = new HashMap<String,Object>();
		data.put("status","success");
		data.put("data",list);
		
		HttpWebIOHelper._printWebJson(data, response);
	}
	
	
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
