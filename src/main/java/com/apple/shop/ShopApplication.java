package com.apple.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
		var test = new Friend("Ryan", 10);
		System.out.println(test.name);

		var object = new Test();
		object.setAge(28);
		object.setName("Ryan");
		object.한살더하기();
		object.나이설정(20);
		System.out.println(object.getAge());




	}
}

class Friend {
	String name = "Kim";
	Integer age = 20;
	Friend(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
}