package br.com.sas.travel.tips.service;

import static br.com.sas.travel.tips.service.RandomOrderComparator.comparator;
import static br.com.sas.travel.tips.service.RandomOrderComparator.predicate;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.tips.model.TravelTip;
import br.com.sas.travel.tips.model.TravelTips;
import br.com.sas.travel.tips.repository.TipsRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TipService {

	private final TipsRepository repository;

	public Mono<TravelTips> search(TravelPlanningCriteria criteria) {
		return searchBasedOnCriteria(criteria)
				.collectList()
				.map(tips -> TravelTips.builder()
						.criteria(criteria)
						.options(tips)
						.build());
	}

	private Flux<TravelTip> searchBasedOnCriteria(TravelPlanningCriteria criteria) {
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