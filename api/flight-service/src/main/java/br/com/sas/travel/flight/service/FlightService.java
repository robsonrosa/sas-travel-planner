package br.com.sas.travel.flight.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Service
public class FlightService {

	public Mono<FlightPlanOptions> search(TravelPlanningCriteria criteria) {
		var factory = new PodamFactoryImpl();
		return Mono.just(factory.manufacturePojo(FlightPlanOptions.class)
				.toBuilder()
				.criteria(criteria)
				.build());
	}
}
