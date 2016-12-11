package co.fredyjimenezrendon.proyectobase.config;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionTrackingMode;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class SpringWebAppInitializer extends WebMvcConfigurerAdapter implements WebApplicationInitializer {


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
        appContext.register(ApplicationContextConfig.class);
        servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("SpringDispatcher", new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
	}
	

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
	}
	
}