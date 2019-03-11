package com.project.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * <p>ClassName: EncryptUtil</p>
 * <p>Description: 加密工具类</p>
 * @Author 
 * @Date: 
 */
public class EncryptUtil {
	
	public static final SecureRandom random = new SecureRandom();
	
	public static void main(String[] args){
		/*long currentTimeMillis = System.currentTimeMillis();
		String password = "123456";
		System.out.println("OriginPassword: " + password);
		String entryptPassword = entryptPassword(password);
		System.out.println("used [" + (System.currentTimeMillis() - currentTimeMillis) + "ms]");
		System.out.println("entryptPassword: " + entryptPassword);
		System.out.println("validPassword: result - " + validatePassword(password, entryptPassword));
		System.out.println("used [" + (System.currentTimeMillis() - currentTimeMillis) + "ms]");*/
		
		String mapToString = "address=%E5%91%83%E5%91%83%E5%91%83&idcard=371329199407112712&itemcode=114&mobilephone=15964879376&realname=%E4%B9%9F%E5%BE%97&schoolcode=2&sex=0&signaccount=65563303116c9d711462354fa4be5784a479aa";
		String generateSign = EncryptUtil.string2MD5(mapToString + "303116c9d711462354fa4be5784a479aa");
		System.out.println(generateSign);
	}
	
	
	/**
	 * @Title: getJSAPISign
	 * @Description: <p>JS-SDK使用权限签名算法</p>
	 * <p>签名生成规则如下：参与签名的字段包括noncestr（随机字符串）, 有效的jsapi_ticket, timestamp（时间戳）, 
	 * url（当前网页的URL，不包含#及其后面部分） 。对所有待签名参数按照字段名的ASCII 码从小到大排序（字典序）后，
	 * 使用URL键值对的格式 （即 key1=value1&key2=value2…）拼接成字符串string1。这里需要注意的是所有参数名均为小写字符。
	 * 对string1作sha1加密，字段名和字段值都采用原始值，不进行URL 转义。</p>
	 * <p></p>
	 * @param params
	 * @return String
	 * @throws
	 */
	public static String getJSAPISign(Map<String, String> params){
		String[] keyValueStrs = new String[params.size()];
		Iterator<Entry<String, String>> iterator = params.entrySet().iterator();
		for(int i = 0; iterator.hasNext(); i++){
			Entry<String, String> temp = iterator.next();
			keyValueStrs[i] = temp.getKey() + "=" + temp.getValue();
		}
		Arrays.sort(keyValueStrs, String.CASE_INSENSITIVE_ORDER);
		String content = StringUtils.join(keyValueStrs, "&");
		System.out.println(content);
		byte[] resultBytes = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			resultBytes = digest.digest(content.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		return Hex.encodeHexString(resultBytes);
	}
	/**
	 * @Title: makeSHA1Sign
	 * @Description: 将字符串按照字典顺序拼接并使用SHA1加密
	 * @param strs 待处理的字符串
	 * @return String
	 * @throws
	 */
	public static String SHA1Encrypt(String... strs) {
		if (strs == null || strs.length == 0 || StringUtils.isAnyBlank(strs)) {
			return null;
		}
		
		Arrays.sort(strs, String.CASE_INSENSITIVE_ORDER);
		StringBuilder content = new StringBuilder();
		for(String temp : strs){
			content.append(temp);
		}
		
		byte[] resultBytes = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			resultBytes = digest.digest(content.toString().getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}

		return Hex.encodeHexString(resultBytes);
	}
	
	
	/**
	 * @Title: phpEntryptPassword
	 * @Description: PHP crypt 方法 生成安全的密码的 java实现
	 * @param plainPassword 明文密码
	 * @return String
	 */
	public static String phpEntryptPassword(String plainPassword) {
		if(plainPassword == null){
			return null;
		}

		return Md5Crypt.md5Crypt(plainPassword.getBytes(), null);
	}
	/**
	 * @Title: phpValidatePassword
	 * @Description: PHP crypt 方法 验证安全的密码的 java实现
	 * @param plainPassword 明文密码
	 * @param localPassword 密文（库中存储）密码
	 * @return boolean 两个参数均为空时 返回true，验证一致时 返回true，
	 */
	public static boolean phpValidatePassword(String plainPassword, String localPassword) {
		if(plainPassword == null && localPassword == null){
			return true;
		}
		if(StringUtils.isAnyBlank(plainPassword, localPassword)){
			return false;
		}
		String validPwd = Md5Crypt.md5Crypt(plainPassword.getBytes(), localPassword);
		return validPwd.equals(localPassword);
	}
	
	/**
	 * @Title: entryptPassword
	 * @Description: 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash 
	 * @param plainPassword 明文密码
	 * @return String
	 */
	public static String entryptPassword(String plainPassword) {
		byte[] salt = generateSalt(8);
		byte[] hashPassword = null;
		try {
			hashPassword = SHA1(plainPassword.getBytes(), salt, 1024);
			return Hex.encodeHexString(salt) + Hex.encodeHexString(hashPassword);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 验证密码
	 * @param plainPassword 明文密码
	 * @param localPassword 密文密码
	 * @return 验证成功返回true
	 */
	public static boolean validatePassword(String plainPassword, String localPassword) {
		byte[] salt = decodeHex(localPassword.substring(0,16));
		byte[] hashPassword = null;
		try {
			hashPassword = SHA1(plainPassword.getBytes(), salt, 1024);
			return localPassword.equals(Hex.encodeHexString(salt) + Hex.encodeHexString(hashPassword));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Hex解码.
	 */
	private static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 生成随机的Byte[]作为salt.
	 * @param numBytes byte数组的大小
	 */
	private static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}
	
	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 * @throws NoSuchAlgorithmException 
	 */
	private static byte[] SHA1(byte[] input, byte[] salt, int iterations) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		
		if (salt != null) {
			digest.update(salt);
		}
		
		byte[] result = digest.digest(input);
		
		for (int i = 1; i < iterations; i++) {
			digest.reset();
			result = digest.digest(result);
		}
		return result;
	}
	/**
	 * @Title: mapToString
	 * @Description: 将Map转换成 请求url参数格式  如：a=1&b=1&d=1
	 * 				其中参数的值是经过URLEncoder.encode(value, "utf-8") 方法编码之后
	 * 				注意：该方法仅限与微信端验证时使用，因为正常的编码逻辑是用源码的值进行签名。
	 * @param params
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public static String mapToString(Map<String, String> params){
		Set<Entry<String,String>> entrySet = params.entrySet();
		StringBuffer sb = new StringBuffer();
		for(Entry<String, String> entry : entrySet){
			sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		String result = sb.toString();
		if(result != null && result.length()>0){
			//取出尾部多余的 &
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
//            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = (md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
  
    /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
  
    }


	public static String getTimestamp(){
		return String.valueOf(System.currentTimeMillis()/1000);
	}


	//获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
	public static String getNonceString(int length) {
	    //随机字符串的随机字符库
	    String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuffer sb = new StringBuffer();
	    int len = KeyString.length();
	    for (int i = 0; i < length; i++) {
	       sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
	    }
	    return sb.toString();
	}
  
}
