package dev.cooremans.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.entities.Users;
import dev.cooremans.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class AuthServlet extends HttpServlet {
    private final ObjectMapper mapper;
    private final UsersDaoPostgres userDAO;
    private final AuthService authService;

    public AuthServlet(ObjectMapper mapper, UsersDaoPostgres userDAO, AuthService authService) {
        this.mapper = mapper;
        this.userDAO = userDAO;
        this.authService = authService;
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session == null) {
//            HashMap<String, Object> errorMessage = new HashMap<>();
//            errorMessage.put("code", 401);
//            errorMessage.put("message", "No session found on request");
//            errorMessage.put("timestamp", LocalDateTime.now().toString());
//
//            resp.setStatus(401); //UNAUTHORIZED USER
//            resp.setContentType("application/json");
//            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
//            return;
//        }
//        System.out.println("[LOG] - session found on request!");
//        System.out.println("[LOG] - session details: " + session.getAttribute("auth-user"));
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Users> users = userDAO.getAllUsers();

        HashMap<String, Object> credentials =  mapper.readValue(req.getInputStream(), HashMap.class);

        String providedUsername = (String) credentials.get("username");
        String providedPassword = (String) credentials.get("password");
        String result = null;

//        Users getU = authService.login(providedUsername, providedPassword);
//        resp.setContentType("application/json");
//        if(getU != null) {
//            result = mapper.writeValueAsString(getU);
//        }
//        resp.getWriter().write(result);

        resp.setContentType("application/json");

        for (Users user: users) {
            if (providedUsername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
                System.out.println("[LOG] - found user!");

                Users getU = authService.login(providedUsername, providedPassword);

                if(getU != null) {
                    result = mapper.writeValueAsString(getU);
                }

                // Because HTTP is a stateless protocol, we need a session to persist data across multiple requests
                HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
                session.setAttribute("auth-user", user); // this attribute is visible on any requests with this session attached
                resp.getWriter().write(result);
                return;

            } else {
                resp.setStatus(400);
                resp.setContentType("application/json");
                HashMap<String, Object> errorMessage = new HashMap<>();
                errorMessage.put("code", 400);
                errorMessage.put("message", "No user found with provided credentials");
                errorMessage.put("timestamp", LocalDateTime.now().toString());
                result = mapper.writeValueAsString(errorMessage);
                resp.getWriter().write(result);
                return;
            }
        }
    }
}


//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        //List<Users> users = userDAO.getAllUsers();
//
//        Users user = mapper.readValue(req.getInputStream(), Users.class);
//
//        //HashMap<String, Object> credentials =  mapper.readValue(req.getInputStream(), HashMap.class);
//
//        //String providedUsername = (String) credentials.get("username");
//        // String providedPassword = (String) credentials.get("password");
//        String result = null;
//        //Users userp = userDAO.getUserByUsername(providedUsername);
//        Users getU = authService.login(user.getUsername(), user.getPassword());
//        resp.setContentType("application/json");
//        if(getU != null) {
//            HttpSession session = req.getSession();
//            session.setAttribute("auth-user", user);
//            result = mapper.writeValueAsString(getU);
//
//        } else {
//            resp.setStatus(400);
//            resp.setContentType("application/json");
//
//            HashMap<String, Object> errorMessage = new HashMap<>();
//            errorMessage.put("code", 400);
//            errorMessage.put("message", "No user found with provided credentials");
//            errorMessage.put("timestamp", LocalDateTime.now().toString());
//
//            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
//        }
//        resp.getWriter().write(result);
//
////        for (Users user: users) {
////            if (providedUsername.equals(user.getUsername()) && providedPassword.equals(user.getPassword())) {
////                System.out.println("[LOG] - found user!");
////
////                Users getU = authService.login(providedUsername, providedPassword);
////
////                // Because HTTP is a stateless protocol, we need a session to persist data across multiple requests
////                HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
////                session.setAttribute("auth-user", user); // this attribute is visible on any requests with this session attached
////
////                resp.setStatus(204); // NO CONTEnt (success but nothing to return)
////                return; // return here otherwise we continue
////            }
////            resp.setStatus(400);
////            resp.setContentType("application/json");
////
////            HashMap<String, Object> errorMessage = new HashMap<>();
////            errorMessage.put("code", 400);
////            errorMessage.put("message", "No user found with provided credentials");
////            errorMessage.put("timestamp", LocalDateTime.now().toString());
////
////            resp.getWriter().write(mapper.writeValueAsString(errorMessage));
//
//    }
