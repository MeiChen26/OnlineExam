package com.project.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import net.sf.json.JSONObject;

/** 
 * @Title: util
 * @Description: HttpGet和HttpPost请求调用util类
 * @author 
 * @date 
 * @version V1.0 
 *
 */
public class HttpUtil {
	private static final Logger logger = Logger.getLogger(HttpUtil.class);
	/**
	 * HttpPost方式请求API并返回json数据
	 * @param uri
	 * @param mapParams
	 * @return
	 */
	public static JSONObject httpPostReturnJson(String uri, Map<String, String> mapParams, int timeout, Map<String, String> headers) {
		
		//创建返回json对象
		JSONObject jsonStr = new JSONObject();
		//参数设定
		List <NameValuePair> params = new ArrayList<NameValuePair>();
		if (mapParams!=null && mapParams.size()>0){
			for(Entry<String, String> entry : mapParams.entrySet()) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		//创建HttpPost对象
		HttpPost httpPost = new HttpPost(uri);
		//设置请求和传输超时时间
		if(timeout == 0) {
			timeout = 90000;
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();

		httpPost.setConfig(requestConfig);

		//创建CloseableHttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建CloseableHttpResponse对象
		CloseableHttpResponse httpResponse = null;
		
		//获取userId
		String userId = "";
	    RequestAttributes requestAttributes = null;
	    try{
	    	requestAttributes = RequestContextHolder.currentRequestAttributes();
	    }catch (Exception e) {
			e.printStackTrace();
		}
	    
	    if (requestAttributes != null) {
	    	HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
	    }
	    
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params,"utf-8"));
			if(!userId.isEmpty()){
				httpPost.setHeader("Authorization", userId);
			}
			
			//设置请求Header
			if(headers != null) {
				Iterator<String> it = headers.keySet().iterator();
				while (it.hasNext()) {
					String key = it.next();
					httpPost.setHeader(key, headers.get(key));
				}
			}
			
			httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String result = EntityUtils.toString(httpEntity);//取出应答字符串
				jsonStr = JSONObject.fromObject(result);
				EntityUtils.consume(httpEntity);
			}
			httpClient.close();
			httpResponse.close();
			httpPost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				httpClient.close();
				httpResponse.close();
				httpPost.releaseConnection();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return jsonStr;
	}
	public static JSONObject httpPostReturnJson(String uri, String json) {
		//创建返回json对象
		JSONObject jsonStr = new JSONObject();
		StringEntity entity = new StringEntity(json, "UTF-8");
		//创建HttpPost对象
		HttpPost httpPost = new HttpPost(uri);
		//设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();

		httpPost.setConfig(requestConfig);

		//创建CloseableHttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建CloseableHttpResponse对象
		CloseableHttpResponse httpResponse = null;
		if(uri.indexOf("getIpInfo")!=-1){
			httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36");
		}
		try {
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String result = EntityUtils.toString(httpEntity);//取出应答字符串
				jsonStr = JSONObject.fromObject(result);
				EntityUtils.consume(httpEntity);
			}
			httpClient.close();
			httpResponse.close();
			httpPost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				httpClient.close();
				httpResponse.close();
				httpPost.releaseConnection();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return jsonStr;
	}
	/**
	 * HttpGet方式请求API并返回json数据
	 * @param uri
	 * @param mapParams 参数可以和uri一体，也可以单独设定，切勿一部分在uri上，一部分在mapParams上
	 * @return
	 */
	public static JSONObject httpGetReturnJson(String uri, Map<String, String> mapParams) {

		//创建返回json对象
		JSONObject jsonStr = new JSONObject();
		//参数设定
		if (mapParams!=null && mapParams.size()>0){
			if (!uri.endsWith("?")){
				uri = uri + "?";
			}
			for(Entry<String, String> entry : mapParams.entrySet()) {
				if (uri.endsWith("?")){
					uri = uri + entry.getKey() + "=" + entry.getValue(); 
				}else{
					uri = uri + "&" + entry.getKey() + "=" + entry.getValue(); 
				}
			} 
		}
		//创建HttpGet对象
		HttpGet httpGet = new HttpGet(uri);
		//设置请求和传输超时时间(10s)
		//HttpHost httpHost = new HttpHost("127.0.0.1", 8787);
		//HttpHost httpHost = new HttpHost("127.0.0.1", 443);
		//RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setSocketTimeout(60000).setConnectTimeout(60000).build();
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
		httpGet.setConfig(requestConfig);
		//创建CloseableHttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建CloseableHttpResponse对象
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = httpClient.execute(httpGet);
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String result = EntityUtils.toString(httpEntity);//取出应答字符串
				jsonStr = JSONObject.fromObject(result);
				EntityUtils.consume(httpEntity);
			}
			httpClient.close();
			httpResponse.close();
			httpGet.releaseConnection();
		} catch (Exception e) {
			//  Auto-generated catch block
			e.printStackTrace();
			try {
				httpClient.close();
				httpResponse.close();
				httpGet.releaseConnection();
			} catch (Exception e1) {
				//  Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return jsonStr;
	}
	
	/**
	 * 内部API请求调用
	 * @param requestKey
	 * @param mapParams
	 * @return
	 */
	public static JSONObject internalHttpPostRequest(String requestKey, Map<String, String> mapParams){
		return HttpUtil.internalHttpPostRequest(requestKey, mapParams, true);
	}
	public static JSONObject internalHttpObjectPostRequest(String requestKey, Map<String, Object> mapParams){
		return null;
	}
	/**
	 * 内部API请求调用
	 * @param requestKey
	 * @param mapParams
	 * @return
	 */
	public static JSONObject internalHttpPostRequestSucess(String requestKey, Map<String, String> mapParams){
		JSONObject jsonStr =  HttpUtil.internalHttpPostRequest(requestKey, mapParams, true);
		HttpUtil.checkHttpRequest(jsonStr);
		return jsonStr;
	}
	
	/**
	 * 内部API请求调用
	 * @param requestKey
	 * @param mapParams
	 * @return
	 */
	public static JSONObject internalHttpPostRequestSucess(String requestKey, Map<String, String> mapParams, boolean removeNullValue){
		JSONObject jsonStr = HttpUtil.internalHttpPostRequest(requestKey, mapParams, removeNullValue);
		HttpUtil.checkHttpRequest(jsonStr);
		return jsonStr;
	}
	
	/**
	 * 内部API请求调用
	 * @param requestKey
	 * @param mapParams
	 * @param removeNullValue
	 * @return
	 */
	public static JSONObject internalHttpPostRequest(String requestKey, Map<String, String> mapParams, boolean removeNullValue){
		return HttpUtil.internalHttpPostRequest(requestKey, mapParams, removeNullValue, null, 0);
	}
	
	/**
	 * 内部API请求调用
	 * @param requestKey
	 * @param mapParams
	 * @return
	 */
	public static JSONObject internalHttpPostRequest(String requestKey, Map<String, String> mapParams, boolean removeNullValue, Map<String, String> headers, int timeout){

		JSONObject jsonStr = new JSONObject();
		Map<String, String> newMap = null;
		if(removeNullValue){
			newMap = removeMapNullValue(mapParams);
		} else {
			newMap = mapParams;
		}

//		String uri = PropertiesUtil.getHttpRequestPath(requestKey);
		//String uri = PropertiesUtil.getMarketConfig("MARKET_API_URL");
		try{
			jsonStr = HttpUtil.httpPostReturnJson(null, newMap, timeout, headers);
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		if (jsonStr==null || jsonStr.size()<=0){
			jsonStr.put("code", "1");
			jsonStr.put("message", "HTTP未请求到数据");
		}
	//	if("1".equals(getDocList().get(uri))){
			System.out.println("--------------------------------");
			if (newMap!=null && newMap.size()>0){
				for(Entry<String, String> entry : newMap.entrySet()) {
					System.out.println(entry.getKey()+":"+ entry.getValue());
				}
			}
			//特殊的action不用打印，数据量过大
			if(!"httpAccountList".equals(requestKey)) {
				System.out.println(jsonStr);
			}

			System.out.println("--------------------------------");
		//}
		return jsonStr;
	}

	/**
	 * 内部API请求调用
	 * @param requestKey
	 * @param str
	 * @return
	 */
	public static JSONObject internalHttpPostRequestSucess(String requestKey, String str) {
		JSONObject jsonStr = HttpUtil.internalHttpPostRequest(requestKey, str);
		HttpUtil.checkHttpRequest(jsonStr);
		return jsonStr;
	}
	
	/**
	 * 内部API请求调用
	 * @param requestKey

	 * @return
	 */
	public static JSONObject internalHttpPostRequest(String requestKey, String str){
		//创建返回json对象
		JSONObject jsonStr = new JSONObject();
		try{
			jsonStr = HttpUtil.httpPostReturnJson(requestKey, str);
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		if (jsonStr==null || jsonStr.size()<=0){
			jsonStr.put("code", "1");
			jsonStr.put("message", "HTTP未请求到数据");
		}
		return jsonStr;
	}
	public static Map<String ,String> getDocList(){
		HashMap<String ,String> hm =new HashMap<String ,String>();
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getPurchaseContractList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getLogisticsStateList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderInvoice/getInvoice.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getShippingMailSendInfo.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/countShippingNote.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/selectOrderInvoiceDetail.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getShippingList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/selectPickGoods.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/pickGoodsSaveOrUpdate.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/pickGoodsSaveOrUpdate.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getClearanceFeedback.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/updaterelatedType.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/insertClearanceFeedback.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getShippingInfo.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/insertShippingInfo.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getBillPaperDeliverInfo.do","1");
		hm.put("http://localhost:8084/market_api_webservice/salesContract/getCustomerList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/insertBillPaperDeliverInfo.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/insertTEOrderState.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderLogistics/selectTEOrderLogisticsBySelective.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderLogistics/updateTEOrderLogisticsBySelective.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderCost/getOrderCost.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/selectTemplateList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getEnquiryDetailList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/getEnquiryList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/searchCompanyList.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/shippingDetail.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderLogistics/getInvoiceSum.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/updateShippingInfo.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/setShipping.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/delShipping.do","1");
		hm.put("http://localhost:8084/market_api_webservice/orderManage/updateOrderState.do","1");
		return hm;
	}
	/**
	 * 移除map中value为null的值
	 * @param oldMap
	 * @return
	 */
	public static Map<String, String> removeMapNullValue(Map<String, String> oldMap){
		if (oldMap==null){
			return null;
		}
		Map<String, String> newMap = new HashMap<String, String>();
		Set<Entry<String, String>> entries = oldMap.entrySet();
		for (Entry<String, String> entry : entries) {
			String newKey = entry.getKey();
			Object obj =entry.getValue();
			if(obj instanceof String){
				String newValue = (String)obj;
				if (StringUtils.isNotEmpty(newValue)){
					newMap.put(newKey, newValue);
				}
			}else{
				if (obj!=null){
					newMap.put(newKey, obj.toString());
				}
			}

		}
		return newMap;
	}
	
	/**
	 * 检查API访问是否成功
	 * @param object
	 * @return
	 */
	public static boolean isHttpRequestSuccess(JSONObject object) {
		String value = StringUtil.toString(object.get("code"));
		return "000".equals(value);
	}
	
	/**
	 * 检查API访问是否成功
	 * @param object
	 */
	public static void checkHttpRequest(JSONObject object) {
		if(!HttpUtil.isHttpRequestSuccess(object)){
		}
	}
	
	public static List<Map<String, Object>> getHttpList(String string, Map<String, String> paraMap) {
		JSONObject result = HttpUtil.internalHttpPostRequestSucess(string, paraMap);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list = JsonConvertUtil.fastJson2MapList(result.getString("data"));
		return list;
	}
	
	public static Map<String, Object> getHttpMap(String string,Map<String, String> paraMap) {
		JSONObject result = HttpUtil.internalHttpPostRequestSucess(string, paraMap);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap = JsonConvertUtil.convert2MapObject(result.getString("data"));
		return resMap;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> getHttpEntityList(String string, Object obj) {
		JSONObject result = null;
		if(obj instanceof Map) {
			result = HttpUtil.internalHttpPostRequestSucess(string, (Map<String,String>) obj);
		}
		else {
			String paraStr = JsonConvertUtil.toFastJsonString(obj);
			result = HttpUtil.internalHttpPostRequestSucess(string, paraStr);
		}
		
		if(HttpUtil.isHttpRequestSuccess(result)) {
			List<T> resList = (List<T>) JsonConvertUtil.fastJson2EntityList(result.getString("data"), obj.getClass());
			return resList;
		}
		return null;
	}
	
	/**
	 * 获取请求头
	 * @return
	 */
	public static Map<String, String> getIMAuthorizationHeaderMap() {
		Map<String, String> headers = new HashMap<>();

		return headers;
	}

}
