package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit/*")
public class EditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserService.getInstance().getUserById(Long.valueOf(req.getParameter("id")));
        req.setAttribute("name", user.getName());
        req.setAttribute("age", user.getAge());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.valueOf(req.getParameter("age"));
        long id = Long.valueOf(req.getParameter("id"));
        User user = UserService.getInstance().getUserById(id);
        user.setAge(age);
        user.setName(name);
        UserService.getInstance().updateUser(user);
        req.setAttribute("userName", name);
        doGet(req, resp);
    }
}
