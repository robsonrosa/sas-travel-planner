package br.com.sas.travel.activity.service;

import static br.com.sas.travel.activity.service.RandomOrderComparator.comparator;
import static br.com.sas.travel.activity.service.RandomOrderComparator.predicate;
import static br.com.sas.travel.activity.service.RandomOrderComparator.score;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.sas.travel.activity.entity.ActivityEntity;
import br.com.sas.travel.activity.model.Activity;
import br.com.sas.travel.activity.model.ActivityOptions;
import br.com.sas.travel.activity.repository.ActivityRepository;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ActivityService {

	private final ActivityRepository repository;

	public Mono<ActivityOptions> search(TravelPlanningCriteria criteria) {
		return searchBasedOnCriteria(criteria)
				.map(ActivityMapper.INSTANCE::map)
				.map(applyScore(criteria))
				.collectList()
				.map(activities -> ActivityOptions.builder()
						.criteria(criteria)
						.options(activities)
						.build());
	}

	private Function<Activity, Activity> applyScore(TravelPlanningCriteria criteria) {
		return activity -> activity.toBuilder().score(score()).build();
	}

	private Flux<ActivityEntity> searchBasedOnCriteria(TravelPlanningCriteria criteria) {
		return repository
				.findAll()
				.collectList()
				.map(list -> list.stream()
						.sorted(comparator())
						.filter(predicate())
						.toList())
				.flatMapMany(Flux::fromIterable);
	}

}
