package java8features;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.function.Consumer;

public class ConsumerComposer {

public static void main(String[] args) {
	try {
		PrintWriter writer = new PrintWriter("filename.txt");
		
		Consumer<String> logger = writer::println;
		Consumer<String> screener = System.out::println;
		Consumer<String> both = screener.andThen(logger);
		
		both.accept("Program started");
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}	
}
