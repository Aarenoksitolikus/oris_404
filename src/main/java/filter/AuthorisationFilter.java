package filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Этот фильтр перехватывает все запросы ("/*") в приложение
 * и проверяет наличие в сессии объекта user.
 * <p>
 * Если в сессии есть активный пользователь, пропускает запрос
 * дальше по цепочке фильтров (FilterChain)
 * <p>
 * Если нет, перенаправляет запрос на главную страницу (index)
 * <p>
 * Не отрабатывает на общедоступные URL'ы
 */
@WebFilter("/*")
public class AuthorisationFilter extends HttpFilter {
    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        String requestURI = ((HttpServletRequest) req).getRequestURI();

        if (requestURI.equals("/index")
                || requestURI.equals("/registration")
                || requestURI.equals("/login")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = ((HttpServletRequest) req).getSession(false);

            if (session == null || session.getAttribute("user") == null) {
                ((HttpServletResponse) resp).sendRedirect("/login");
            } else {
                chain.doFilter(req, resp);
            }
        }
    }
}
