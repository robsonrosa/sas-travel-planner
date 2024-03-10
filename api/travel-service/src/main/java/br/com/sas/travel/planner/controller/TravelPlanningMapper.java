package br.com.sas.travel.planner.controller;

import static br.com.sas.travel.planner.controller.DefaultMapper.MAPPER;
import static java.util.Optional.ofNullable;

import org.springframework.stereotype.Component;

import br.com.sas.travel.accommodation.model.AccommodationOptions;
import br.com.sas.travel.activity.model.ActivityOptions;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.destination.model.DestinationOptions;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import br.com.sas.travel.planner.api.model.AccommodationOptionsResponse;
import br.com.sas.travel.planner.api.model.ActivityOptionsResponse;
import br.com.sas.travel.planner.api.model.DestinationOptionsResponse;
import br.com.sas.travel.planner.api.model.FlightPlanOptionsResponse;
import br.com.sas.travel.planner.api.model.OptimalTravelPlanningResponse;
import br.com.sas.travel.planner.api.model.TravelPlanningCriteriaResponse;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.planner.api.model.TravelTipsResponse;
import br.com.sas.travel.planner.model.OptimalTravelPlanning;
import br.com.sas.travel.planner.model.TravelPlanning;
import br.com.sas.travel.tips.model.TravelTips;
import reactor.core.publisher.Flux;

@Component
public class TravelPlanningMapper {

	public Flux<TravelPlanningResponse> apply(Flux<TravelPlanning<?>> model) {
		return model.map(this::toResponse);
	}

	public TravelPlanningResponse toResponse(TravelPlanning<?> model) {
		return ofNullable(model)
				.map(this::mapResponse)
				.orElse(null);
	}

	private TravelPlanningResponse mapResponse(TravelPlanning<?> model) {
		return switch (model.getType()) {
			case CRITERIA:
				yield mapCriteria(model);
			case DESTINATION:
				yield mapDestination(model);
			case FLIGHTPLAN:
				yield mapFlightPlan(model);
			case ACCOMMODATION:
				yield mapAccommodation(model);
			case ACTIVITY:
				yield mapActivity(model);
			case TRAVELTIP:
				yield mapTravelTips(model);
			case OPTIMAL:
				yield mapOptimal(model);
		};
	}

	private static TravelPlanningCriteriaResponse mapCriteria(TravelPlanning<?> model) {
		return new TravelPlanningCriteriaResponse()
				.data(MAPPER.map((TravelPlanningCriteria) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

	private static DestinationOptionsResponse mapDestination(TravelPlanning<?> model) {
		return new DestinationOptionsResponse()
				.data(MAPPER.map((DestinationOptions) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

	private static FlightPlanOptionsResponse mapFlightPlan(TravelPlanning<?> model) {
		return new FlightPlanOptionsResponse()
				.data(MAPPER.map((FlightPlanOptions) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

	private static AccommodationOptionsResponse mapAccommodation(TravelPlanning<?> model) {
		return new AccommodationOptionsResponse()
				.data(MAPPER.map((AccommodationOptions) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

	private static ActivityOptionsResponse mapActivity(TravelPlanning<?> model) {
		return new ActivityOptionsResponse()
				.data(MAPPER.map((ActivityOptions) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

	private static TravelTipsResponse mapTravelTips(TravelPlanning<?> model) {
		return new TravelTipsResponse()
				.data(MAPPER.map((TravelTips) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

	private static OptimalTravelPlanningResponse mapOptimal(TravelPlanning<?> model) {
		return new OptimalTravelPlanningResponse()
				.data(MAPPER.map((OptimalTravelPlanning) model.getData()))
				.type(MAPPER.map(model.getType()));
	}

}
