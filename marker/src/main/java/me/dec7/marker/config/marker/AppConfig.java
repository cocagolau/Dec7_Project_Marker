package me.dec7.marker.config.marker;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@Import({ PersistenceConfig.class, SecurityConfig.class })
@ComponentScan(
		basePackages = {
				"**.service",
		},
		excludeFilters={
				@Filter(type = FilterType.ANNOTATION, value = Configuration.class)
		}
)
@EnableAsync
public class AppConfig {

	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(10);

		return executor;
	}
	
}