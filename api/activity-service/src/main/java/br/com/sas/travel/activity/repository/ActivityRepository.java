package br.com.sas.travel.activity.repository;

import org.springframework.stereotype.Repository;

import com.azure.spring.data.cosmos.repository.ReactiveCosmosRepository;

import br.com.sas.travel.activity.model.Activity;
import reactor.core.publisher.Flux;

@Repository
public interface ActivityRepository extends ReactiveCosmosRepository<Activity, String> {

	Flux<Activity> findByAddress(String address);

}
