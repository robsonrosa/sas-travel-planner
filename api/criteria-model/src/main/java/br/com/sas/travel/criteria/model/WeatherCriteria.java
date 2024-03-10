package br.com.sas.travel.criteria.model;

import java.util.List;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class WeatherCriteria {

	private TemperatureRange temperature;

	private List<String> conditions;

}
