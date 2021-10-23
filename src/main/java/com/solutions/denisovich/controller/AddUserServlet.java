package com.solutions.denisovich.controller;

import com.solutions.denisovich.config.DatabaseConnectionManager;
import com.solutions.denisovich.model.entity.Role;
import com.solutions.denisovich.model.entity.User;
import com.solutions.denisovich.model.dao.JdbcRoleDao;
import com.solutions.denisovich.model.dao.JdbcUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "AddUserServlet", urlPatterns = {"/add_user"})
public class AddUserServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(AddUserServlet.class);
    JdbcUserDao jdbcUserDao;
    JdbcRoleDao jdbcRoleDao;

    @Override
    public void init() {
        jdbcUserDao = new JdbcUserDao(DatabaseConnectionManager.getDataSource());
        jdbcRoleDao = new JdbcRoleDao(DatabaseConnectionManager.getDataSource());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String adminLastName = (String) session.getAttribute("adminLastName");
        request.setAttribute("adminLastName", adminLastName);
        getServletContext().getRequestDispatcher("/add_user.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String birthday = request.getParameter("birthday");
        String roleName = request.getParameter("role");

        Role role = jdbcRoleDao.findByName(roleName);

        User userToAdd = new User();
        userToAdd.setLogin(login);
        userToAdd.setPassword(password);
        userToAdd.setFirstName(firstName);
        userToAdd.setLastName(lastName);
        userToAdd.setEmail(email);
        Date birthdayDate = null;
        try {
            birthdayDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }
        userToAdd.setBirthday(birthdayDate);
        userToAdd.setRole(role);
        LOG.info("New user: {}", userToAdd);
        jdbcUserDao.create(userToAdd);
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
