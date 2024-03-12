package br.com.sas.travel.tips.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Data
@Container(containerName = "tips")
public class TravelTipEntity {

	@Id
	private String id;

	private String content;

	private String destinationCode;

	private Double rating;

}
