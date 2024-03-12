package br.com.sas.travel.activity.entity;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import br.com.sas.travel.activity.model.ActivityDifficulty;
import br.com.sas.travel.activity.model.ActivityType;
import lombok.Data;

@Data
@Container(containerName = "activities")
public class ActivityEntity {

	@Id
	private String code;

	private String name;

	private String description;

	private String address;

	private ActivityType type;

	private ActivityDifficulty difficulty;

	private Double rating;

}
