package com.example.demo;

//import lombok.Setter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@Setter
@Getter
@RequiredArgsConstructor
public class HelloLombok {
	// private String hello;
	// private int lombok;
	
	private final String hello;
	private final int lombok;
	
	public static void main(String[] arg) {
		// HelloLombok helloLombok = new HelloLombok();
		// helloLombok.setHello("헬로");
		// helloLombok.setLombok(5);
		
		HelloLombok helloLombok = new HelloLombok("헬로", 5);
		
		System.out.println(helloLombok.getHello());
		System.out.println(helloLombok.getLombok());		
	}
	
}
