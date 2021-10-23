package com.solutions.denisovich.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "EditPasswordFilter", urlPatterns = {"/edit_user"}, servletNames = {"EditUserServlet"})
public class EditPasswordFilter implements Filter {
    private static final Logger LOG = LogManager.getLogger(EditPasswordFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String password = servletRequest.getParameter("password");
        LOG.info("password new: {}", password);
        String passwordPrevious = servletRequest.getParameter("password_previous");
        LOG.info("password_previous: {}", passwordPrevious);
        if (password == null || password.equals("")) {
            servletRequest.setAttribute("password", passwordPrevious);
        } else {
            servletRequest.setAttribute("password", password);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
