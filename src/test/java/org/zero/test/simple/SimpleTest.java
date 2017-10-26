package org.zero.test.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * simple test cases
 * @author zero
 */
public class SimpleTest {
	
	public static void main(String[] args) throws ParseException {
		testDate("2017-07-02 00:08:23");
	}
	
	private static void testDate(String str) throws ParseException {
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
