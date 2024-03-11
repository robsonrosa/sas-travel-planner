package br.com.sas.travel.activity.model;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Data;

@Data
@Container(containerName = "activities")
public class Activity {

	@Id
	private String code;

	private Double score;

	private Double rating;

	private String name;

	private String description;

	private String address;

	private ActivityType type;

	private ActivityDifficulty difficulty;

}
