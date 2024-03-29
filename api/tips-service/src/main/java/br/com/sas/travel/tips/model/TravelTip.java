package br.com.sas.travel.tips.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import com.azure.spring.data.cosmos.core.mapping.Container;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class TravelTip {

	private String id;

	private Double score;

	private Double rating;

	private String content;

	private String destinationCode;

}
