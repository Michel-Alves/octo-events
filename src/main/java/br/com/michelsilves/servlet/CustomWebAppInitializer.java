package br.com.michelsilves.servlet;

import br.com.michelsilves.servlet.filter.CustomeRequestLoggingFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class CustomWebAppInitializer implements
        WebApplicationInitializer {
    public void onStartup(ServletContext container) {

        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("br.com.michelsilves");
        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher
                = container.addServlet("dispatcher",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        container.addFilter("customRequestLoggingFilter",
                CustomeRequestLoggingFilter.class)
                .addMappingForServletNames(null, false, "dispatcher");
    }
}