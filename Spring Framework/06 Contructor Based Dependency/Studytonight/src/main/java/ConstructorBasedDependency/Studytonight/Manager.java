package ConstructorBasedDependency.Studytonight;

import org.springframework.stereotype.Component;

@Component
public class Manager implements Employee{
	
	Accountant accountant;
	
	public Manager(Accountant accountant) {
		System.out.println("manager constructor");
		this.accountant = accountant;
	}
	public void doWork() {
		System.out.println("Manage the branch office");
	}
	
	public void callMetting() {
		accountant.doWork();
	}
}