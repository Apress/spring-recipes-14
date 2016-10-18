package com.apress.springrecipes.city.servlet;

import com.apress.springrecipes.city.config.DistanceConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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
        ctx.addListener(new RequestContextListener());

        // Configure facelets to use xhtml instead of jsp extension
        ctx.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
        // Map the FacesServlet to *.xhtml
        ctx.getServletRegistration("FacesServlet").addMapping("*.xhtml");
    }
}
