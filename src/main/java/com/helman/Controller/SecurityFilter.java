package com.helman.Controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@project order
@Author Mahdieh Parhizkari
@Date 1/9/21
@Time 3:25AM
        Created by Intellije IDEA
        Description:JPA-Criteria*/

@WebFilter(filterName = "SecurityFilter", servletNames = {"CustomerAct", "EmployeeAct", "OfficeAct",
        "orderAct", "OrderdetailAct", "PaymentAct", "ProductAct", "ProductlineAct", "UserAct"})
public class SecurityFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(request, response);
        HttpServletRequest req = (HttpServletRequest)request;
        if (req.getSession() != null && req.getSession().getAttribute("sessionUser") != null)
            chain.doFilter(request, response);
        else
            ((HttpServletResponse)response).sendRedirect("index.jsp");
    }
}
