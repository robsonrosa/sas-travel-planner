package br.com.sas.travel.destination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class DestinationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DestinationServiceApplication.class, args);
	}

}
