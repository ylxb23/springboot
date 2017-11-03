package org.zero.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zero.boot.web.controller.GreetingController;
import org.zero.boot.web.init.App;

/**
 * spring-boot test case basic class.
 * @date 2017年11月3日 下午6:31:49
 * @author zero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value="classpath:/templates")
@AutoConfigureMockMvc
@SpringBootTest(classes=App.class)
public abstract class TestBasic {

	protected MockMvc mvc;
	
	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
	}
}
