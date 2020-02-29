package com.project.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarUtil {

	public static List<List<List<Map<String, Object>>>> chuli(String dates) throws Exception {
		List<List<List<Map<String, Object>>>> list = new ArrayList<>();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(dates);	//将字符串转化为指定的日期格式
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);	//将日期转化为日历

		int maxDay = calendar.getActualMaximum(Calendar.DATE);	//当前日期中当前月对应的最大天数

		int currentDay = calendar.get(Calendar.DATE);	//当前日期中的当前天
		calendar.set(Calendar.DATE, 1); // 设置为当前月的第一天

		int firstDay = calendar.get(Calendar.DAY_OF_WEEK);	//当前日期中当前月第一天对应的星期数
		
		DateFormat format1 = new SimpleDateFormat("yyyy-MM");
		Date date1 = format1.parse(dates);
		String yymm = format1.format(date1);
		
		List<List<Map<String, Object>>> listl = new ArrayList<>(); //一行
		List<Map<String, Object>> listm = null; //一个对象
		Map<String, Object> map = null;	//属性
		for (int j = 1; j < firstDay; j++) {
			//System.out.print("\t");
			listm = new ArrayList<>(); //一个对象
			map = new HashMap<>();	//属性
			map.put("date", null);
			map.put("r", null);
			map.put("current", false);
			listm.add(map);
			listl.add(listm);
		}
 
		for (int i = 1; i <= maxDay; i++) {
			if (i == currentDay) {
				listm = new ArrayList<>(); //一个对象
				map = new HashMap<>();	//属性
				map.put("date", yymm+"-"+String.format("%02d", i));
				map.put("r", i);
				map.put("current", true);
				listm.add(map);
				listl.add(listm);
			} else {
				listm = new ArrayList<>(); //一个对象
				map = new HashMap<>();	//属性
				map.put("date", yymm+"-"+String.format("%02d", i));
				map.put("r", i);
				map.put("current", false);
				listm.add(map);
				listl.add(listm);
			}
			if ((i - (8 - firstDay)) % 7 == 0) { //换行
				List<List<Map<String, Object>>> listb = new ArrayList<>();
				listb.addAll(listl);
				list.add(listb);
				listl = new ArrayList<>();
			} else if(i == maxDay) {
				List<List<Map<String, Object>>> listb = new ArrayList<>();
				listb.addAll(listl);
				list.add(listb);
				listl = new ArrayList<>();
			}
		}
		
		return list;
	}
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		String currnetD = sdf.format(cal.getTime());//当前日期
		cal.add(Calendar.MONTH, -1);
		String pevD = sdf.format(cal.getTime());//上月
		cal.add(Calendar.MONTH, 2);
		String nextD = sdf.format(cal.getTime()); //下月
		System.out.println(currnetD+"|"+pevD+"|"+nextD);
		List<List<List<Map<String, Object>>>> list = chuli(pevD);
		for(List<List<Map<String, Object>>> o : list) {//全部
			for(List<Map<String, Object>> o1: o) { //每行
				for(Map<String, Object> m : o1) { //属性
					System.out.print(m.get("date")+"|"+m.get("current")+"|"+m.get("r"));
				}
				System.out.print("\t");
			}
			System.out.println("\n");
		}

	}
}
