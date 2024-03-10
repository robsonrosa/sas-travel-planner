package br.com.sas.travel.accommodation.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.accommodation.model.AccommodationOptions;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Service
public class AccommodationService {

	public Mono<AccommodationOptions> search(TravelPlanningCriteria criteria) {
		var factory = new PodamFactoryImpl();
		return Mono.just(factory.manufacturePojo(AccommodationOptions.class)
				.toBuilder()
				.criteria(criteria)
				.build());
	}

}
