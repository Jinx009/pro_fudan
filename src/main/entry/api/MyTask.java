package main.entry.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import service.basicFunctions.active.MsgService;


@Component
@Lazy(value=false)
public class MyTask {
	
	@Autowired
	private MsgService msgService;
	
	
	@Scheduled(cron = "*/2 * * * * ?")//每隔2秒执行一次
    public void test() throws Exception {
		msgService.send();
    }


    //@Scheduled(cron = "0 0 1 * * ?")//每天凌晨1点整
    //@Scheduled(cron = "0 30 0 * * ?")//每天凌晨0点30分
    //@Scheduled(cron = "0 */60 * * * ?")//1小时处理一次
}