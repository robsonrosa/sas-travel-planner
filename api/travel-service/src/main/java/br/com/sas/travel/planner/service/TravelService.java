package br.com.sas.travel.planner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.sas.travel.accommodation.service.AccommodationService;
import br.com.sas.travel.activity.service.ActivityService;
import br.com.sas.travel.criteria.service.CriteriaService;
import br.com.sas.travel.destination.service.DestinationService;
import br.com.sas.travel.flight.service.FlightService;
import br.com.sas.travel.planner.model.TravelPlanning;
import br.com.sas.travel.tips.service.TipService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TravelService {

	private final CriteriaService criteriaService;
	private final DestinationService destinationService;
	private final FlightService flightService;
	private final AccommodationService accommodationService;
	private final ActivityService activityService;
	private final TipService tipService;

	public Flux<TravelPlanning> plan(String searchTerm) {
		return criteriaService.search(searchTerm)
				.flatMapMany(criteria -> {
					var criteriaResponse = TravelPlanning.criteria(criteria);

					return destinationService.search(criteria)
							.flatMapMany(destinations -> {
								var optimalDestination = destinations.getOptions().get(0);
								var criteriaWithDestination = criteria.withDestinationCode(optimalDestination.getCode());

								var partialData = List.of(
										flightService.search(criteriaWithDestination).map(TravelPlanning::flight),
										accommodationService.search(criteriaWithDestination).map(TravelPlanning::accommodation),
										activityService.search(criteriaWithDestination).map(TravelPlanning::activity),
										tipService.search(criteriaWithDestination).map(TravelPlanning::tip)
								);

								return Flux.merge(partialData)
										.then(Mono.just(TravelPlanning.optimal(optimalDestination)));

							}).startWith(criteriaResponse);
				});
	}

	//	public Flux<TravelPlanningResponse> plan(String searchTerm) {
	//		return Flux.create(fluxSink -> {
	//
	//		});
	//
	//		Mono<TravelPlanningCriteria> criteriaMono =  criteriaService.search(searchTerm);
	//		criteriaMono.map(criteria -> {
	//			// emit event "get criteria"
	//			Mono<DestinationOptions> destinationMono =  destinationService.search(criteria);
	//			destinationMono.map(destinations -> {
	//				// emit event "get destination"
	//				var optimalDestination = destinations.getOptions().get(0);
	//				var criteriaWithDestination = criteria.toBuilder()
	//						.destinationCode(optimalDestination.getCode())
	//						.build();
	//				Mono<FlightPlanOptions> flightMono =  flightService.search(criteriaWithDestination);
	//				Mono<AccommodationOptions> accommodationMono =  accommodationService.search(criteriaWithDestination);
	//				Mono<ActivityOptions> activityMono =  activityService.search(criteriaWithDestination);
	//				Mono<TravelTips> tipMono =  tipService.search(criteriaWithDestination);
	//
	//				flightMono.map(flightPlanOptions -> {
	//					// emit event "get flightPlanOptions"
	//				})
	//				accommodationMono.map(accommodationOptions -> {
	//					// emit event "get accommodationOptions"
	//				})
	//				activityMono.map(activityOptions -> {
	//					// emit event "get activityOptions"
	//				})
	//				tipMono.map(travelTips -> {
	//					// emit event "get travelTips"
	//				})
	//
	//				// after all emit event "finished"
	//
	//			});
	//		});
	//
	//	}

}
