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

@WebServlet("/admin/delete")
public class DeleteServlet extends HttpServlet {

    private UserService userService = UserImplService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        User user = userService.getUserById(Long.valueOf(req.getParameter("id")));
        userService.deleteUser(user);
        resp.sendRedirect("/admin");
    }
}
