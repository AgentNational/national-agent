package com.pay.national.agent.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtil {


 	static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
 	
 	
 	/**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern)
    {
        String formatDate = null;
        if (pattern != null && pattern.length > 0)
        {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        }
        else
        {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }
 	/**
 	 * 获取前一个月的第一天
 	 * @param date 格式如yyyy-MM-dd
 	 * @return
 	 * @throws ParseException
 	 */
 	public static String getfirstOfPrevMonth(String date) throws ParseException{
 		Date date2 = sdf.parse(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		calendar.add(Calendar.MONTH, -1);//获取上个月
		calendar.set(Calendar.DAY_OF_MONTH, 1);//获取月份第一天
		
		return sdf.format(calendar.getTime());
 	}
 	
 	/**
 	 * 获取当天
 	 * @return
 	 */
 	public static String getToday()
 	{
 		return sdf.format(new Date());
 	}
 	
 	/**
    *
    * 生成与某一天相差特定天数的日期
    *
    * @param date 如：2015-05-01
    * @param pattern 如：YYYY-MM-DD
    * @param days 0 为当天
    * @return
    * @see [类、类#方法、类#成员]
    */
   public static String getFixedDays(String date, String pattern, Integer days)
   {
       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
       Date d = new Date();
       try
       {
           d = simpleDateFormat.parse(date);
       }
       catch (ParseException e)
       {
           System.out.println("日期格式不正确");
           e.printStackTrace();
           return null;
       }
       Calendar day = Calendar.getInstance();
       day.setTime(d);
       day.add(Calendar.DATE, days);
       String nextDay = simpleDateFormat.format(day.getTime());
       return nextDay;
   }
 	
     /**
      * 计算两个日期的日差
      * @param smdate
      * @param bdate
      * @return
      * @throws Exception
      * @author ji.li
      */
	 public static int daysBetween(String smdate,String bdate) throws Exception
	    {
	        Date newSmdate=sdf.parse(smdate);
	        Date newBdate=sdf.parse(bdate);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(newSmdate);
	        long time1 = cal.getTimeInMillis();
	        cal.setTime(newBdate);
	        long time2 = cal.getTimeInMillis();
	        long between_days=(time2-time1)/(1000*3600*24);

	       return Math.abs(Integer.parseInt(String.valueOf(between_days)));
	    }
	 /**
	  * 迭代出两个时期间的所有日期
	  * @param smdate
	  * @param bdate
	  * @return
	  * @throws Exception
	  * @author ji.li
	  */
	 public static String[] iteratorDate(String smdate,String bdate) throws Exception{
		 int daysBetween = daysBetween(smdate,bdate);
		 String[] dates = new String[daysBetween+1];
		 dates[0] =smdate;
		 for (int i = 1; i <= daysBetween; i++) {
			 dates[i]=operDate(smdate,(i));
		}
		 return dates;
	 }
	 /**
	  * 按照DAY加减日期
	  * @param date
	  * @param oper
	  * @return
	  * @throws java.text.ParseException
	  * @author ji.li
	  */
	 public static String operDate(String date,int oper) throws java.text.ParseException{
	   Calendar calendar=Calendar.getInstance();
	   calendar.setTime(sdf.parse(date));
	   calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+oper);//让日期加1
	   return sdf.format(calendar.getTime());
	 }
	 
	 /**
	  * 按照DAY加减日期（在当月内操作，最小日期为当月第一天）
	  * @param date
	  * @param oper
	  * @return
	  * @throws java.text.ParseException
	  * @author chao.chen
	  */
	 public static String operDateInMonth(String date,int oper) throws java.text.ParseException{
	   Calendar calendar=Calendar.getInstance();
	   calendar.setTime(sdf.parse(date));
	   calendar.set(Calendar.DAY_OF_MONTH,
				   (
				      1==calendar.get(Calendar.DAY_OF_MONTH)?
				      1 : 
					  calendar.get(Calendar.DAY_OF_MONTH)+oper
				   )
			   );
	   return sdf.format(calendar.getTime());
	 }



	   /**
	     * 当月第一天
	     * @return
	     * @throws ParseException
	     */
	    public static String getFirstDay(String date) throws ParseException {
	        Date theDate = sdf.parse(date);
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        String day_first = sdf.format(gcLast.getTime());
	        return day_first;
	    }
	    

	   /**
	     * 当月最后一天
	     * @return
	     * @throws ParseException
	     */
	    public static String getLastDay(String date) throws ParseException {
	        Date theDate = sdf.parse(date);
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.set(Calendar.DAY_OF_MONTH, gcLast.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	        String day_first = sdf.format(gcLast.getTime());
	        return day_first;
	    }
	    
	    
	    /**
	     * 判断指定日期是当月第几天
	     * @return
	     * @throws ParseException
	     */
	    public static int getDayOfMonth(String date) throws ParseException {
	        Date theDate = sdf.parse(date);
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        return gcLast.get(Calendar.DAY_OF_MONTH);
	    }
	    
	    
	    /** 
	     * 指定日期相差月份第一天
	     * @Description  一句话描述方法用法
	     * @param date
	     * @param addMonth
	     * @return
	     * @throws ParseException
	     * @see 需要参考的类或方法
	     */
	    public static String getMonthFirstDay(String date,int addMonth) throws ParseException {
	        Date theDate = sdf.parse(date);
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.add(Calendar.MONTH, addMonth);
	        gcLast.set(Calendar.DAY_OF_MONTH, 1);
	        String day_first = sdf.format(gcLast.getTime());
	        return day_first;
	    }
	    
	    /** 
	     * 指定日期相差月份最后一天
	     * @Description  一句话描述方法用法
	     * @param date
	     * @param addMonth
	     * @return
	     * @throws ParseException
	     * @see
	     */
	    public static String getMonthLastDay(String date,int addMonth) throws ParseException {
	        Date theDate = sdf.parse(date);
	        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
	        gcLast.setTime(theDate);
	        gcLast.add(Calendar.MONTH, addMonth);
	        gcLast.set(Calendar.DAY_OF_MONTH, gcLast.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	        String day_first = sdf.format(gcLast.getTime());
	        return day_first;
	    }
	/**
	 * 把日期字符串按照对应格式转换成日期类型
	 *
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date parseStrToDate(String str, String format) {
		if (str == null || format == null) {
			return null;
		}
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
	 * 相差月份的第一天
	 * @return
	 */
	public static Date getFirstDay(Date date,int month){
		GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}


	/**
	 * 相差月份的最后一天
	 * @return
	 */
	public static Date getLastDay(Date date,int month){
		GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH,month);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}


	public static Date addDay(Date date,int addDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,addDay);
        return calendar.getTime();

    }
}
