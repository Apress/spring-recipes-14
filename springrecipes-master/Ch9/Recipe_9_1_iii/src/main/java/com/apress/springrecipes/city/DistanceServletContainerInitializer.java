package com.apress.springrecipes.city;

import com.apress.springrecipes.city.config.DistanceConfiguration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * Created by marten on 09-07-14.
 */
public class DistanceServletContainerInitializer implements ServletContainerInitializer {


    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        // Register the ContextLoaderListener
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(DistanceConfiguration.class);

        ctx.addListener(new ContextLoaderListener(appContext));

        // Register the Servlet
        ServletRegistration.Dynamic registration = ctx.addServlet("distance", new HttpRequestHandlerServlet());
        registration.setLoadOnStartup(1);
        registration.addMapping("/distance");


    }
}
