package me.dec7.marker.config.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = {
				"me.dec7.marker"
		},
		excludeFilters={
				@Filter(type = FilterType.ANNOTATION, value = Configuration.class)
		}
)
@Import({ WebSocketConfig.class })
@EnableGlobalMethodSecurity(prePostEnabled=true, securedEnabled = true)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setCache(true);
		viewResolver.setSuffix(".ftl");
		viewResolver.setContentType("text/html; charset=UTF-8");
		viewResolver.setExposeSpringMacroHelpers(true);

		return viewResolver;
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfig() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("/WEB-INF/pages");

		return configurer;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/WEB-INF/static/favicon/favicon.ico");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/static/images/");
		registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/scripts/");
		registry.addResourceHandler("/stylesheets/**").addResourceLocations("/WEB-INF/stylesheets/");
		registry.addResourceHandler("/templates/**").addResourceLocations("/WEB-INF/templates/");
	}

}