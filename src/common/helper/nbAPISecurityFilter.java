package common.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class nbAPISecurityFilter implements Filter{

	private List<String> excludedPages = new ArrayList<String>();
	
	WebApplicationContext springContext;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		Boolean isExcludedPath = false;
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String servletPath = httpServletRequest.getServletPath();
		
		for( String excludePath : excludedPages){
			//设计上应该是/openAPI/路径下的所有的api都是不需要登录访问的，详细的参看web.xml中的配置
			if( servletPath.startsWith(excludePath) ){
				isExcludedPath = true;
			}
		}
		
		System.out.println("nbAPISecurityFilter: "+servletPath+" isExcludedPath:"+isExcludedPath);
		//需要拦截
		if( !isExcludedPath ){
			Object sessionUser=httpServletRequest.getSession().getAttribute("sessionUser");
			if(null==sessionUser&&!"success".equals((String)sessionUser)){
				httpServletResponse.sendRedirect("/active/login.html");
				return;
			}
		}
		chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludedPage = filterConfig.getInitParameter("excludedPages");
		String[] excludedPageArray = excludedPage.split(";");
		excludedPages.clear();
		for(int i = 0 ; i < excludedPageArray.length ; i++){
			excludedPages.add(excludedPageArray[i].trim());
		}
		springContext = 
		        WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
	}
}
