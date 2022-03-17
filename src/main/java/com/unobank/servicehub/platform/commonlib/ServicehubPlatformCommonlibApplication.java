package com.unobank.servicehub.platform.commonlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ServicehubPlatformCommonlibApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServicehubPlatformCommonlibApplication.class, args);
	}
}
