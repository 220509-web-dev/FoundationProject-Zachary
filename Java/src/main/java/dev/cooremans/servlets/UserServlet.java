package dev.cooremans.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.dto.ErrorResponse;
import dev.cooremans.dto.ResourceCreationResponse;
import dev.cooremans.entities.Users;
import dev.cooremans.services.AuthService;
import dev.cooremans.utils.exceptions.DataSourceException;
import dev.cooremans.utils.exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final AuthService authService;

    private final UsersDaoPostgres userDAO;

    public UserServlet(ObjectMapper mapper,UsersDaoPostgres userDAO, AuthService authService) {
        this.mapper = mapper;
        this.userDAO = userDAO;
        this.authService = authService;
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

        String username = req.getParameter("username");
        String id = req.getParameter("id");
        System.out.println("[LOG] - MADE IT INSIDE GET");

        if (username != null) {
            System.out.println("[LOG] - MADE IT INSIDE username");

            Users user = authService.getByUsername(username);
            resp.setContentType("application/json");
            resp.getWriter().write(String.valueOf(user));
        } else if (id != null) {
            System.out.println("[LOG] - MADE IT INSIDE id");

            Users user = authService.getUserBeId(id);
            resp.setContentType("application/json");
            resp.getWriter().write(String.valueOf(user));
        } else {
            System.out.println("[LOG] - MADE IT INSIDE all");
            List<Users> users = userDAO.getAllUsers();
            String response = mapper.writeValueAsString(users);
            resp.setContentType("application/json");
            resp.getWriter().write(response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("[LOG] - UserServlet received a request at " + LocalDateTime.now());

        resp.setContentType("application/json");

        try{
            Users newUser = mapper.readValue(req.getInputStream(), Users.class);
            ResourceCreationResponse payload = authService.createNewUser(newUser);
            resp.setStatus(201);
            resp.getWriter().write(mapper.writeValueAsString(payload));
        } catch (InvalidRequestException e) {
            resp.setStatus(400);
            resp.getWriter().write(mapper.writeValueAsString(new ErrorResponse(400, e.getMessage())));
        } catch (DataSourceException e) {
            resp.setStatus(500);
            System.out.println(e.getMessage());
            resp.getWriter().write(mapper.writeValueAsString(new ErrorResponse(500, "An internal error occurred. Devs please check application logs.")));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        resp.setStatus(204);
    }
}
