package java8features;

import java.util.ArrayList;
import java.util.List;

public class Team {

	String name;
	List<Person> people = new ArrayList<>();

	Team(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Team: " + name + ". [People:" + people + "]";
	}
}
