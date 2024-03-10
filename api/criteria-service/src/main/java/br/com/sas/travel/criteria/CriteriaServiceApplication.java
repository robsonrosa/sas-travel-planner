package br.com.sas.travel.criteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class CriteriaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriteriaServiceApplication.class, args);
	}

}
