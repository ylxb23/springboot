package org.zero.boot.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 使用jdk1.8提供的api创建的时间工具类
 *
 * @author zero
 */
public class DateUtil {
	private DateUtil() {}
	
	public static final String DATE_TIME_PATTERN_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	
	public static final String DATE_TIME_PATTERN_yyyy_MM_dd = "yyyy-MM-dd";
	
	/**
	 * 将时间格式化为 "yyyy-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DATE_TIME_PATTERN_yyyy_MM_dd_HH_mm_ss);
	}
	
	/**
	 * 将时间格式化为字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static String format(Date date, DateTimeFormatter formatter) {
		LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
		return ldt.format(formatter);
	}
	
	/**
	 * 将时间格式化为字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return format(date, DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * 解析时间字符串
	 * @param date
	 * @param formatter
	 * @return
	 */
	public static Date parse(String date, DateTimeFormatter formatter) {
		return Date.from(LocalDateTime.parse(date, formatter).toInstant(ZoneOffset.UTC));
	}
	
	/**
	 * 解析时间字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		return parse(date, DateTimeFormatter.ofPattern(pattern));
	}
	
	public static void main(String[] args) {
		String date = "2017-10-26 11:16:59";
		System.out.println(parse(date, DATE_TIME_PATTERN_yyyy_MM_dd_HH_mm_ss));
		System.out.println(format(new Date(), DATE_TIME_PATTERN_yyyy_MM_dd_HH_mm_ss));
	}
}
