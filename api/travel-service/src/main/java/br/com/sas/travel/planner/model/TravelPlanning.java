package br.com.sas.travel.planner.model;

import static br.com.sas.travel.planner.model.TravelDataType.ACCOMMODATION;
import static br.com.sas.travel.planner.model.TravelDataType.ACTIVITY;
import static br.com.sas.travel.planner.model.TravelDataType.CRITERIA;
import static br.com.sas.travel.planner.model.TravelDataType.DESTINATION;
import static br.com.sas.travel.planner.model.TravelDataType.FLIGHTPLAN;
import static br.com.sas.travel.planner.model.TravelDataType.OPTIMAL;
import static br.com.sas.travel.planner.model.TravelDataType.TRAVELTIP;

import br.com.sas.travel.accommodation.model.AccommodationOptions;
import br.com.sas.travel.activity.model.ActivityOptions;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.destination.model.DestinationOptions;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import br.com.sas.travel.tips.model.TravelTips;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TravelPlanning<T> {

	private TravelDataType type;

	private T data;

	public static <T extends TravelPlanningCriteria> TravelPlanning<T> criteria(T data) {
		return new TravelPlanning<>(CRITERIA, data);
	}

	public static <T extends DestinationOptions> TravelPlanning<T> destination(T data) {
		return new TravelPlanning<>(DESTINATION, data);
	}

	public static <T extends FlightPlanOptions> TravelPlanning<T> flight(T data) {
		return new TravelPlanning<>(FLIGHTPLAN, data);
	}

	public static <T extends AccommodationOptions> TravelPlanning<T> accommodation(T data) {
		return new TravelPlanning<>(ACCOMMODATION, data);
	}

	public static <T extends ActivityOptions> TravelPlanning<T> activity(T data) {
		return new TravelPlanning<>(ACTIVITY, data);
	}

	public static <T extends TravelTips> TravelPlanning<T> tip(T data) {
		return new TravelPlanning<>(TRAVELTIP, data);
	}

	public static <T extends OptimalTravelPlanning> TravelPlanning<T> optimal(T data) {
		return new TravelPlanning<>(OPTIMAL, data);
	}

}
