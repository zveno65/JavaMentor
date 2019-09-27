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

@WebServlet("/delete/*")
public class DeleteServlet extends HttpServlet {

    private UserService userService = UserImplService.getInstance();
    //private UserService userService = UserJdbcService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserById(Long.valueOf(req.getParameter("id")));
        userService.deleteUser(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/list");
        dispatcher.forward(req, resp);
    }
}
