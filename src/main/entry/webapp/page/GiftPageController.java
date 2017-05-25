package main.entry.webapp.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GiftPageController {

	@RequestMapping(value="/gift")
	public String giftPage(){
		return "gift/index";
	}
	
}
