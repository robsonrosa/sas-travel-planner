package br.com.sas.travel.activity.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.activity.entity.ActivityEntity;
import br.com.sas.travel.activity.model.Activity;

@Mapper
public interface ActivityMapper {

	ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

	Activity map(ActivityEntity entity);

}