package com.avihs.movie.core.properties;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class CustomPropertyPlaceholderConfigurerImpl extends
		PropertyPlaceholderConfigurer {

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		// TODO Auto-generated method stub
//		org.hibernate.service.jta.platform.spi.JtaPlatform o=null;
		return super.resolvePlaceholder(placeholder, props);
		
	}
}