package br.com.sas.travel.planner.controller;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.planner.api.model.AccommodationOptions;
import br.com.sas.travel.planner.api.model.ActivityOptions;
import br.com.sas.travel.planner.api.model.DestinationOptions;
import br.com.sas.travel.planner.api.model.FlightPlanOptions;
import br.com.sas.travel.planner.api.model.OptimalTravelPlanning;
import br.com.sas.travel.planner.api.model.TravelPlanningCriteria;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.planner.api.model.TravelTips;

@Mapper
public interface DefaultMapper {
	DefaultMapper MAPPER = Mappers.getMapper(DefaultMapper.class);

	TravelPlanningResponse.TypeEnum map(br.com.sas.travel.planner.model.TravelDataType type);

	TravelPlanningCriteria map(br.com.sas.travel.criteria.model.TravelPlanningCriteria criteria);

	DestinationOptions map(br.com.sas.travel.destination.model.DestinationOptions destinations);

	FlightPlanOptions map(br.com.sas.travel.flight.model.FlightPlanOptions data);

	AccommodationOptions map(br.com.sas.travel.accommodation.model.AccommodationOptions data);

	ActivityOptions map(br.com.sas.travel.activity.model.ActivityOptions data);

	TravelTips map(br.com.sas.travel.tips.model.TravelTips data);

	OptimalTravelPlanning map(br.com.sas.travel.planner.model.OptimalTravelPlanning data);

}