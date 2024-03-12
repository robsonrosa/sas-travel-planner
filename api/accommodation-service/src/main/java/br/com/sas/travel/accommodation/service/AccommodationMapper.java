package br.com.sas.travel.accommodation.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.accommodation.entity.AccommodationEntity;
import br.com.sas.travel.accommodation.model.Accommodation;

@Mapper
public interface AccommodationMapper {

	AccommodationMapper INSTANCE = Mappers.getMapper(AccommodationMapper.class);

	Accommodation map(AccommodationEntity entity);

}