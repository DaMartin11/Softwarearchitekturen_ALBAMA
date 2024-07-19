package com.ALBAMA.cart_service.cartservice;

import com.ALBAMA.cart_service.authentication.JwtRoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CartServiceApplication {


	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(CartServiceApplication.class, args);
	}


	@Bean
	public WebMvcConfigurer interceptorConfiguration() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(new JwtRoleInterceptor(environment))
						.addPathPatterns("/**");
			}

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000/")
						.allowCredentials(true)
						.allowedHeaders("*")
						.allowedMethods("*");
			}

		};
	}

}
