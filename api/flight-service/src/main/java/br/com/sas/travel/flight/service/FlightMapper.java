package br.com.sas.travel.flight.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.flight.entity.FlightEntity;
import br.com.sas.travel.flight.model.Airport;
import br.com.sas.travel.flight.model.Flight;

@Mapper
public interface FlightMapper {

	FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

	@Mapping(target = "code", source = "entity.code")
	@Mapping(target = "departureAirport", expression = "java(departureAirport)")
	@Mapping(target = "arrivalAirport", expression = "java(arrivalAirport)")
	Flight map(FlightEntity entity, Airport departureAirport, Airport arrivalAirport);

}