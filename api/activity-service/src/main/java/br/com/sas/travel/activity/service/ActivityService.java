package br.com.sas.travel.activity.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.activity.model.ActivityOptions;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import reactor.core.publisher.Mono;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Service
public class ActivityService {

	public Mono<ActivityOptions> search(TravelPlanningCriteria criteria) {
		var factory = new PodamFactoryImpl();
		return Mono.just(factory.manufacturePojo(ActivityOptions.class)
				.toBuilder()
				.criteria(criteria)
				.build());
	}

}
