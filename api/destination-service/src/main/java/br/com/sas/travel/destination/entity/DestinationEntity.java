package br.com.sas.travel.destination.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Data
@Container(containerName = "destinations")
public class DestinationEntity {

	@Id
	private String code;

	private Double rating;

	private String destination;

}
