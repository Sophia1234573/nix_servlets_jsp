package com.solutions.denisovich.custom_tag;

import com.solutions.denisovich.config.DatabaseConnectionManager;
import com.solutions.denisovich.model.entity.User;
import com.solutions.denisovich.model.dao.JdbcUserDao;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class DisplayUsersTableTag extends TagSupport {
    JdbcUserDao jdbcUserDao;

    @Override
    public int doStartTag() throws JspException {
        jdbcUserDao = new JdbcUserDao(DatabaseConnectionManager.getDataSource());
        List<User> all = jdbcUserDao.findAll();
        JspWriter out = pageContext.getOut();
        StringBuilder builder = new StringBuilder();
        builder.append("<table class=\"table table-striped\" border=\"1\" cellpadding=\"5\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Login</th>\n" +
                "      <th scope=\"col\">First Name</th>\n" +
                "      <th scope=\"col\">Last Name</th>\n" +
                "      <th scope=\"col\">Age</th>\n" +
                "      <th scope=\"col\">Role</th>\n" +
                "      <th scope=\"col\">Actions</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "<tbody>\n");
        for (User user : all) {

            builder.append("<tr><td>")
                    .append(user.getLogin())
                    .append("</td><td>")
                    .append(user.getFirstName())
                    .append("</td><td>")
                    .append(user.getLastName())
                    .append("</td><td>")
                    .append(calculateAge(user.getBirthday()))
                    .append("</td><td>")
                    .append(user.getRole().getName())
                    .append("</td><td>")
                    .append("<a href=\"/get_user_to_edit?id=" + user.getId() + "\" class=\"btn btn-primary\"><b>Edit</b></a>")
                    .append("<button data-href=\"/delete?id=" + user.getId() + "\" class=\"btn btn-danger\" data-bs-toggle=\"modal\" data-bs-target=\"#confirmDelete\"><b>Delete</b></button>")
                    .append("</td></tr>");
        }
        try {
            out.write(builder.toString());
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.print("</tbody></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }

    private int calculateAge(Date birthday) {
        LocalDate now = LocalDate.now();
        LocalDate birt = Instant.ofEpochMilli(birthday.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        return Period.between(birt, now).getYears();
    }
}
