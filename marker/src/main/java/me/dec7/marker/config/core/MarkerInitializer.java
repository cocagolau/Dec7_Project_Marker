package me.dec7.marker.config.core;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import me.dec7.marker.config.marker.AppConfig;
import me.dec7.marker.filter.SiteMeshFilter;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class MarkerInitializer implements WebApplicationInitializer {
	
	private static final String ALL = "/*";
	private static final String ROOT = "/";
	private static final String MARKER = "marker";
	private static final String UTF8 = "UTF-8";
	private static final String TRUE = "true";

	@Override
	public void onStartup(ServletContext container) {
		// spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);

		// root application context 라이프사이클 관리
		container.addListener(new ContextLoaderListener(rootContext));

		// spring application context의 dispatcher servlet
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class);

		// dispatcher servlet 등록
		ServletRegistration.Dynamic dispatcher = container.addServlet(MARKER, new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(ROOT);
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", TRUE);

		// filter
		container.addFilter("sitemeshFilter", SiteMeshFilter.class).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, ALL);
		FilterRegistration charEncodingfilterReg = container.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
		charEncodingfilterReg.setInitParameter("encoding", UTF8);
		charEncodingfilterReg.setInitParameter("forceEncoding", TRUE);
		charEncodingfilterReg.addMappingForUrlPatterns(null, false, ALL);
		
		FilterRegistration springSecurityFilterChain = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, ALL);
	}

}