package academy.learnprogramming.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {

    private final static String DISPATCHER_SERVLET_NAME = "dispatcher";


    @Override
    public void onStartup(ServletContext servletContext) {
        log.info("onStartup");

        // create the spring application context
        final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfig.class);

        // create the dispatcher servlet
        final DispatcherServlet servlet = new DispatcherServlet(context);

        // register and configure the dispatcher servlet
        final ServletRegistration.Dynamic registration = servletContext.addServlet(DISPATCHER_SERVLET_NAME, servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}