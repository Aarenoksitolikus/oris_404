package servlet;

import dao.DataClass;
import entity.UserEntity;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Этот сервлет позволяет аутентифицировать пользователя
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        this.userService = new UserServiceImpl(new DataClass());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("src/main//webapp/html/login_page.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.trim().isBlank()
                || password == null || password.isEmpty()) {
            resp.sendRedirect("/login?error=Username and password are required");
        }

        UserEntity user = userService.authenticateUser(username, password);

        if (user == null) {
            resp.sendRedirect("/login?error=User does not exist");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            resp.sendRedirect("/index");
        }
    }
}
