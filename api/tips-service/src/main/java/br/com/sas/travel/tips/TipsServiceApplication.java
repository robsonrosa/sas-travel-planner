package br.com.sas.travel.tips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;

@SpringBootApplication(nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class TipsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TipsServiceApplication.class, args);
	}

}
