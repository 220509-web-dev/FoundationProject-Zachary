package dev.cooremans.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cooremans.daos.UsersDAO;
import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.entities.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final UsersDaoPostgres userDAO;

    public UserServlet(ObjectMapper mapper, UsersDaoPostgres userDAO) {
        this.mapper = mapper;
        this.userDAO = userDAO;
    }

    @Override
    public void init() throws ServletException {
        System.out.println("[LOG] - UserServlet instantiated!");
        System.out.println("[LOG] - Init param, test-init-key: " + this.getServletConfig().getInitParameter("test-init-key"));
        System.out.println("[LOG] - Init param, user-servlet-key: " + this.getServletConfig().getInitParameter("user-servlet-key"));
        System.out.println("[LOG] - Init param, another-param: " + this.getServletConfig().getInitParameter("another-param"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Users> users = userDAO.getAllUsers();
        String respPayload = mapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().write(respPayload);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - UserServlet received a request at " + LocalDateTime.now());
        try {
            Users newUser = mapper.readValue(req.getInputStream(), Users.class);
            System.out.println(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.setStatus(204);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
