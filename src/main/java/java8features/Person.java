package java8features;

import java.util.Optional;

public class Person {

	private String name;
	private int age;
	private String password;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person(String name, int age, String password) {
		this.name = name;
		this.age = age;
		this.password = password;
	}

	public void prettyPrint() {
		System.out.println(name + " has " + age + " years old.");
	}

	@Override
	public String toString() {
		return "name: " + name + ". age: " + age + ".";
	}

	public Optional<String> getNameOptional() {
		return Optional.ofNullable(name);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Optional<Integer> getAge() {
		return Optional.ofNullable(age);
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Optional<String> getPassword() {
		return Optional.ofNullable(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
