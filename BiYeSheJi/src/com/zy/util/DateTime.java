package com.zy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
	
	/**
	 * 当Date型的时间转换成带时间字符串
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public  static String getDateTime(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(now);
	}
	
	
	/**
	 * 当Date型的时间转换成带时间字符串中间没有任何符号
	 * @return yyyyMMddHHmmss
	 */
	public  static String getDateTime1(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(now);
	}
	
	/**
	 * 根据指定格式返回相应字符串
	 * @param format
	 * @return
	 */
	public static String getDateTimeByFormat(String format){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(now);
	}
	
	/**
	 * 获取时间，精确到毫秒
	 * @return
	 */
	public  static String getDateTime2(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(now);
	}
	
	/**
	 * 当Date型的时间转换成日期字符串
	 * @return yyyy-MM-dd
	 */
	public static String getDate(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(now);
	}
	
	/**
	 * 当Date型的时间转换成日期字符串和星期几
	 * @return yyyy-MM-dd
	 */
	public static String getDateWeek(){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
		return sdf.format(now);
	}
	
	/**
	 * 当Date型的时间转换成日期字符串
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String getDate1(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return  sdf.format(date);
	}
	/**
	 * 给定的时间字符串转换成java.util.Date类型
	 * @param date
	 * @return
	 */
	public static Date getString(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return  sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
	/**
	 * 将给的时间字符串转换成数据库类型的date
	 * @param date
	 * @return
	 */
	public static java.sql.Date getString1(String date){
		Date day = getString(date);
		long time = day.getTime();
		java.sql.Date day1 = new java.sql.Date(time);
		return day1;
	}
	/**
	 * 当Date型的时间转换成日期字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  sdf.format(date);
	}
	/**
	 * 获得多次提醒的时间点
	 * @param hour 频率
	 * @param time 次数
	 * @return
	 */
	public static String[] createTime(int hour,int time){
		String s[] = new String[time];
		Date now = new Date();
		for(int i =0; i< time; i ++){
			long time1 = now.getTime()+hour*60*60*1000*(i+1);
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(time1);
			Date now1 = cal.getTime();
			String now2 = getDateTime(now1);
			s[i] = now2;
		}
		return s;
	}
	
	/**
	 * 获得本周的时间段
	 * @return
	 */
	public static String[] getWeek(){
		
		 String [] str = new String[2];
		 Calendar cal = Calendar.getInstance();
		 int day_of_week = cal.get(Calendar.DAY_OF_WEEK)-1;
		 System.out.println("----------"+day_of_week);
		 cal.add(Calendar.DATE, - day_of_week);
		 str[0] = getDate1(cal.getTime());//周一日期
		 cal.add(Calendar.DATE, 6);//周末日期
		 str[1] = getDate1(cal.getTime());
		 return str;
	}
	//判断是不是星期日
	public  static boolean isSunday(){
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		    cal.setTime(now);
		    if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
		 {
		 return true;
		 }
		 return false;
	}
	
	/**
	 * 获得上周的周一和周五的日期
	 * @return
	 */
	public static String[] getWeek3(){
		
		 String [] str = new String[3];
		 Calendar cal = Calendar.getInstance();
		 int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		 cal.add(Calendar.DATE, - day_of_week);
		 cal.add(Calendar.DATE, -1);//周日日期
		 str[2] = getDate1(cal.getTime());//周日期
		 cal.add(Calendar.DATE,-2);//周五日期
		 str[1] = getDate1(cal.getTime());
		 cal.add(Calendar.DATE, -4);//周一
		 str[0] = getDate1(cal.getTime());
		 System.out.println(str[2]);
		 return str;
	}
	
	/**
	 * 获得本周的周一和周五的日期
	 * @return
	 */
	public static String[] getWeek1(){
		
		 String [] str = new String[3];
		 Calendar cal = Calendar.getInstance();
		 int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		 cal.add(Calendar.DATE, - day_of_week);
		 str[0] = getDate1(cal.getTime());//周一日期
		 cal.add(Calendar.DATE, 4);//周五日期
		 str[1] = getDate1(cal.getTime());
		 cal.add(Calendar.DATE, 2);//周日
		 str[2] = getDate1(cal.getTime());
		 System.out.println(str[2]);
		 return str;
	}
	/**
	 * 获得下周的周一和周五的日期
	 * @return
	 */
	public static String[] getWeek2(){
		
		 String [] str = new String[3];
		 Calendar cal = Calendar.getInstance();
		 int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 2;
		 cal.add(Calendar.DATE, - day_of_week);
		// str[0] = getDate1(cal.getTime());//周一日期
		 cal.add(Calendar.DATE, 7);//下周一日期
		 str[0] = getDate1(cal.getTime());
		 cal.add(Calendar.DATE, 4);//周五日期
		 str[1] = getDate1(cal.getTime());
		 cal.add(Calendar.DATE, 2);//周日
		 str[2] = getDate1(cal.getTime());
		 return str;
	}
	/**
	 * 获得当前时间属于一年中的第几周
	 * @return
	 * @create Dec 3, 2009 8:21:10 PM
	 */
	public static String getWeekByYear(){
		 Calendar cal = Calendar.getInstance();
		 int week = cal.get(Calendar.WEEK_OF_YEAR);
		return week+"";
	}
	/**
	 * 获得当前时间属于一年中的第个月
	 * @return
	 */
	public static int getMonthYear(){
		 Calendar cal = Calendar.getInstance();
		 int month = cal.get(Calendar.MONTH);
		return month;
	}
	/**
	 * 获得当前属于哪具年份
	 * @return
	 */
	public static int getYear(){
		 Calendar cal = Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		return year;
	}
	/**
	 * 获得一天中的当前小时
	 * @return
	 * @create Mar 9, 2010 12:02:32 PM
	 */
	public static int getHour(){
		 Calendar cal = Calendar.getInstance();
		 int hour = cal.get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	/**
	 * 传一个小时值，获得当前时间加上传来的值之后的时间字符串
	 * @param hour 小时
	 * @return
	 * @create Mar 22, 2010 1:07:30 PM
	 */
	public static String getTime(int hour){
		Date now = new Date();
		long time1 = now.getTime()+hour*60*60*1000;
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time1);
		Date now1 = cal.getTime();
		String now2 = getDateTime(now1);
		return now2;
	}
	/**
	 * 给定的日期添加一定的天数（字符串）
	 */
	public static String nextDay(Calendar cal,int days){
		
		 cal.add(cal.DAY_OF_MONTH, days);
		 Date date = cal.getTime();
		 String now2 = getDate1(date);
		 return now2;
	}
	
}
