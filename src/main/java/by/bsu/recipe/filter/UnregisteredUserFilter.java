package by.bsu.recipe.filter;

import by.bsu.recipe.bean.model.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UnregisteredUserFilter implements Filter {

    private static final String LOGIN_PAGE = "/login.xhtml";
    private static final String IGNORED_PARAMETER = "ignored";
    private List<String> ignoredPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String pages = filterConfig.getInitParameter(IGNORED_PARAMETER);
        ignoredPages = Arrays.asList(pages.split(";"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String url = request.getRequestURI();

        UserBean userBean = (UserBean) session.getAttribute("userBean");

        if (userBean == null) {
            System.out.println("Unregistered user filtered because of null userBean");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, servletResponse);
            return;
        }

        boolean loggedIn = userBean.isAuthorized();
        boolean ignoredPage = ignoredPages.stream().anyMatch(s -> url.equals(request.getContextPath() + s));

        if (!loggedIn && !ignoredPage) {
            System.out.println("Unregistered user filtered");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, servletResponse);
        } else {
            filterChain.doFilter(request, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
