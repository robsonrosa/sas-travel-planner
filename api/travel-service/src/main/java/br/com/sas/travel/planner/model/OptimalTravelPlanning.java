package br.com.sas.travel.planner.model;

import java.util.List;

import br.com.sas.travel.accommodation.model.Accommodation;
import br.com.sas.travel.activity.model.Activity;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.destination.model.Destination;
import br.com.sas.travel.flight.model.FlightPlan;
import br.com.sas.travel.tips.model.TravelTip;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptimalTravelPlanning {

	private TravelPlanningCriteria criteria;

	private Destination destination;

	private FlightPlan flightPlan;

	private Accommodation accommodation;

	private List<Activity> activities;

	private List<TravelTip> tips;

}
