package servlet;

import model.User;
import service.UserHibService;
import service.UserImplService;
import service.UserService;
import service.UserJdbcService;

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
