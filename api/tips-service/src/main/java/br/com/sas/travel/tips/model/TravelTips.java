package br.com.sas.travel.tips.model;

import java.util.List;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.Builder;

@Builder(toBuilder = true)
public class TravelTips {

	private TravelPlanningCriteria criteria;

	private List<TravelTip> options;

}
