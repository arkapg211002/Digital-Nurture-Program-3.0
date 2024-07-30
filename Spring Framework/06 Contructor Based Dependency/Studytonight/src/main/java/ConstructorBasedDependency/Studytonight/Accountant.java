package ConstructorBasedDependency.Studytonight;

import org.springframework.stereotype.Component;
@Component
public class Accountant implements Employee{
	
	public Accountant() {
		System.out.println("Inside Accountant Constructor");
	}
	public void doWork() {	
		System.out.println("Audit the accounts...");
	}
}