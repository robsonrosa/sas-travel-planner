package br.com.sas.travel.criteria.model;

import org.joda.time.Interval;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TravelPlanningCriteria {

	private final String searchTerm;

	private final WeatherCriteria weather;

	private final LanguageCriteria language;

	private final BudgetCriteria budget;

	private final LifestyleCriteria lifestyle;

	private final Interval departureDateRange;

	private final Interval arrivalDateRange;

	private final ActivityCriteria activity;

	private final CulturalCriteria culture;

	private final Boolean withPets;

	private final Boolean withChildren;

	private final String destinationCode;

	public TravelPlanningCriteria withDestinationCode(String destinationCode) {
		return this.toBuilder()
				.destinationCode(destinationCode)
				.build();
	}

}