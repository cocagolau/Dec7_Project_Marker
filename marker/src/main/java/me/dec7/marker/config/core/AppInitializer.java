package me.dec7.marker.config.core;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

import me.dec7.marker.config.marker.AppConfig;
import me.dec7.marker.filter.SiteMeshFilter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import freemarker.core.Environment;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final String ROOT = "/";

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[] { AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { ROOT };
	}

	@Override
	protected Filter[] getServletFilters() {
		
		container.addFilter("sitemeshFilter", SiteMeshFilter.class).addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD), false, ALL);
		FilterRegistration charEncodingfilterReg = container.addFilter("characterEncodingFilter", CharacterEncodingFilter.class);
		charEncodingfilterReg.setInitParameter("encoding", UTF8);
		charEncodingfilterReg.setInitParameter("forceEncoding", TRUE);
		charEncodingfilterReg.addMappingForUrlPatterns(null, false, ALL);
		
		FilterRegistration springSecurityFilterChain = container.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, ALL);
		
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		Environment env = new Environment(template, rootDataModel, out)
		characterEncodingFilter.setEnvironment(environment);
		
		return new Filter[] {
				new OpenEntityManagerInViewFilter()
			};
	}

}
