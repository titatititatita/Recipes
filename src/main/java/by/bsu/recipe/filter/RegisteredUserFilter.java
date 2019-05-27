package by.bsu.recipe.filter;

import by.bsu.recipe.bean.model.UserBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class RegisteredUserFilter implements Filter {

    private static final String INDEX_PAGE = "/index.xhtml";
    private static final String FILTERED_PARAMETER = "filtered";

    private List<String> filteredPages;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String pages = filterConfig.getInitParameter(FILTERED_PARAMETER);
        filteredPages = Arrays.asList(pages.split(";"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        UserBean userBean = (UserBean) session.getAttribute("userBean");

        if (userBean == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String url = request.getRequestURI();
        boolean loggedIn = userBean.isAuthorized();
        boolean filteredPage = filteredPages.stream().anyMatch(s -> url.equals(request.getContextPath() + s));

        if (loggedIn && filteredPage) {
            System.out.println("Registered user filtered");
            request.getRequestDispatcher(INDEX_PAGE).forward(request, servletResponse);
        } else {
            filterChain.doFilter(request, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
