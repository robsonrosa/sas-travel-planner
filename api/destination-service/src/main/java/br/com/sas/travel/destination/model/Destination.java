package br.com.sas.travel.destination.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Data
@Container(containerName = "destinations")
public class Destination {

	@Id
	private String code;

	private Double score;

	private Double rating;

	private String destination;

}
