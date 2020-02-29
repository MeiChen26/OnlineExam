package com.project.utils;

import javax.servlet.http.HttpServletResponse;

public class HttpSendObject {
	/**
	 * 返回给客户端
	 * @param returnData
	 * @param response
	 * @return
	 */
	public static void sendObject(String returnData, HttpServletResponse response)
	{
		try
		{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			response.getWriter().write(returnData);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}finally {
			try{
				response.getWriter().flush();
				response.getWriter().close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	

}
