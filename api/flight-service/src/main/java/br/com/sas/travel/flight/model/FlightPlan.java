package br.com.sas.travel.flight.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightPlan {

	private Double score;

	private Double rating;

	private Flight returnFlight;

	private Flight outwardFlight;
}
