package br.com.sas.travel.flight.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.flight.entity.AirportEntity;
import br.com.sas.travel.flight.model.Airport;

@Repository
public interface AirportRepository extends ReactiveCosmosRepository<AirportEntity, String> {

}
