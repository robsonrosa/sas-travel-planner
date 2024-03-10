package br.com.sas.travel.planner.controller;

import org.openapitools.api.TravelApi;
import org.openapitools.model.TravelPlanningCriteria;
import org.openapitools.model.TravelPlanningRequest;
import org.openapitools.model.TravelPlanningResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TravelController implements TravelApi {

	@Override
	public ResponseEntity<TravelPlanningResponse> travelPost(TravelPlanningRequest request) {
		return ResponseEntity.ok(new TravelPlanningResponse()
				.type(TravelPlanningResponse.TypeEnum.CRITERIA)
				.data(new TravelPlanningCriteria()
						.searchTerm(request.getSearchTerm())));

	}
}
