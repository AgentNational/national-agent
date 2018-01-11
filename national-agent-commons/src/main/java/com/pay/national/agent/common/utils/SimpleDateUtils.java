package com.pay.national.agent.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
/**
 * @Description: 格式化时间工具类
 * @see: 需要参考的类
 * @version 2017年9月4日 下午5:28:45
 * @author zhenhui.liu
 */
public class SimpleDateUtils {

	public final static String[] PATTERN = new String[]{"yyyy-MM","yyyyMM","yyyy/MM",
            "yyyyMMdd","yyyy-MM-dd","yyyy/MM/dd",
            "yyyyMMddHHmmss",
                        "yyyy-MM-dd HH:mm:ss",
                        "yyyy-MM-dd HH:mm:ss Z",
                        "yyyy/MM/dd HH:mm:ss"};
	public static Date parseDate(String date){
		try {
			return DateUtils.parseDate(date, PATTERN);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String formatDate(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String formatWithTime(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static void main(String[] args) {
		System.out.println(formatWithTime(new Date()));
	}

}
