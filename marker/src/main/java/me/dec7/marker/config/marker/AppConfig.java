package me.dec7.marker.config.marker;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SecurityConfig.class, PersistenceConfig.class })
public class AppConfig {
	
}