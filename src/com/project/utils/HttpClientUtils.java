package com.project.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;

/**
 *  依赖的jar包有：commons-lang-2.6.jar、httpclient-4.3.2.jar、httpcore-4.3.1.jar、commons-io-2.4.jar
 * @author 
 *
 */
public class HttpClientUtils {

    public static final int CONN_TIMEOUT=10000;
    public static final int READ_TIMEOUT=10000;
    public static final String DEFAULT_CHARSET="UTF-8";
    private static HttpClient client = null;
    public static Logger logger = Logger.getLogger(HttpClientUtils.class);  
    
    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(128);
        cm.setDefaultMaxPerRoute(128);
        client = HttpClients.custom().setConnectionManager(cm).build();
    }
    
    public static String postParameters(String url, String stringBody) throws ConnectTimeoutException, SocketTimeoutException, Exception{
        return postString(url,stringBody,"application/x-www-form-urlencoded",DEFAULT_CHARSET,CONN_TIMEOUT,READ_TIMEOUT);
    }
    
    public static String postParameters(String url, Map<String, String> params) throws ConnectTimeoutException,  
     SocketTimeoutException, Exception {
         return postForm(url, params, null, CONN_TIMEOUT, READ_TIMEOUT);
     }
    
    public static String get(String url) throws Exception {  
        return get(url, DEFAULT_CHARSET, null, null);  
    }
    
    public static String get(String url, String charset) throws Exception {  
        return get(url, charset, CONN_TIMEOUT, READ_TIMEOUT);  
    } 

    public static String postFile(String url, File file) throws ConnectTimeoutException, SocketTimeoutException, Exception{
    	Map<String, File> files = new HashMap<String, File>();
    	files.put("file", file);
    	return postFile(url, files, null, DEFAULT_CHARSET, CONN_TIMEOUT, READ_TIMEOUT);
    }
    
    /** 
     * 发送一个 Post 请求, 使用指定的字符集编码. 
     * @param url 
     * @param file 上传文件 
     * @param charset 编码 
     * @param connTimeout 建立链接超时时间,毫秒. 
     * @param readTimeout 响应超时时间,毫秒. 
     * @throws ConnectTimeoutException 建立链接超时异常 
     * @throws SocketTimeoutException  响应超时异常
     * @throws Exception 
     */  
    public static String postFile(String url, Map<String, File> files, Map<String, String> headers,String charset, Integer connTimeout, Integer readTimeout) 
    		throws ConnectTimeoutException, SocketTimeoutException, Exception {
    	
    	HttpClient client = null;
    	HttpPost post = new HttpPost(url);
    	String result = "";
    	try {
    		
    		if (headers != null && !headers.isEmpty()) {  
                for (Entry<String, String> entry : headers.entrySet()) {  
                    post.addHeader(entry.getKey(), entry.getValue());  
                }  
            } 
    		
    		if(files != null && files.size() > 0){
    			MultipartEntityBuilder mebuilder = MultipartEntityBuilder.create();
    			mebuilder.setMode(HttpMultipartMode.RFC6532);
    			Iterator<Entry<String, File>> iterator = files.entrySet().iterator();
    			while(iterator.hasNext()){
    				Entry<String, File> keyVal = iterator.next();
    				File f = keyVal.getValue();
    				if(!f.exists()) continue;
    				mebuilder.addPart(keyVal.getKey(), new FileBody(f));
    			}
    			post.setEntity(mebuilder.build());
    		}
    		
    		// 设置参数
    		Builder customReqConf = RequestConfig.custom();
    		if (connTimeout != null) {
    			customReqConf.setConnectTimeout(connTimeout);
    		}
    		if (readTimeout != null) {
    			customReqConf.setSocketTimeout(readTimeout);
    		}
    		post.setConfig(customReqConf.build());
    		
    		HttpResponse response;
    		if (url.startsWith("https")) {
    			// 执行 Https 请求.
    			client = createSSLInsecureClient();
    		} else {
    			// 执行 Http 请求.
    			client = HttpClientUtils.client;
    		}
    		response = client.execute(post);
    		result = IOUtils.toString(response.getEntity().getContent(), charset);
    	} finally {
    		post.releaseConnection();
    		if (url.startsWith("https") && client != null&& client instanceof CloseableHttpClient) {
    			((CloseableHttpClient) client).close();
    		}
    	}
    	return result;
    }  
    /** 
     * 发送一个 Post 请求, 使用指定的字符集编码. 
     * @Title: postString
     * @Description: 
     * @param url RequestBody
     * @param body
     * @param mimeType
     * @param charset
     * @param connTimeout 建立链接超时时间,毫秒. 
     * @param readTimeout 响应超时时间,毫秒. 
     * @return ResponseBody, 使用指定的字符集编码.
     * @throws ConnectTimeoutException 建立链接超时异常 
     * @throws SocketTimeoutException  响应超时 
     */
    public static String postString(String url, String body, String mimeType,String charset, Integer connTimeout, Integer readTimeout) 
            throws ConnectTimeoutException, SocketTimeoutException, Exception {
        HttpClient client = null;
        HttpPost post = new HttpPost(url);
        String result = "";
        try {
            if (StringUtils.isNotBlank(body)) {
                HttpEntity entity = new StringEntity(body, ContentType.create(mimeType, charset));
                post.setEntity(entity);
            }
            // 设置参数
            Builder customReqConf = RequestConfig.custom();
            if (connTimeout != null) {
                customReqConf.setConnectTimeout(connTimeout);
            }
            if (readTimeout != null) {
                customReqConf.setSocketTimeout(readTimeout);
            }
            post.setConfig(customReqConf.build());

            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
            }
            HttpResponse response = client.execute(post);
            result = IOUtils.toString(response.getEntity().getContent(), charset);
        } finally {
            post.releaseConnection();
            if (url.startsWith("https") && client != null&& client instanceof CloseableHttpClient) {
                ((CloseableHttpClient) client).close();
            }
        }
        return result;
    }  
    
    
    
    /**
     * @Title: postForm
     * @Description: send post request
     * @param url 请求地址
     * @param params 请求参数
     * @param headers 请求头
     * @param connTimeout 连接超时时间
     * @param readTimeout 读取超时时间
     * @return 返回结果
     * @throws ConnectTimeoutException
     * @throws SocketTimeoutException
     * @throws Exception String
     * @throws
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers, Integer connTimeout,Integer readTimeout) throws ConnectTimeoutException,  
            SocketTimeoutException, Exception {  
  
        HttpClient client = null;  
        HttpPost post = new HttpPost(url);  
        try {  
            if (params != null && !params.isEmpty()) {  
                List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();  
                Set<Entry<String, String>> entrySet = params.entrySet();  
                for (Entry<String, String> entry : entrySet) {  
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
                }  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);  
                post.setEntity(entity);  
            }
            
            if (headers != null && !headers.isEmpty()) {  
                for (Entry<String, String> entry : headers.entrySet()) {  
                    post.addHeader(entry.getKey(), entry.getValue());  
                }  
            }  
            // 设置参数  
            Builder customReqConf = RequestConfig.custom();  
            if (connTimeout != null) {  
                customReqConf.setConnectTimeout(connTimeout);  
            }  
            if (readTimeout != null) {  
                customReqConf.setSocketTimeout(readTimeout);  
            }  
            post.setConfig(customReqConf.build());  
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
            }
            HttpResponse response = client.execute(post);
            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");  
        } finally {  
            post.releaseConnection();  
            if (url.startsWith("https") && client != null  
                    && client instanceof CloseableHttpClient) {  
                ((CloseableHttpClient) client).close();  
            }  
        }  
    } 
    
    
    
    
    /** 
     * 发送一个 GET 请求 
     *  
     * @param url 
     * @param charset 
     * @param connTimeout  建立链接超时时间,毫秒. 
     * @param readTimeout  响应超时时间,毫秒. 
     * @return 
     * @throws ConnectTimeoutException   建立链接超时 
     * @throws SocketTimeoutException   响应超时 
     * @throws Exception 
     */  
    public static String get(String url, String charset, Integer connTimeout,Integer readTimeout) 
            throws ConnectTimeoutException,SocketTimeoutException, Exception { 
        
        HttpClient client = null;  
        HttpGet httpGet = new HttpGet(url);  
        String result = "";  
        try {  
            // 设置参数  
            Builder customReqConf = RequestConfig.custom();  
            if (connTimeout != null) {  
                customReqConf.setConnectTimeout(connTimeout);  
            }  
            if (readTimeout != null) {  
                customReqConf.setSocketTimeout(readTimeout);  
            }  
            httpGet.setConfig(customReqConf.build());  
  
            if (url.startsWith("https")) {
                // 执行 Https 请求.
                client = createSSLInsecureClient();
            } else {
                // 执行 Http 请求.
                client = HttpClientUtils.client;
            }
            HttpResponse response = client.execute(httpGet);
  
            result = IOUtils.toString(response.getEntity().getContent(), charset);  
        } finally {  
            httpGet.releaseConnection();  
            if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {  
                ((CloseableHttpClient) client).close();  
            }  
        }  
        return result;  
    }  
    
    /**
     * 创建 SSL连接
     * @return
     * @throws GeneralSecurityException
     */
	private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
		try {
			SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(null, new TrustStrategy() {
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (GeneralSecurityException e) {
			throw e;
		}
	}
	
	  /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            logger.error("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
}