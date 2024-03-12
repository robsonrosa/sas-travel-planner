package br.com.sas.travel.activity.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Activity {

	private String code;

	private Double score;

	private Double rating;

	private String name;

	private String description;

	private String address;

	private ActivityType type;

	private ActivityDifficulty difficulty;

}
