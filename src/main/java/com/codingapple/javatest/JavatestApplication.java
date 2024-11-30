package com.codingapple.javatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;

@SpringBootApplication
public class JavatestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavatestApplication.class, args);

		// var test = new Test();
		// System.out.println("==========="+test.name);
		// test.main();

		var friend = new Friend("choi");
	}

}

class Test {
	String name = "Kim";
	void main() {
		System.out.println("Hello World");
	}
}

class Friend {
	String name = "Lee";
	int age = 35;

	// class 실행될 때 자동으로 생성됨 constructor
	Friend(String name) {
		System.out.println("Friend constructor ======== "+this.name);
		this.name = name;
		System.out.println("Friend constructor ======== "+this.name);
	}
}