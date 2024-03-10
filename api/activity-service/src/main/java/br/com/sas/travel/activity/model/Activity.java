package br.com.sas.travel.activity.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Activity {

	private BigDecimal score;

	private BigDecimal rating;

	private String code;

	private String name;

	private String description;

	private String address;

	private ActivityType type;

	private ActivityDifficulty difficulty;

}
