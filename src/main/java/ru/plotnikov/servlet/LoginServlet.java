package ru.plotnikov.servlet;

import ru.plotnikov.model.User;
import ru.plotnikov.service.UserImplService;
import ru.plotnikov.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class LoginServlet extends HttpServlet {
    private UserService userService = UserImplService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        if (userService.isExist(name, password)) {
            User user = userService.getUserByData(name, password);
            String role = user.getRole();
//            req.getSession().setAttribute("name", name);
            req.getSession().setAttribute("role", role);
            req.getSession().setAttribute("id", user.getId());
            if (role.equals("admin"))
                resp.sendRedirect("/"+role);
            else {
                resp.sendRedirect("/user?addName="+user.getName());
            }
        }
        else {
            resp.sendRedirect("/");
        }

    }
}
