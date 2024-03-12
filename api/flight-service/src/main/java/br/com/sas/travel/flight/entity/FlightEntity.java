package br.com.sas.travel.flight.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import br.com.sas.travel.flight.model.Airport;
import lombok.Data;

@Data
@Container(containerName = "flights")
public class FlightEntity {

	@Id
	private String code;

	private LocalDateTime departure;

	private LocalDateTime arrival;

	private String departureAirportCode;

	private String arrivalAirportCode;

}
