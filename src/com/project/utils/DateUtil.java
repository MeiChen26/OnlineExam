/**
 * Copyright(C) 2010-2012 ZTE-ITS WuXi LTD. All Rights Reserved.
 * 
 * 中兴智能交通股份有限公司版权所有2010-2012
 *
 * DateUtil.java
 *
 * ver    变更日                  部门                           编写人                变更内容
 * --------------------------------------------------------------
 * V1.0   2015-7-28   基础平台组          zhaoxq      初版
 */
package com.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具包
 * 
 * @author
 */
public class DateUtil {

    /**
     * 获取日期<br />
     * 根据输入格式，返回日期
     * 
     * @param  date 转换日期
     * @param  dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm:ss
     * @return 日期字符串
     */
    public static String getDateToStr(Date date, String dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if(dateFormatStr != null && dateFormatStr.length() > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr);
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        return dateFormat.format(date);
    }

    /**
     * 获取日期<br />
     * 根据输入格式，返回日期
     * 
     * @param  date 转换日期
     * @return 日期字符串
     */
    public static String getDateToStr(Date date) {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
    
    /**
     * 获取日期<br />
     * 根据输入格式，返回日期
     * 
     * @param  date 转换日期
     * @return 日期字符串
     */
    public static String getDateToStrCode(Date date) {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return dateFormat.format(date);
    }
    
    
    /**
     * 获取日期<br />
     * 根据输入格式，返回日期
     * 
     * @param  date 转换日期
     * @return 日期字符串
     */
    public static String getDateToStrSec(Date date) {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }
    
    
    /**
     * 获取日期<br />
     * 根据输入格式，返回日期
     * 
     * @param  date 转换日期
     * @return 日期字符串
     */
    public static String getDateToStuCode(Date date) {
        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(date);
    }
    
    public static Date getNextMinute(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.MINUTE, 45);  
        date = calendar.getTime();  
        return date;  
    }
    
    /**
     * 
     * <p>Title: differentDaysByMillisecond</p>
     * <p>Description: 判断两个时间的间隔</p>
     * @param date1
     * @param date2
     * @return int
     */
    public static int differentDaysByMillisecond(Date date1,Date date2) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String dates1 = sdf.format(date1);
    	String dates2 = sdf.format(date2);
    	Date d2 = null;
		Date d1 = null;
		int days = 0;
		try {
			d2 = sdf.parse(dates2);
			d1 = sdf.parse(dates1);
			days = (int) ((d2.getTime() - d1.getTime()) / (1000*3600*24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
    }
    
    public static void main(String[] args) {
    	System.out.println(getDateToStrCode(getNextMinute(new Date())));
	}
}
