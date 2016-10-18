package com.apress.springrecipes.city.servlet;

import com.apress.springrecipes.city.config.DistanceConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by marten on 09-07-14.
 */
public class DistanceApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext ctx) throws ServletException {
        // Register the ContextLoaderListener
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(DistanceConfiguration.class);

        ctx.addListener(new ContextLoaderListener(appContext));

        // Register the Servlet
        ServletRegistration.Dynamic registration = ctx.addServlet("distance", new HttpRequestHandlerServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/distance");

        //Register the Filter
        FilterRegistration.Dynamic filterReg = ctx.addFilter("cityServiceRequestFilter", new DelegatingFilterProxy());
        filterReg.addMappingForServletNames(null, false, "distance");

    }
}
