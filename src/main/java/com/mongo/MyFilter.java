package com.mongo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		System.out.println("FILTR LOAD");
        HttpServletRequest request = (HttpServletRequest) arg0;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());
 
   
        ((HttpServletResponse) arg1).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) arg1).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
 	((HttpServletResponse) arg1).addHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        HttpServletResponse resp = (HttpServletResponse) arg1;
 
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
 
        // pass the request along the filter chain
        arg2.doFilter(request, arg1);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
