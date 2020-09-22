package org.sda.twitter.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", servletNames = {"PublishTweetServlet"}, urlPatterns = {"/profile.jsp", "/users.jsp", "/newTweet.html"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession(false);

        if (session != null) {
            chain.doFilter(request,response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }
}
