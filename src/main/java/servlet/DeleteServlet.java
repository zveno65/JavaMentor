package servlet;

import model.User;
import service.UserServiceHib;
import service.UserServiceJDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/*")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserServiceHib.getInstance().getUserById(Long.valueOf(req.getParameter("id")));
        UserServiceHib.getInstance().deleteUser(user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/list");
        dispatcher.forward(req, resp);
    }
}
