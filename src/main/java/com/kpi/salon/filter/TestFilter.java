package com.kpi.salon.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/TestFilter")
public class TestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();

//        if (session.getAttribute("user") == null) {
//
//            if (session.getAttribute("unconfirmedUser") == null) {
//                req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(servletRequest, servletResponse);
//            } else {
//                req.getRequestDispatcher("/WEB-INF/pages/code.jsp").forward(servletRequest, servletResponse);
//            }
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
