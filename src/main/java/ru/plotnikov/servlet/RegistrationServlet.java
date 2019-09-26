package ru.plotnikov.servlet;

import ru.plotnikov.model.User;
import ru.plotnikov.service.UserImplService;
import ru.plotnikov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private UserService userService = UserImplService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String pass = req.getParameter("password");

        if (!userService.isExist(name, pass)) {
            int age = Integer.valueOf(req.getParameter("age"));
            User user = new User(name, pass, age, "user");
            userService.addUser(user);
            HttpSession session = req.getSession(false);
            if (session.getAttribute("role") != null && session.getAttribute("role").equals("admin"))
                resp.sendRedirect("/admin");
//            req.setAttribute("id", user.getId());
            resp.sendRedirect("/login");
//            resp.sendRedirect("/user?id="+user.getId()+"&name="+user.getName());
        }
        else {
            req.setAttribute("isOk", false);
            req.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(req, resp);
        }
    }
}
