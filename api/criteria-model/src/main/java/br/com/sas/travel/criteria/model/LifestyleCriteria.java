package br.com.sas.travel.criteria.model;

import java.util.List;

import lombok.Data;

@Data
public class LifestyleCriteria {

	private List<AccommodationType> accommodationPreferences;

	private List<FoodPreferencesEnum> foodPreferences;

}
