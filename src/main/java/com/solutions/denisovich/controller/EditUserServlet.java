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

@WebServlet(name = "EditUserServlet", urlPatterns = {"/edit_user"})
public class EditUserServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(EditUserServlet.class);
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
        getServletContext().getRequestDispatcher("/edit_user.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String login = request.getParameter("login");
        String password = (String) request.getAttribute("password");
        String email = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String birthday = request.getParameter("birthday");
        String roleName = request.getParameter("role");

        Role role = jdbcRoleDao.findByName(roleName);

        User userToUpdate = new User();
        userToUpdate.setId(Long.parseLong(id));
        userToUpdate.setLogin(login);
        userToUpdate.setPassword(password);
        userToUpdate.setFirstName(firstName);
        userToUpdate.setLastName(lastName);
        userToUpdate.setEmail(email);
        Date birthdayDate = null;
        try {
            birthdayDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userToUpdate.setBirthday(birthdayDate);
        userToUpdate.setRole(role);
        LOG.info("Updated user: {}", userToUpdate);
        jdbcUserDao.update(userToUpdate);
        getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}

