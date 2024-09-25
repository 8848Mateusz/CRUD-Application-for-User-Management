package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/show")
public class UserShow extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String userIdParam = req.getParameter("id");
        if (userIdParam != null && !userIdParam.isEmpty()) {
            try {
                int userId = Integer.parseInt(userIdParam);
                UserDao userDao = new UserDao();
                User user = userDao.read(userId);
                if (user != null) {
                    req.setAttribute("user", user);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "Nieprawidłowy format ID: " + userIdParam);
            }
        }
        getServletContext().getRequestDispatcher("/users/show.jsp").forward(req, res);
    }
}
