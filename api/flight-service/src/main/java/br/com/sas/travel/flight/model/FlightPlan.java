package br.com.sas.travel.flight.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class FlightPlan {

	private BigDecimal score;

	private BigDecimal rating;

	private Flight returnFlight;

	private Flight outwardFlight;
}
