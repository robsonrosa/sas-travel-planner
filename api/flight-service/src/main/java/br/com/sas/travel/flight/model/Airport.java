package br.com.sas.travel.flight.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Airport {

	private String code;

	private String name;

	private String address;

	private String location;

}
