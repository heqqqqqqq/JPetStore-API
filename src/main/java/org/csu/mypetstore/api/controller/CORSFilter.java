package org.csu.mypetstore.api.controller;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*过滤器解决跨域问题*/
@WebFilter(filterName = "CorsFilter")
@Configuration
public class CORSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)throws IOException, ServletException{
        HttpServletResponse response=(HttpServletResponse)res;
        response.setHeader("Access-Control-Allow-Origin","*");//*表示任何人都可以跨域访问服务器
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,PATCH,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers","origin,X-requested-with,Content-Type,Accept");
        filterChain.doFilter(req,res);
    }
}

