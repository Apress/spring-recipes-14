package com.apress.springrecipes.city.filter;

import com.apress.springrecipes.city.CityServiceRequestAuditor;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;


/**
 * This class is designed to intercept requests to the {@link com.apress.springrecipes.city.CityServiceImpl} and log them
 */
public class CityServiceRequestFilter implements Filter {
    private CityServiceRequestAuditor cityServiceRequestAuditor;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain)
        throws IOException, ServletException {
        Map parameterMap = servletRequest.getParameterMap();

        this.cityServiceRequestAuditor.log(parameterMap);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    public void setCityServiceRequestAuditor(final CityServiceRequestAuditor cityServiceRequestAuditor) {
        this.cityServiceRequestAuditor = cityServiceRequestAuditor;
    }
}
