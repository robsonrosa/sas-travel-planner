package br.com.sas.travel.planner.controller;

import org.openapitools.api.TravelApi;
import org.openapitools.model.TravelPlanningCriteria;
import org.openapitools.model.TravelPlanningRequest;
import org.openapitools.model.TravelPlanningResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class TravelPlannerController implements TravelApi {

	@Override public Mono<ResponseEntity<Flux<TravelPlanningResponse>>> travelPost(Mono<TravelPlanningRequest> travelPlanningRequest,
			ServerWebExchange exchange) {
		return TravelApi.super.travelPost(travelPlanningRequest, exchange);
	}

	public ResponseEntity<TravelPlanningResponse> travelPost(TravelPlanningRequest request) {
		return ResponseEntity.ok(new TravelPlanningResponse()
				.type(TravelPlanningResponse.TypeEnum.CRITERIA)
				.data(new TravelPlanningCriteria()
						.searchTerm(request.getSearchTerm())));

	}
}

