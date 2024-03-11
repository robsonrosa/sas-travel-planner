package br.com.sas.travel.destination.service;

import static br.com.sas.travel.destination.service.RandomOrderComparator.comparator;
import static br.com.sas.travel.destination.service.RandomOrderComparator.predicate;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.destination.model.Destination;
import br.com.sas.travel.destination.model.DestinationOptions;
import br.com.sas.travel.destination.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DestinationService {

	private final DestinationRepository repository;

	public Mono<DestinationOptions> search(TravelPlanningCriteria criteria) {

		return searchBasedOnCriteria(criteria)
				.collectList()
				.map(destinations -> DestinationOptions.builder()
						.criteria(criteria)
						.options(destinations)
						.build());
	}

	// TODO: implement using criteria
	public Flux<Destination> searchBasedOnCriteria(TravelPlanningCriteria criteria) {
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
