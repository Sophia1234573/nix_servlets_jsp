package com.solutions.denisovich.controller;

import com.solutions.denisovich.config.DatabaseConnectionManager;
import com.solutions.denisovich.model.entity.User;
import com.solutions.denisovich.model.dao.JdbcUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    JdbcUserDao jdbcUserDao;

    @Override
    public void init() {
        jdbcUserDao = new JdbcUserDao(DatabaseConnectionManager.getDataSource());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User userByLogin = jdbcUserDao.findByLogin(login);

        if (userByLogin.getPassword() == null || !userByLogin.getPassword().equals(password)) {
            response.getWriter().print("Sorry, but you are not registered in the system. Try again:)");
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
        else if (ValidateLogin.validateAdmin(userByLogin)) {
            String adminLastName = userByLogin.getLastName();
            session.setAttribute("adminLastName", adminLastName);
            request.setAttribute("adminLastName", adminLastName);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if (ValidateLogin.validateUser(userByLogin)) {
            String userFirsName = userByLogin.getFirstName();
            request.setAttribute("userFirsName", userFirsName);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        }
    }
}
