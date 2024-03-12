package br.com.sas.travel.flight.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Flight {

	private String code;

	private LocalDateTime departure;

	private LocalDateTime arrival;

	private Airport departureAirport;

	private Airport arrivalAirport;

}
