package br.com.sas.travel.accommodation.service;

import static br.com.sas.travel.accommodation.service.RandomOrderComparator.comparator;
import static br.com.sas.travel.accommodation.service.RandomOrderComparator.predicate;
import static br.com.sas.travel.accommodation.service.RandomOrderComparator.score;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.sas.travel.accommodation.entity.AccommodationEntity;
import br.com.sas.travel.accommodation.model.Accommodation;
import br.com.sas.travel.accommodation.model.AccommodationOptions;
import br.com.sas.travel.accommodation.repository.AccommodationRepository;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccommodationService {

	private final AccommodationRepository repository;

	public Mono<AccommodationOptions> search(TravelPlanningCriteria criteria) {
		return searchBasedOnCriteria(criteria)
				.map(AccommodationMapper.INSTANCE::map)
				.map(applyScore(criteria))
				.collectList()
				.map(accommodations -> AccommodationOptions.builder()
						.criteria(criteria)
						.options(accommodations)
						.build());
	}

	private Function<Accommodation, Accommodation> applyScore(TravelPlanningCriteria criteria) {
		return accommodation -> accommodation.toBuilder().score(score()).build();
	}

	private Flux<AccommodationEntity> searchBasedOnCriteria(TravelPlanningCriteria criteria) {
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
