package br.com.sas.travel.destination.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.destination.entity.DestinationEntity;
import br.com.sas.travel.destination.model.Destination;
import reactor.core.publisher.Flux;

@Repository
public interface DestinationRepository extends ReactiveCosmosRepository<DestinationEntity, String> {

	Flux<DestinationEntity> findByDestination(String destination);

}
