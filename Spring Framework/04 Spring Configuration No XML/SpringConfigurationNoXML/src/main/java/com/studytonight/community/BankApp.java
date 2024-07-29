package com.studytonight.community;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BankApp {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		Manager employee = context.getBean(Manager.class);
		employee.doWork();
		context.close();
	}
}