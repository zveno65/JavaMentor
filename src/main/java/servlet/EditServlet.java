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

@WebServlet("/edit/*")
public class EditServlet extends HttpServlet {

    private UserService userService = UserImplService.getInstance();
    //private UserService userService = UserJdbcService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUserById(Long.valueOf(req.getParameter("id")));
        req.setAttribute("user", user);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/views/edit.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int age = Integer.valueOf(req.getParameter("age"));
        long id = Long.valueOf(req.getParameter("id"));
        User user = userService.getUserById(id);
        user.setAge(age);
        user.setName(name);
        userService.updateUser(user);
        req.setAttribute("editName", name);
        new ListServlet().doGet(req, resp);
    }
}
