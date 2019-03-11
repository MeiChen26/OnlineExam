package com.project.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

import sun.misc.BASE64Encoder;

import java.util.TreeMap;

import org.springframework.util.Base64Utils;

import com.project.exam.common.Configuration;

/**
 * @author fei.gao
 * sig 签名计算算法
 * 
 */
public class ClientMD5SignMake {


	
	
	public static void main(String[] args) {
		/*Map<String,String> treeMap = new TreeMap<String, String>();
		treeMap.put("clsbdh", "001");
		treeMap.put("fdjh", "002");
		treeMap.put("hphm", "鲁Q12345");
		System.out.println(packParams(treeMap));*/
		/*System.out.println(getMD5(getSha1(getSha1(getMD5("1_15563236586")))+"1"));
		System.out.println(Base64Encoder("1$15563236586$ac5428630818e5a53e2273274afec644"));*/
		
		//System.out.println(auth_key("1", "15563236586"));
		String s = "";
		try {
			s = URLEncoder.encode("测试", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Base64.getEncoder().encodeToString(s.getBytes()));
		Map<String,String> treeMap = new TreeMap<String, String>();
		treeMap.put("payway", "1");
		treeMap.put("body", Base64.getEncoder().encodeToString(s.getBytes()));
		treeMap.put("passback_params", "ds_alipay");
		treeMap.put("out_trade_no", "123456789");
		treeMap.put("total_fee", "1");
		treeMap.put("seller_code", "2");
		treeMap.put("timeout_express", "");
		
		System.out.println(makeMD5Sign(treeMap, "303116c9d711462354fa4be5784a479aa"));
		//请求接口地址
//		public static String URL = "http://yuntuapi.amap.com/${interface}?";

		//用户KEY
//		String user_key = "f3f76a0914c5495d2b1e5b0aff4c46e7";
		
		//用户私钥
//		String user_md5_secretcode = "303116c9d711461e5fa4be5784a479aa";
		//制造请求参数  ，计算签名跟参数前后顺序(用TreeMap)有关
//		Map<String,String> pmapParms = new TreeMap<String, String>();
//		pmapParms.put("type", "iOS");
//		
//		String sig = packParams(pmapParms);
//		System.out.println(sig);
//		makeMD5Sign(pmapParms,user_md5_secretcode);
		
//		String validCode=getMd5StandardString("你好");
//		System.out.println(validCode);
//		try {
//			System.out.println(URLEncoder.encode("你好","UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			//  Auto-generated catch block
//			e.printStackTrace();
//		}
		//用户私钥
//				String user_md5_secretcode = "303116c9d711461e5fa4be5784a479aa";
////				制造请求参数  ，计算签名跟参数前后顺序(用TreeMap)有关
////				sfzmhm=372824197406154352&sjwybs=000000000000000&sig=457b1c80d946222a99c7d310a00e1d65&dabh=384198&xm=刘祥明
//				Map<String,String> pmapParms = new TreeMap<String, String>();
//				pmapParms.put("sfzmhm", "372824197406154352");
//				pmapParms.put("sjwybs", "000000000000000");
//				pmapParms.put("dabh", "dabh");
//				pmapParms.put("xm", "刘祥明");
//				pmapParms.put("type", "0");
//				String sig = packParams(pmapParms);
//				System.out.println(sig);
				
		
		//System.out.println(URLDecoder.decode("%E7%9F%B3%E5%B3%B0%E5%85%A8%E8%B4%A3"));
	}
	
	public static String packParams(Map map){
		return makeMD5Sign(map,Configuration.get("secretKey"));
	}
	
	/**
	 * 生成MD5签名
	 */
	public static String makeMD5Sign(Map<String,String> map,String user_md5_secretcode){
		
		/**
		 * 拼接原始请求串
		 * city=CHINA&enc=utf-8&filter=Province:53174EAC+PostCode:116031&key=${key}&keywords=酒店&limit=100&output=json&tableid=55adb0c7e4b0a76fce4c8dd6
		 */
		String oldParamsStr = toOldQueryString(map);
		if(oldParamsStr.contains("*")){
			oldParamsStr = oldParamsStr.replace("*", "%2A");
		}
//		System.out.println(oldParamsStr);
		
		/**
		 * MD5(用按照请求参数排过顺序的请求串+用户私钥)生成签名 sig
		 * sig = 3dbe13640b0fdb1d057ed5dd671362bf
		 */
		String signStr = getMd5StandardString(oldParamsStr+user_md5_secretcode);
		
//		System.out.println(signStr);
		
		/**
		 * 调用下面的toEncodeQueryString方法，对TreeMap内所有value作utf8编码，拼接请求串
		 * 
		 * city=CHINA&enc=utf-8&filter=Province%3A53174EAC%2BPostCode%3A116031&key=%24%7Bkey%7D&keywords=%E9%85%92%E5%BA%97&limit=100&output=json&tableid=55adb0c7e4b0a76fce4c8dd6
		 */
		StringBuffer paramsStr = new StringBuffer(toEncodeQueryString(map));
		
//		System.out.println(paramsStr);
		
		//拼接签名参数
		paramsStr.append("&sig="+signStr);
		
		/**
		 * 最终发起的请求串
		 * http://yuntuapi.amap.com/${interface}?city=CHINA&enc=utf-8&filter=Province%3A53174EAC%2BPostCode%3A116031&key=%24%7Bkey%7D&keywords=%E9%85%92%E5%BA%97&limit=100&output=json&tableid=55adb0c7e4b0a76fce4c8dd6&sig=c926cd113c5bd7bc33291ed7c8ae5425
		 */
		String wholeStr = paramsStr.toString();
		
//		System.out.println(wholeStr);
		return signStr; 
	
	}
	
	/**
	 * 对Map内所有value，拼接返回结果
	 * @param data
	 * @return
	 */
    public static String toOldQueryString(Map<?, ?> data) {
    	
    	StringBuffer queryString = new StringBuffer();
    	
		try {
			for (Entry<?, ?> pair : data.entrySet()) {
				queryString.append(pair.getKey() + "=");
				queryString.append(URLEncoder.encode((String)pair.getValue(),"UTF-8")+"&");
//				queryString.append((String)pair.getValue()+"&");
			}
			if (queryString.length() > 0) {
				queryString.deleteCharAt(queryString.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryString.toString();
    }
    
	/**
	 * 对Map内所有value作utf8编码，拼接返回结果
	 * @param data
	 * @return
	 */
    public static String toEncodeQueryString(Map<?, ?> data) {
    	
    	StringBuffer queryString = new StringBuffer();
    	
		try {
			for (Entry<?, ?> pair : data.entrySet()) {
				queryString.append(pair.getKey() + "=");
				queryString.append(URLEncoder.encode((String) pair.getValue(),
						"UTF-8") + "&");
//				queryString.append(pair.getValue() + "&");
			}
			if (queryString.length() > 0) {
				queryString.deleteCharAt(queryString.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryString.toString();
    }
    
    /**
     * MD5算法
     * @param source
     * @return
     */
    public static String getMd5StandardString(String source){
    	MessageDigest md = null;
    	try {
    		md = MessageDigest.getInstance("MD5");
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	}
    	try {
    		//不写，获取系统编码 现统一编码为UTF-8
    		md.update(source.getBytes("UTF-8"));
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
    	byte b[] = md.digest();
    	System.out.println(md.toString());
    	return standardBytes2HexString(b);
    }
    
    public static String standardBytes2HexString(byte[] b) {
    	StringBuilder ret = new StringBuilder();
    	for (int i = 0; i < b.length; i++) {
    		String hex = Integer.toHexString(b[i] & 0xFF);
    		if (hex.length() == 1) {
    			hex = '0' + hex;
    		}
    		ret.append(hex);
    	}
    	return ret.toString();
    }
    
    /** 
     * 对字符串md5加密(小写+字母) 
     * 
     * @param str 传入要加密的字符串 
     * @return  MD5加密后的字符串 
     */  
    public static String getMD5(String str) {  
        try {  
            // 生成一个MD5加密计算摘要  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            // 计算md5函数  
            md.update(str.getBytes());  
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符  
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值  
            return new BigInteger(1, md.digest()).toString(16);  
        } catch (Exception e) {  
           e.printStackTrace();  
           return null;  
        }  
    }
    
    /**
     * 
     * <p>Title: getSha1</p>
     * <p>Description: sha1加密</p>
     * @param str
     * @return String
     */
    public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 
     * <p>Title: Base64Encoder</p>
     * <p>Description: base64加密</p>
     * @param str
     * @return String
     */
    public static String Base64Encoder(String str) {  
        BASE64Encoder encoder = new BASE64Encoder();  
        String encode = encoder.encode(str.getBytes());  
        return encode;  
    }
    
    /**
     * <p>Title: auth_key</p>
     * <p>Description: </p>
     * @param uid
     * @param phone
     * @return String
     */
    public static String auth_key(String uid, String phone) {
    	String token = getMD5(getSha1(getSha1(getMD5(uid+"_"+phone)))+uid);
    	return Base64Encoder(uid+"$"+phone+"$"+token);
    }

}
