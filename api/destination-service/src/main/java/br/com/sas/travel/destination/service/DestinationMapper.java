package br.com.sas.travel.destination.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.destination.entity.DestinationEntity;
import br.com.sas.travel.destination.model.Destination;

@Mapper
public interface DestinationMapper {

	DestinationMapper INSTANCE = Mappers.getMapper(DestinationMapper.class);

	Destination map(DestinationEntity entity);

}