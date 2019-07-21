package com.hsjjc.config.errorpage;

import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
public class ErrorPageConfig {
	@Bean
	public ErrorPageRegistrar errorPageRegistrar(){
		return new MyErrorPageRegistrar();
	}
}
