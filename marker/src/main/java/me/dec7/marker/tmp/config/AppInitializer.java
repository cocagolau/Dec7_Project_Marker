package me.dec7.marker.tmp.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

import me.dec7.marker.config.core.WebConfig;
import me.dec7.marker.config.marker.AppConfig;
import me.dec7.marker.filter.SiteMeshFilter;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import freemarker.core.Environment;

public class AppInitializer {
//	extends AbstractAnnotationConfigDispatcherServletInitializer {
//}
//
//	private static final String ROOT = "/";
//
//	@Override
//	protected Class<?>[] getRootConfigClasses() {
//		
//		return new Class<?>[] { AppConfig.class};
//	}
//
//	@Override
//	protected Class<?>[] getServletConfigClasses() {
//
//		return new Class<?>[] { WebConfig.class };
//	}
//
//	@Override
//	protected String[] getServletMappings() {
//		return new String[] { ROOT };
//	}
//
//	@Override
//	protected Filter[] getServletFilters() {
//		return null;
//	}

}
