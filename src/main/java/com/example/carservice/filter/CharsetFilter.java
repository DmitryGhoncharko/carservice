package com.example.carservice.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@Slf4j
@WebFilter(filterName = "CharsetFilter", urlPatterns = "/*")
public class CharsetFilter implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");
        if (encoding == null) encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Before setting encoding: " + request.getCharacterEncoding());
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding);
        response.setCharacterEncoding(encoding);
        log.info("After setting encoding: " + request.getCharacterEncoding());

        chain.doFilter(request, response);
    }

}