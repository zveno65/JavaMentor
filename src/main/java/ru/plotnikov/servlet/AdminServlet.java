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
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private UserService userService = UserImplService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);
        if (req.getParameter("editName") != null)
            req.setAttribute("editName", req.getParameter("editName"));
        if (req.getParameter("addName") != null)
            req.setAttribute("addName", req.getParameter("addName"));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/list.jsp");
        requestDispatcher.forward(req, resp);
    }
}