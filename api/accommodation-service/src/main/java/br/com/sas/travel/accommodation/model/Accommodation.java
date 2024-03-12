package br.com.sas.travel.accommodation.model;

import br.com.sas.travel.accommodation.api.model.AccommodationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Accommodation {

	private String code;

	private Double score;

	private Double rating;

	private String name;

	private String address;

	private AccommodationType type;

}
