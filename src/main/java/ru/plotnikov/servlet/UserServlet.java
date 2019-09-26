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

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService = UserImplService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Long id;
        if (req.getParameter("id")==null)
            id = (Long) req.getSession(false).getAttribute("id");
        else
            id = Long.valueOf(req.getParameter("id"));
        User user = userService.getUserById(id);
        String editName = req.getParameter("editName");
        String addName = req.getParameter("addName");
        if (editName != null)
            req.setAttribute("editName", editName);
        if (addName != null)
            req.setAttribute("addName", addName);
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/user.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        int age = Integer.valueOf(req.getParameter("age"));
        String pass = req.getParameter("password");
        Long id;
        if (req.getParameter("id")==null)
            id = (Long) req.getSession(false).getAttribute("id");
        else
            id = Long.valueOf(req.getParameter("id"));
        User user = userService.getUserById(id);
        user.setAge(age);
        user.setName(name);
        user.setPassword(pass);
        userService.updateUser(user);
//        req.setAttribute("editName", name);
        if (req.getSession(false).getAttribute("role").equals("admin"))
            resp.sendRedirect("/admin?editName="+name);
        else
            resp.sendRedirect("/user?editName="+user.getName());
//        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
