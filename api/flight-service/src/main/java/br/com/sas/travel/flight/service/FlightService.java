package br.com.sas.travel.flight.service;

import static br.com.sas.travel.flight.service.RandomOrderComparator.rating;
import static br.com.sas.travel.flight.service.RandomOrderComparator.score;

import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.flight.model.Flight;
import br.com.sas.travel.flight.model.FlightPlan;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import br.com.sas.travel.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightService {

	private final FlightRepository repository;

	public Mono<FlightPlanOptions> search(TravelPlanningCriteria criteria) {
		return searchBasedOnCriteria(criteria)
				.collectList()
				.map(plans -> FlightPlanOptions.builder()
						.criteria(criteria)
						.options(plans)
						.build());
	}

	private Flux<FlightPlan> searchBasedOnCriteria(TravelPlanningCriteria criteria) {
		return searchOutwardFlightsBasedOnCriteria(criteria)
				.zipWith(searchReturnFlightsBasedOnCriteria(criteria))
				.map(flights -> FlightPlan.builder()
						.outwardFlight(flights.getT1())
						.returnFlight(flights.getT2())
						.score(score())
						.rating(rating())
						.build());
	}

	private Flux<Flight> searchReturnFlightsBasedOnCriteria(TravelPlanningCriteria criteria) {
		return repository.findByDepartureAirport_Location(criteria.getDestinationCode());
	}

	private Flux<Flight> searchOutwardFlightsBasedOnCriteria(TravelPlanningCriteria criteria) {
		return repository.findByArrivalAirport_Location(criteria.getDestinationCode());
	}

}