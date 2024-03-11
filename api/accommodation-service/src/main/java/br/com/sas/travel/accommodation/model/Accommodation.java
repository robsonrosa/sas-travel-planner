package br.com.sas.travel.accommodation.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import br.com.sas.travel.accommodation.api.model.AccommodationType;
import lombok.Builder;
import lombok.Data;

@Data
@Container(containerName = "accommodations")
public class Accommodation {

	@Id
	private String code;

	private Double score;

	private Double rating;

	private String name;

	private String address;

	private AccommodationType type;

}
