package com.solutions.denisovich.controller;

import com.solutions.denisovich.config.DatabaseConnectionManager;
import com.solutions.denisovich.model.entity.User;
import com.solutions.denisovich.model.dao.JdbcUserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@WebServlet(name = "GetUserToEditServlet", urlPatterns = {"/get_user_to_edit"})
public class GetUserToEditServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(GetUserToEditServlet.class);
    JdbcUserDao jdbcUserDao;

    @Override
    public void init() {
        jdbcUserDao = new JdbcUserDao(DatabaseConnectionManager.getDataSource());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User userToEdit = jdbcUserDao.findById(Long.parseLong(id));
        LOG.info("userToEdit: {}", userToEdit);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String bd = formatter.format(userToEdit.getBirthday());
        request.setAttribute("userToEdit", userToEdit);
        request.setAttribute("bd", bd);
        getServletContext().getRequestDispatcher("/edit_user.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOG.info("I'm in post method");
    }
}
