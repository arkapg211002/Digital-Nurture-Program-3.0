package com.studytonight.community;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class App {

	private static ApplicationContext context;
	public static void main(String args[]) throws Exception
	{
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		Employee e = (Employee)context.getBean("employee");
		System.out.println("Name : "+e.getName());
		System.out.println("ID : "+e.getEmpid());
		
	}
}
