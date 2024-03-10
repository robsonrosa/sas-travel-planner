package br.com.sas.travel.activity.model;

import java.util.List;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.Builder;

@Builder(toBuilder = true)
public class ActivityOptions {

	private TravelPlanningCriteria criteria;

	private List<Activity> options;

}
