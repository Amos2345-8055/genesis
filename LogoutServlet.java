package com.javatpoint;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Delete the "name" cookie
        Cookie cookie = new Cookie("name", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        out.println("You are successfully logged out!");
        response.sendRedirect("login.html");

        out.close();
    }
}
