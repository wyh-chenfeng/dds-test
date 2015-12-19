package test.service.syndrome;

import java.util.List;

import model.mybatis.Syndrome;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import service.syndrome.SyndromeService;
import test.service.SpringTestSupport;

import com.google.gson.Gson;

public class TestSyndromeService extends SpringTestSupport {

	@Autowired
	private SyndromeService syndromeService;
	
	@Test
	@Ignore
	public void testFindSyndromeInitData() {
//		List<Map<String, Object>>  list = syndromeService.findSyndromeInitData();
		List<Syndrome> list2 = syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		Gson gson = new Gson();
		log.debug("==============================");
//		log.debug(gson.toJson(list));
		log.debug(gson.toJson(list2));
		log.debug("==============================");
	}
	
	
	@Test
//	@Ignore
	public void testCreate() {
		Syndrome syndrome = new Syndrome();
		
		syndrome.setDescription("描述");
		syndrome.setSymptomName("名字");
		syndrome.setSyndromeElementEnd("A");
		syndrome.setSyndromeElementStart("B"); 
		syndrome.setSymptomCategory("symptomCategory");
		
		syndromeService.test(syndrome);
		syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		syndromeService.findAll();
		
		Gson gson = new Gson();
		log.debug("==============================");
		log.debug(gson.toJson(syndrome.getId()));
		log.debug("==============================");
	}
	
	
}
