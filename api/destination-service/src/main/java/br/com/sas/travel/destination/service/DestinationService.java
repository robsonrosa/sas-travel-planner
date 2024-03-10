package br.com.sas.travel.destination.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.destination.model.DestinationOptions;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Service
public class DestinationService {

	public Mono<DestinationOptions> search(TravelPlanningCriteria criteria) {
		var factory = new PodamFactoryImpl();
		return Mono.just(factory.manufacturePojo(DestinationOptions.class)
				.toBuilder()
				.criteria(criteria)
				.build());
	}
}
