package br.com.sas.travel.planner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import br.com.sas.travel.accommodation.service.AccommodationService;
import br.com.sas.travel.activity.service.ActivityService;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.criteria.service.CriteriaService;
import br.com.sas.travel.destination.model.Destination;
import br.com.sas.travel.destination.service.DestinationService;
import br.com.sas.travel.flight.service.FlightService;
import br.com.sas.travel.planner.model.OptimalTravelPlanning;
import br.com.sas.travel.planner.model.TravelPlanning;
import br.com.sas.travel.tips.service.TipService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TravelService {

	private final CriteriaService criteriaService = new CriteriaService();
	private final DestinationService destinationService = new DestinationService();
	private final FlightService flightService = new FlightService();
	private final AccommodationService accommodationService = new AccommodationService();
	private final ActivityService activityService = new ActivityService();
	private final TipService tipService = new TipService();

	public Flux<TravelPlanning<?>> plan(String searchTerm) {
		return criteriaService.search(searchTerm)
				.flatMapMany(criteria -> planWith(criteria)
						.startWith(TravelPlanning.criteria(criteria)));
	}

	private Flux<TravelPlanning<?>> planWith(TravelPlanningCriteria criteria) {

		return destinationService.search(criteria)
				.flatMapMany(destinations -> {
					// TODO: get optimal by highest score
					var optimalDestination = destinations.getOptions().get(0);
					var criteriaWithDestination = criteria.withDestinationCode(optimalDestination.getCode());

					var partialData = List.of(
							Mono.just(TravelPlanning.destination(destinations)),
							flightService.search(criteriaWithDestination).map(TravelPlanning::flight),
							accommodationService.search(criteriaWithDestination).map(TravelPlanning::accommodation),
							activityService.search(criteriaWithDestination).map(TravelPlanning::activity),
							tipService.search(criteriaWithDestination).map(TravelPlanning::tip)
					);

					return Flux.merge(partialData)
							.collectList()
							.map(emitOptimalResult(optimalDestination))
							.flatMapMany(Flux::fromIterable);

				});
	}

	private static Function<List<TravelPlanning<?>>, ArrayList<TravelPlanning<?>>> emitOptimalResult(Destination optimalDestination) {
		return list -> {
			var concat = new ArrayList<>(list);
			// TODO: add other optimal
			concat.add(TravelPlanning.optimal(OptimalTravelPlanning.builder()
					.destination(optimalDestination)
					.build()));
			return concat;
		};
	}

}
