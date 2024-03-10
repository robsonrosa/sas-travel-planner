package br.com.sas.travel.tips.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.tips.model.TravelTips;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Service
public class TipService {

	public Mono<TravelTips> search(TravelPlanningCriteria criteria) {
		var factory = new PodamFactoryImpl();
		return Mono.just(factory.manufacturePojo(TravelTips.class)
				.toBuilder()
				.criteria(criteria)
				.build());
	}

}
