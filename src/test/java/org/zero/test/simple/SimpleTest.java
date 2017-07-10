package org.zero.test.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleTest {
	
	public static void main(String[] args) throws ParseException {
		String str = "2017-07-02 00:08:23";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date now = format.parse(str);
		
		System.out.println(now);
		System.out.println(now.getTime());
		
		int days = (int)((now.getTime() - System.currentTimeMillis()) / (24*3600000));
		System.out.println(days);
	}
	
}
