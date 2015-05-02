package me.dec7.marker.tmp.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;

import me.dec7.marker.config.core.WebConfig;
import me.dec7.marker.config.marker.AppConfig;
import me.dec7.marker.filter.SiteMeshFilter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MarkerSecurityInitializer {
//	extends AbstractAnnotationConfigDispatcherServletInitializer {
//}
//	
//	private static final String ALL = "/*";
//	private static final String ROOT = "/";
//	private static final String MARKER = "marker";
//	private static final String UTF8 = "UTF-8";
//	private static final String TRUE = "true";
//	
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//
//		return new Class[]{AppConfig.class};
//	}
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//		
//		return new Class[]{WebConfig.class};
//	}
//	
//	@Override
//	protected String[] getServletMappings() {
//		
//		return new String[]{ROOT};
//	}
//	
//	@Override
//	protected String getServletName() {
//		
//		return MARKER;
//	}
//	@Override
//	protected Dynamic registerServletFilter(ServletContext context, Filter filter) {
//		
//		context.addFilter("sitemeshFilter", SiteMeshFilter.class).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, ALL);
//		Dynamic charEncodingFilterReg = context.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
//		charEncodingFilterReg.setInitParameter("encoding", UTF8);
//		charEncodingFilterReg.setInitParameter("forceEncoding", TRUE);
//		charEncodingFilterReg.addMappingForUrlPatterns(null, false, ALL);
//		
//		Dynamic springSecurityFilterReg = context.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//		springSecurityFilterReg.addMappingForUrlPatterns(null, false, ALL);
//		
//		
//		Dynamic openEntityManagerInViewFilterReg = context.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);
//		openEntityManagerInViewFilterReg.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, ALL);
//		
//		return openEntityManagerInViewFilterReg;
//	}
//	
	
	

	

}