package br.com.sas.travel.accommodation.model;

import java.util.List;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.Builder;

@Builder(toBuilder = true)
public class AccommodationOptions {

	private TravelPlanningCriteria criteria;

	private List<Accommodation> options;

}
