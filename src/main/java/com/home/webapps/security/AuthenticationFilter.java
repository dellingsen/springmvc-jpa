package com.home.webapps.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements javax.servlet.Filter {
    protected ServletContext servletContext;
 
    public void init(FilterConfig filterConfig) {
        servletContext = filterConfig.getServletContext();
    }
 
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException 
    {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
     
        if ("login".equals(getRequestedPage(req)) || 
        	"createUser".equals(getRequestedPage(req))) 
        { 
            // Accessing login/logout is always permitted 
            chain.doFilter(req, resp); 
            return; 
        } 
        
        if (!isAuth(req)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return; //break filter chain, requested JSP/servlet will not be executed
        }
     
        //propagate to next element in the filter chain, ultimately JSP/ servlet gets executed
        chain.doFilter(request, response);       
    }
 
    /**
     * logic to accept or reject access to the page, check log in status
     * @return true when authentication is deemed valid
     */
    protected boolean isAuth(HttpServletRequest request)
    {
    	HttpSession session = request.getSession(); 
        return (session.getAttribute("User") != null); 
    }

    private String getRequestedPage(HttpServletRequest aHttpRequest) 
    { 
        String url = aHttpRequest.getRequestURI(); 
        int firstSlash = url.indexOf("/",1); 
        String requestedPage = null; 
        if (firstSlash != -1) requestedPage = 
            url.substring(firstSlash + 1, url.length()); 
        return requestedPage; 
    } 
    
	@Override
	public void destroy() {
		
	}
 
}