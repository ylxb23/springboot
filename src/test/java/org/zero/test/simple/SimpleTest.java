package org.zero.test.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * simple test cases
 * @author zero
 */
public class SimpleTest {
	
	@Test
	public void testDate() throws ParseException {
		String str = "2017-07-02 00:08:23";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = format.parse(str);
		
		int days = (int)((now.getTime() - System.currentTimeMillis()) / (24*3600000));
		if(days > 0) {
			System.out.printf("%s 距离今天还有 %d天. \n", str, days);
		} else if(days < 0) {
			System.out.printf("%s 距离今天已经过去了 %d天. \n", str, -days);
		} else {
			System.out.printf("%s 不就是今天嘛. \n", str);
		}
	}
	
}
