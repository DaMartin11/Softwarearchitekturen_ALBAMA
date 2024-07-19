package com.ALBAMA.productservice.productservice;

import com.ALBAMA.productservice.authentication.JwtRoleInterceptor;
import com.ALBAMA.productservice.authentication.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ProductserviceApplication {
	@Autowired
	private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer interceptorConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new JwtRoleInterceptor(environment, Role.ADMIN.role))
						.addPathPatterns("/**");
			}
		};
	}

}
