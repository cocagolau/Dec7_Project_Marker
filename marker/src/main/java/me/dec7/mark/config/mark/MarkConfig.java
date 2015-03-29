package me.dec7.mark.config.mark;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"**.config.mark"})
@Import({ SecurityConfig.class })
public class MarkConfig {
	
}