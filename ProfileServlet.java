package com.javatpoint;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = null;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("name".equals(c.getName())) {
                    name = c.getValue();
                    break;
                }
            }
        }

        if (name != null && !name.isEmpty()) {
            out.println("<b>Welcome to your profile!</b><br>");
            out.println("Hello, " + name + "!");
        } else {
            out.println("Please login first.");
            response.sendRedirect("login.html");
        }

        out.close();
    }
}
