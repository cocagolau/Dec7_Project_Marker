package me.dec7.marker.config.marker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"**.config.marker"})
@Import({ SecurityConfig.class })
public class MarkerConfig {
	
}