package com.jack.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.jack.reggie.common.BaseContext;
import com.jack.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
check user logged in.
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // Path matcher
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestURI = httpServletRequest.getRequestURI();

        log.info("Request received:{}",requestURI);

        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        boolean check = check(requestURI,urls);

        if(check){
            log.info("This request is valid:{}",requestURI);
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;

        }
        // check already logged in?
        if(httpServletRequest.getSession().getAttribute("employee") != null){
            log.info("User already logged in, User id is : {}",httpServletRequest.getSession().getAttribute("employee"));

            Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);


            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        // check already logged in?
        if(httpServletRequest.getSession().getAttribute("user") != null){
            log.info("User already logged in, User id is : {}",httpServletRequest.getSession().getAttribute("user"));

            Long userId = (Long) httpServletRequest.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);


            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        // Stream the data to front end
        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }

    private boolean check(String URI, String[] urls) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url,URI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
