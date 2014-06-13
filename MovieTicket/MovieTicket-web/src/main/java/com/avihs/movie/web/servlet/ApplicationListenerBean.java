package com.avihs.movie.web.servlet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationListenerBean implements ApplicationListener {

	public void onApplicationEvent(ApplicationEvent applicationEvent) {

		if (applicationEvent instanceof ContextRefreshedEvent) {
			ApplicationContext applicationContext = ((ContextRefreshedEvent) applicationEvent)
					.getApplicationContext();
			System.out.println("applicationContext = " + applicationContext);
		}
	}

}