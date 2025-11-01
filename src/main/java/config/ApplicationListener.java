package config;

import converter.UserToUserEntityConverter;
import dao.DataClass;
import service.UserService;
import service.UserServiceImpl;
import servlet.LoginServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataClass dataClass = new DataClass();
        UserToUserEntityConverter userConverter = new UserToUserEntityConverter();
        UserService userService = new UserServiceImpl(dataClass, userConverter);

        context.setAttribute("userService", userService);
    }
}
