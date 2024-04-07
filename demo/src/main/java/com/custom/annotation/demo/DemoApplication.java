package com.custom.annotation.demo;

import com.custom.annotation.demo.models.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws Exception {

		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Hello");

		Person person = new Person("shamim", "sarker");
		person.initializeObject(person);
		System.out.println(person.toString());
		StringBuilder s1 = new StringBuilder("Arifa, I just want to say, 'I love You, let's get married and goto abroad.");
		System.out.println(s1.reverse());
	}

}
