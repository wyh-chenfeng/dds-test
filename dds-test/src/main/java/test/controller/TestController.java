package test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import model.mybatis.Syndrome;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.syndrome.SyndromeService;

@Controller
@RequestMapping("/") 
public class TestController {
	private final Logger log = Logger.getLogger(TestController.class);
	
	@Autowired
	private SyndromeService syndromeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		log.debug("================访问controller  index=====================");
		
		return "index";
	}

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public String test(){
		log.debug("================访问controller test=====================");
		
//		List<Syndrome> list2 = syndromeService.findAll();
//		Gson gson = new Gson();
//		log.debug("==============================");
////		log.debug(gson.toJson(list));
//		log.debug(gson.toJson(list2));
//		log.debug("==============================");
//		
//		Syndrome syndrome = new Syndrome();
//		
//		syndrome.setDescription("描述");
//		syndrome.setSymptomName("名字");
//		syndrome.setSyndromeElementEnd("A");
//		syndrome.setSyndromeElementStart("B"); 
//		syndrome.setSymptomCategory("symptomCategory");
//		
//		syndromeService.create(syndrome);
//		
//		log.debug("==============================");
//		log.debug(gson.toJson(syndrome.getId()));
//		log.debug("==============================");
		
		final Syndrome syndrome = new Syndrome();
		
		syndrome.setDescription("描述");
		syndrome.setSymptomName("名字");
		syndrome.setSyndromeElementEnd("A");
		syndrome.setSyndromeElementStart("B"); 
		syndrome.setSymptomCategory("symptomCategory");
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				new Thread(){
					public void run() {
						System.out.println("============= run ===========");
						syndromeService.test(syndrome);
						syndromeService.findAll();
						syndromeService.findAll();
						syndromeService.test(syndrome);
						syndromeService.findAll();
						syndromeService.findAll();
						syndromeService.test(syndrome);
						syndromeService.findAll();
						syndromeService.test(syndrome);
						syndromeService.findAll();
						syndromeService.findAll();
					};
				}.start();
			}
		}, new Date(), 1000);
		
		return "test";
	} 
	
	@RequestMapping(value = "test", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTest(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "wyh");
		map.put("mail", "wyh@163.com");
		
		return map;
	}
}
