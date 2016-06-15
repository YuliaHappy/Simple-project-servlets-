package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(false);
        if (session != null) {
            String name = session.getAttribute("name").toString();
            out.print("Welcome, " + name);
        } else {
            resp.sendError(403, "Session empty!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("login");
        String password = req.getParameter("password");

        out.print("Welcome, " + name);

        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        session.setAttribute("password", password);

        resp.sendRedirect("/ActionServlet");

        out.close();
    }
}
