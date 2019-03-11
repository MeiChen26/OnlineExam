package com.project.utils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author 
 * @createTime 
 * @desc  json与String类型转换通用方法
 * 
 */
@Service
public class JsonConvertUtil {
	/**
	 * 统一JSON转换格式(FastJson)
	 * @param object
	 * @return
	 */
	public static String toFastJsonString(Object object) {
		SerializeConfig config = new SerializeConfig();
		config.put(java.util.Date.class, new FastJsonDateFormatSerializer());
		config.put(java.sql.Date.class, new FastJsonDateFormatSerializer());
		config.put(java.sql.Timestamp.class, new FastJsonDateFormatSerializer());
		config.put(java.lang.Double.class, new FastJsonDoubleFormatSerializer());
//		ValueFilter filter = new ValueFilter() {
//		    @Override
//		    public Object process(Object obj, String s, Object v) {
//		        
//		    return v;
//		    }
//		};
		return com.alibaba.fastjson.JSON.toJSONString(object, config
				, SerializerFeature.WriteMapNullValue
				, SerializerFeature.WriteNullStringAsEmpty 
				, SerializerFeature.WriteNullListAsEmpty
				, SerializerFeature.WriteDateUseDateFormat
				, SerializerFeature.WriteBigDecimalAsPlain);
	}
	
	/**
	 * JSON转对象
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> T fastJsonStringToEntity(String str, Class<T> clazz) {
		
		T object = com.alibaba.fastjson.JSON.parseObject(str, clazz);
		return object;
	}
	
	/**
	 * JSON转list
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> fastJson2EntityList(String str, Class<T> clazz) {
		try{
			List<T> list = com.alibaba.fastjson.JSON.parseArray(str, clazz);
			return list;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getJsonValue(String jstr, String key) {
		try{
			com.alibaba.fastjson.JSONObject jsonObject = (com.alibaba.fastjson.JSONObject)com.alibaba.fastjson.JSON.parse(jstr);
			return StringUtil.toString(jsonObject.get(key));
		}catch (Exception e) {
			// : handle exception
			e.printStackTrace();
			return null;
		}
		
	}

	/**
	 * 转换Map
	 * @param jsonValue
	 * @return
	 */
	public static List<Map<String, Object>> fastJson2MapList(String jsonValue) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		try{
			com.alibaba.fastjson.JSONArray array = com.alibaba.fastjson.JSON.parseArray(jsonValue);
			for(int i = 0; i < array.size(); i++) {
				Map<String, Object> map = convert2MapObject(array.get(i).toString());
				if(map != null) {
					list.add(map);
				}
			}
		}catch (Exception e) {
		}
		return list;
	}

	/**
	 * JSON转Map
	 * @param jsonValue
	 * @return
	 */
	public static Map<String, Object> convert2MapObject(String jsonValue) {
		try{
			Map<String, Object> map = JSON.parseObject(jsonValue, new TypeReference<HashMap<String, Object>>(){});
			return map;
		}catch (Exception e) {

		}

		return null;
	}
}

