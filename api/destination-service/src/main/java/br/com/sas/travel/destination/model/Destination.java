package br.com.sas.travel.destination.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Destination {

	private BigDecimal score;

	private BigDecimal rating;

	private String destination;

	private String code;

}
