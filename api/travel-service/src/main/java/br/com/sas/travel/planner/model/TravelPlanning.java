package br.com.sas.travel.planner.model;

import static org.openapitools.model.TravelPlanningResponse.TypeEnum.ACCOMMODATION;
import static org.openapitools.model.TravelPlanningResponse.TypeEnum.ACTIVITY;
import static org.openapitools.model.TravelPlanningResponse.TypeEnum.CRITERIA;
import static org.openapitools.model.TravelPlanningResponse.TypeEnum.DESTINATION;
import static org.openapitools.model.TravelPlanningResponse.TypeEnum.FLIGHTPLAN;
import static org.openapitools.model.TravelPlanningResponse.TypeEnum.OPTIMAL;
import static org.openapitools.model.TravelPlanningResponse.TypeEnum.TRAVELTIP;

import org.openapitools.model.TravelPlanningResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TravelPlanning {

	private TravelPlanningResponse.TypeEnum type;

	private Object data;

	public static TravelPlanning criteria(Object data) {
		return new TravelPlanning(CRITERIA, data);
	}

	public static TravelPlanning destination(Object data) {
		return new TravelPlanning(DESTINATION, data);
	}

	public static TravelPlanning flight(Object data) {
		return new TravelPlanning(FLIGHTPLAN, data);
	}

	public static TravelPlanning accommodation(Object data) {
		return new TravelPlanning(ACCOMMODATION, data);
	}

	public static TravelPlanning activity(Object data) {
		return new TravelPlanning(ACTIVITY, data);
	}

	public static TravelPlanning tip(Object data) {
		return new TravelPlanning(TRAVELTIP, data);
	}

	public static TravelPlanning optimal(Object data) {
		return new TravelPlanning(OPTIMAL, data);
	}

}
