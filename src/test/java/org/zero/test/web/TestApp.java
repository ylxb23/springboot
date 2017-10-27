package org.zero.test.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zero.boot.web.controller.GreetingController;

/**
 * 
 * @date 2017年10月27日 下午2:04:13
 * @author zero
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc
public class TestApp {
	private Logger logger = LoggerFactory.getLogger(TestApp.class);
	
	private MockMvc mvc;
	
	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(new GreetingController()).build();
	}
	
	@Test
	public void testCount() {
		int times = 10;
		while(times-- > 0) {
			try {
				MvcResult result = mvc.perform(get("/count").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();
				String resultstring = result.getResponse().getContentAsString();
				logger.debug("get: /count result: {}", resultstring);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
