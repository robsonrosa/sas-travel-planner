package br.com.sas.travel.flight.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Data
@Container(containerName = "airports")
public class AirportEntity {

	@Id
	private String code;

	private String name;

	private String address;

	private String location;

}
