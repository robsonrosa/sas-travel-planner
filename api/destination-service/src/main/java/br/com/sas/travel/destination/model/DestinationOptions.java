package br.com.sas.travel.destination.model;

import java.util.List;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DestinationOptions {

	private TravelPlanningCriteria criteria;

	private List<Destination> options;

}
