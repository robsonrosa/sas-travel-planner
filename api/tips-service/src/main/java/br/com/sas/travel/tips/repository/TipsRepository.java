package br.com.sas.travel.tips.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.tips.entity.TravelTipEntity;
import reactor.core.publisher.Flux;

@Repository
public interface TipsRepository extends ReactiveCosmosRepository<TravelTipEntity, String> {

	Flux<TravelTipEntity> findByDestinationCode(String destinationCode);

}
