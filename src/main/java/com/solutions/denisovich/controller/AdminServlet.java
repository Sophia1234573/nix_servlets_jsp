package com.solutions.denisovich.controller;

import com.solutions.denisovich.config.DatabaseConnectionManager;
import com.solutions.denisovich.model.entity.User;
import com.solutions.denisovich.model.dao.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminServlet", urlPatterns = {"/hello_admin"})
public class AdminServlet extends HttpServlet {
    JdbcUserDao jdbcUserDao;

    @Override
    public void init() {
        jdbcUserDao = new JdbcUserDao(DatabaseConnectionManager.getDataSource());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        User userAdmin = jdbcUserDao.findByLogin(login);
        String lastName = userAdmin.getLastName();
        request.setAttribute("adminLastName", lastName);
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
