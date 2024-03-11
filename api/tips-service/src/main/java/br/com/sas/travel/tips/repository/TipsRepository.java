package br.com.sas.travel.tips.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.tips.model.TravelTip;
import reactor.core.publisher.Flux;

@Repository
public interface TipsRepository extends ReactiveCosmosRepository<TravelTip, String> {

	Flux<TravelTip> findByDestinationCode(String destinationCode);

}
