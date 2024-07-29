package com.studytonight.community;
import org.springframework.stereotype.Component;
@Component
public class Manager implements Employee{
	
	public void doWork() {
		
		System.out.println("Manage the branch office");
	}
}