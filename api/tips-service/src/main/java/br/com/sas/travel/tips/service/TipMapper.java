package br.com.sas.travel.tips.service;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sas.travel.tips.entity.TravelTipEntity;
import br.com.sas.travel.tips.model.TravelTip;

@Mapper
public interface TipMapper {

	TipMapper INSTANCE = Mappers.getMapper(TipMapper.class);

	TravelTip map(TravelTipEntity entity);

}