package com.example.i18n.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.example.i18n.resolver.UrlLocaleResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	// To solver URL like:
	// /SomeContextPath/en/login2
	// /SomeContextPath/vi/login2
	// /SomeContextPath/ja/login2
	@Bean(name="localeResolver")
	public LocaleResolver getLocaleResolver() {
		LocaleResolver resolver = new UrlLocaleResolver();
		return resolver;
//		System.out.println("LocaleResolver getLocaleResolver");
//		CookieLocaleResolver resolver = new CookieLocaleResolver();
//		resolver.setCookieDomain("myAppLocaleCookie");
//		// 10 minutes
//		resolver.setCookieMaxAge(60*10);
//		return resolver;
	}
	
	@Bean(name="messageSource")
	public MessageSource getMessageResource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		
		// Read i18n/messages_xxx.properties file
		// For example: i18n/messages_en.properties
		messageResource.setBasename("classpath:i18n/messages");
		messageResource.setDefaultEncoding("UTF-8");
		return messageResource;
	}
		
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		
		registry.addInterceptor(localeInterceptor).addPathPatterns("/en/*", "/fr/*", "/vi/*");
	}

}
