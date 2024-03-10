package br.com.sas.travel.tips.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TravelTip {

	private BigDecimal score;

	private BigDecimal rating;

	private String content;

}
