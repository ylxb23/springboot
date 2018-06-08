package org.zero.test.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.zero.test.TestBasic;

/**
 * mvc mock test
 * @date 2017年10月27日 下午2:04:13
 * @author zero
 */
public class TestApp extends TestBasic {
	
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
				Assert.assertTrue(resultstring.contains("Hello"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
