package dev.cooremans.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cooremans.daos.UsersDaoPostgres;
import dev.cooremans.services.AuthService;
import dev.cooremans.servlets.AuthServlet;
import dev.cooremans.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import java.time.LocalDateTime;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce){

        System.out.println("[LOG] -  The servlet was initialized at " + LocalDateTime.now());
        ObjectMapper mapper = new ObjectMapper();


        UsersDaoPostgres userDAO = new UsersDaoPostgres();
        AuthService authService = new AuthService(userDAO);

        UserServlet userServlet = new UserServlet(mapper, userDAO, authService);
        AuthServlet authServlet = new AuthServlet(mapper, userDAO, authService);

        ServletContext context = sce.getServletContext();

        context.addServlet("AuthServlet", authServlet).addMapping("/auth/*", "/auth/login");

        ServletRegistration.Dynamic registeredServlet = context.addServlet("UserServlet", userServlet);
        registeredServlet.setLoadOnStartup(3);
        registeredServlet.setInitParameter("user-servlet-key", "user-servlet-value");
        registeredServlet.setInitParameter("another-param", "another-value");
        registeredServlet.addMapping("/users/*");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[LOG] - The servlet context was destroyed at " + LocalDateTime.now());
    }

}