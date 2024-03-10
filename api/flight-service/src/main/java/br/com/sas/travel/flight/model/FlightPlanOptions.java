package br.com.sas.travel.flight.model;

import java.util.List;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class FlightPlanOptions {

	private TravelPlanningCriteria criteria;

	private List<FlightPlan> options;

}
