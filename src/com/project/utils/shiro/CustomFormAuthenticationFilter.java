package com.project.utils.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;  

import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
  
  
/**
 * <p>ClassName: CustomFormAuthenticationFilter</p>
 * <p>Description: </p>
 * @Author  
 * @Date:
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter  {  
    @Override  
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {  
  
        return super.onAccessDenied(request, response, mappedValue);  
    }  
}  
