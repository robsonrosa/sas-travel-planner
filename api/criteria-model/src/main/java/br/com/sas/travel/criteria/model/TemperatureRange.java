package br.com.sas.travel.criteria.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TemperatureRange {

	private BigDecimal min;

	private BigDecimal max;

}
