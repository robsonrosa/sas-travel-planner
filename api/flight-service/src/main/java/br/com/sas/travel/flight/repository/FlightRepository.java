package br.com.sas.travel.flight.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.flight.model.Flight;
import reactor.core.publisher.Flux;

@Repository
public interface FlightRepository extends ReactiveCosmosRepository<Flight, String> {

	Flux<Flight> findByArrivalAirport_Location(String airport);

	Flux<Flight> findByDepartureAirport_Location(String airport);

}
