package br.com.sas.travel.criteria.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Service
public class CriteriaService {

	public Mono<TravelPlanningCriteria> search(String searchTerm) {
		var factory = new PodamFactoryImpl();
		return Mono.just(factory.manufacturePojo(TravelPlanningCriteria.class)
				.toBuilder()
				.searchTerm(searchTerm)
				.build());
	}

}
