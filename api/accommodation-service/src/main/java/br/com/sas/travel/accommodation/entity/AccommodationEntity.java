package br.com.sas.travel.accommodation.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import br.com.sas.travel.accommodation.api.model.AccommodationType;
import lombok.Data;

@Data
@Container(containerName = "accommodations")
public class AccommodationEntity {

	@Id
	private String code;

	private String name;

	private String address;

	private AccommodationType type;

	private Double rating;

}
