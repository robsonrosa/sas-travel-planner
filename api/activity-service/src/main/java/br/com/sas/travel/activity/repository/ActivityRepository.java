package br.com.sas.travel.activity.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.activity.entity.ActivityEntity;
import reactor.core.publisher.Flux;

@Repository
public interface ActivityRepository extends ReactiveCosmosRepository<ActivityEntity, String> {

	Flux<ActivityEntity> findByAddress(String address);

}
