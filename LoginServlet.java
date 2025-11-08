package com.javatpoint;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String password = request.getParameter("password");

        if (name == null || name.isEmpty() || password == null || password.isEmpty()) {
            out.println("Error: Please provide both username and password.");
            return;
        }

        if ("admin123".equals(password)) {
            // Create cookie for user
            Cookie userCookie = new Cookie("name", name);
            userCookie.setMaxAge(3600); // 1 hour
            response.addCookie(userCookie);

            // Redirect to profile page
            response.sendRedirect("ProfileServlet");
        } else {
            out.println("Invalid username or password!");
            response.sendRedirect("login.html");
        }

        out.close();
    }
}
