package br.com.sas.travel.planner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import br.com.sas.travel.planner.api.TravelApiDelegate;
import br.com.sas.travel.planner.api.model.TravelPlanningRequest;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.planner.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class TravelPlannerController implements TravelApiDelegate {

	private final TravelService service;

	private final TravelPlanningMapper mapper;

	@Override
	public Mono<ResponseEntity<Flux<TravelPlanningResponse>>> travelPost(Mono<TravelPlanningRequest> travelPlanningRequest,
			ServerWebExchange exchange) {

		return travelPlanningRequest
				.map(TravelPlanningRequest::getSearchTerm)
				.map(service::plan)
				.map(mapper::apply)
				.map(ResponseEntity::ok);
	}
}