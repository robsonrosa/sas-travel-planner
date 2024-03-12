package br.com.sas.travel.destination.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Destination {

	private String code;

	private Double score;

	private Double rating;

	private String destination;

}
