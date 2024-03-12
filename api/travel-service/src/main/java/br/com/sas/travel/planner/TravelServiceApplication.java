package br.com.sas.travel.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FullyQualifiedAnnotationBeanNameGenerator;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication(
		scanBasePackages = {
				"br.com.sas.travel.planner",
				"br.com.sas.travel.criteria",
				"br.com.sas.travel.destination",
				"br.com.sas.travel.flight",
				"br.com.sas.travel.accommodation",
				"br.com.sas.travel.activity",
				"br.com.sas.travel.tips",
		},
		nameGenerator = FullyQualifiedAnnotationBeanNameGenerator.class)
public class TravelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelServiceApplication.class, args);
	}

}
