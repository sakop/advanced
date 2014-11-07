package spring.mvc;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
public class SpringFilter implements Filter{

    @Override
    public void init(FilterConfig paramFilterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
            FilterChain paramFilterChain) throws IOException, ServletException {
        System.out.println("before action");
        paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
        System.out.println("after action");
    }

    @Override
    public void destroy() {

    }


}
