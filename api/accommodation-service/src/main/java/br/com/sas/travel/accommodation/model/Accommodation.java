package br.com.sas.travel.accommodation.model;

import java.math.BigDecimal;

import org.openapitools.model.AccommodationType;

import lombok.Builder;

@Builder
public class Accommodation {

	private BigDecimal score;

	private BigDecimal rating;

	private String code;

	private String name;

	private String address;

	private AccommodationType type;

}
