package test.service;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = "classpath:config/applicationContext.xml")
//@ActiveProfiles("development")
public abstract class SpringTestSupport {
	
	public Logger log = Logger.getLogger(SpringTestSupport.class);
}
