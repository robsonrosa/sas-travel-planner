package br.com.sas.travel.accommodation.model;

import java.math.BigDecimal;

import br.com.sas.travel.accommodation.api.model.AccommodationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Accommodation {

	private BigDecimal score;

	private BigDecimal rating;

	private String code;

	private String name;

	private String address;

	private AccommodationType type;

}
