package com.am.news;

import com.am.news.configurations.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@Import({WebSecurityConfig.class})
@ComponentScan(basePackages="com")
public class NewsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NewsApplication.class, args);
	}
}
