package br.com.sas.travel.flight.service;

import static br.com.sas.travel.flight.service.RandomOrderComparator.rating;
import static br.com.sas.travel.flight.service.RandomOrderComparator.score;

import java.util.function.Function;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.flight.entity.FlightEntity;
import br.com.sas.travel.flight.model.Airport;
import br.com.sas.travel.flight.model.Flight;
import br.com.sas.travel.flight.model.FlightPlan;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import br.com.sas.travel.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@RequiredArgsConstructor
public class FlightService {

	private final FlightMapper mapper = FlightMapper.INSTANCE;

	private final FlightRepository repository;

	private final AirportService airportService;

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

	// TODO: should consider the user location - just trying to simplify business logic here
	private Flux<Flight> searchReturnFlightsBasedOnCriteria(TravelPlanningCriteria criteria) {
		return repository.findByDepartureAirportCode(criteria.getDestinationCode())
				.flatMap(joiningWithAirports());
	}

	// TODO: should consider the user location - just trying to simplify business logic here
	private Flux<Flight> searchOutwardFlightsBasedOnCriteria(TravelPlanningCriteria criteria) {
		return repository.findByArrivalAirportCode(criteria.getDestinationCode())
				.flatMap(joiningWithAirports());
	}

	private Function<FlightEntity, Publisher<? extends Flight>> joiningWithAirports() {
		return entity -> {
			var departureMono = airportService.getByCode(entity.getDepartureAirportCode());
			var arrivalMono = airportService.getByCode(entity.getArrivalAirportCode());
			return departureMono.zipWith(arrivalMono)
					.map(toModelWithAirportsAnd(entity));
		};
	}

	private Function<Tuple2<Airport, Airport>, Flight> toModelWithAirportsAnd(FlightEntity entity) {
		return airports -> mapper.map(entity, airports.getT1(), airports.getT2());
	}

}