package br.com.sas.travel.flight.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Flight {

	private String code;

	private OffsetDateTime departure;

	private OffsetDateTime arrival;

	private Airport airport;
}
