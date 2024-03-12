package br.com.sas.travel.flight.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.flight.entity.AirportEntity;
import br.com.sas.travel.flight.model.Airport;

@Mapper
public interface AirportMapper {

	AirportMapper INSTANCE = Mappers.getMapper(AirportMapper.class);

	Airport map(AirportEntity entity);

}