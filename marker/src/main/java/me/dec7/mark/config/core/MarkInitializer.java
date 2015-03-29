package me.dec7.mark.config.core;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import me.dec7.mark.config.mark.MarkConfig;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class MarkInitializer implements WebApplicationInitializer {
	
	private static final String ALL = "/*";
	private static final String ROOT = "/";
	private static final String MARK = "mark";
	private static final String UTF8 = "UTF-8";
	private static final String TRUE = "true";

	@Override
	public void onStartup(ServletContext container) {
		// spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(MarkConfig.class);

		// root application context 라이프사이클 관리
		container.addListener(new ContextLoaderListener(rootContext));

		// spring application context의 dispatcher servlet
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(MarkServletConfig.class);

		// dispatcher servlet 등록
		ServletRegistration.Dynamic dispatcher = container.addServlet(MARK, new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(ROOT);
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", TRUE);

		// filter
		FilterRegistration charEncodingfilterReg = container.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
		charEncodingfilterReg.setInitParameter("encoding", UTF8);
		charEncodingfilterReg.setInitParameter("forceEncoding", TRUE);
		charEncodingfilterReg.addMappingForUrlPatterns(null, false, ALL);
		
		FilterRegistration springSecurityFilterChain = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, ALL);
	}

}