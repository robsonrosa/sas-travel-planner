package br.com.sas.travel.flight.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Data
@Container(containerName = "flights")
public class Flight {

	@Id
	private String code;

	private LocalDateTime departure;

	private LocalDateTime arrival;

	private Airport departureAirport;

	private Airport arrivalAirport;

}
