package net.luvina.insurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
/**
 * ApplicationStart.java
 * @author nguyenthinhphu
 * Start Application
 */
public class ApplicationStart {

	/**
	 * Main
	 * @param args
	 * Run Application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationStart.class, args);

	}
	
	
}
