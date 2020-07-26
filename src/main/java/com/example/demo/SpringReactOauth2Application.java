package com.example.demo;

import java.security.Principal;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class SpringReactOauth2Application {
	
	@GetMapping("/")
	public String message(Principal principal) {
		Map<String, Object> data = (Map<String, Object>) ((OAuth2Authentication) principal)
				.getUserAuthentication()
				.getDetails();
		
		return "Hi " + data.get("name");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringReactOauth2Application.class, args);
	}
//
//	@Bean
//	public FilterRegistrationBean<OAuth2ClientContextFilter> oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
//	 FilterRegistrationBean<OAuth2ClientContextFilter> registration = new FilterRegistrationBean<OAuth2ClientContextFilter>();
//	 registration.setFilter(filter);
//	 registration.setOrder(Ordered.HIGHEST_PRECEDENCE +1);
//	 return registration;
//	}
}
