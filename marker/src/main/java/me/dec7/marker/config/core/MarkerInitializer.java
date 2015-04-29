package me.dec7.marker.config.core;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import me.dec7.marker.config.marker.AppConfig;
import me.dec7.marker.filter.SiteMeshFilter;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
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

	private final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	
	@Override
	public void onStartup(ServletContext servletContext) {
		// spring application context
//		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);

		// root application context 라이프사이클 관리
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		// spring session 리스너 등록
//		servletContext.addListener(new HttpSessionEventPublisher());
		

		// spring application context의 dispatcher servlet
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class);

		// dispatcher servlet 등록
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(MARKER, new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping(ROOT);
		dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", TRUE);

		// filter
		FilterRegistration charEncodingfilterReg = servletContext.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
		charEncodingfilterReg.setInitParameter("encoding", UTF8);
		charEncodingfilterReg.setInitParameter("forceEncoding", TRUE);
		charEncodingfilterReg.addMappingForUrlPatterns(null, false, ALL);
		
		FilterRegistration sitemeshFilterReg = servletContext.addFilter("sitemeshFilter", SiteMeshFilter.class);
		sitemeshFilterReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, ALL);
		
		FilterRegistration openEntityManagerInViewFilterReg = servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);
		openEntityManagerInViewFilterReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, ALL);
		
//		FilterRegistration springSecurityFilterReg = servletContext.addFilter("springSecurityFilter", new DelegatingFilterProxy("springSecurityFilterChain"));
		FilterRegistration springSecurityFilterReg = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		springSecurityFilterReg.addMappingForUrlPatterns(null, false, ALL);
		

		
		
		
		
	}

}