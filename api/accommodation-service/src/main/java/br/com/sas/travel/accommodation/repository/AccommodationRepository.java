package br.com.sas.travel.accommodation.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.accommodation.entity.AccommodationEntity;
import br.com.sas.travel.accommodation.model.Accommodation;
import reactor.core.publisher.Flux;

@Repository
public interface AccommodationRepository extends ReactiveCosmosRepository<AccommodationEntity, String> {

	Flux<AccommodationEntity> findByAddress(String address);

}
