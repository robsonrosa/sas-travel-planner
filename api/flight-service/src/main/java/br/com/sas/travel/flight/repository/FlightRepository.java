package br.com.sas.travel.flight.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.flight.entity.FlightEntity;
import reactor.core.publisher.Flux;

@Repository
public interface FlightRepository extends ReactiveCosmosRepository<FlightEntity, String> {

	Flux<FlightEntity> findByArrivalAirportCode(String airport);

	Flux<FlightEntity> findByDepartureAirportCode(String airport);

}
