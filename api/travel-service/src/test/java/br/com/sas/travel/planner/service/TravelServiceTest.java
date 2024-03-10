package br.com.sas.travel.planner.service;

import static br.com.sas.travel.planner.model.TravelDataType.CRITERIA;
import static br.com.sas.travel.planner.model.TravelDataType.OPTIMAL;
import static java.util.EnumSet.complementOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.planner.model.TravelDataType;
import br.com.sas.travel.planner.model.TravelPlanning;
import reactor.test.StepVerifier;

class TravelServiceTest {

	@Test
	public void shouldAllEventsInCorrectOrder() {

		var service = new TravelService();
		var result = service.plan("teste");

		var responseTypes = EnumSet.noneOf(TravelDataType.class);
		Function<EnumSet<TravelDataType>, Predicate<TravelPlanning<?>>> validator = (acceptableTypes) -> (response) -> {
			var validDataNonDuplicatedType = response.getData() != null && !responseTypes.contains(response.getType());
			responseTypes.add(response.getType());
			return validDataNonDuplicatedType && acceptableTypes.contains(response.getType());
		};

		// TODO: increment with data assertions
		StepVerifier.create(result)
				.expectNextMatches(validator.apply(EnumSet.of(CRITERIA)))
				.expectNextMatches(validator.apply(complementOf(EnumSet.of(CRITERIA, OPTIMAL))))
				.expectNextMatches(validator.apply(complementOf(EnumSet.of(CRITERIA, OPTIMAL))))
				.expectNextMatches(validator.apply(complementOf(EnumSet.of(CRITERIA, OPTIMAL))))
				.expectNextMatches(validator.apply(complementOf(EnumSet.of(CRITERIA, OPTIMAL))))
				.expectNextMatches(validator.apply(complementOf(EnumSet.of(CRITERIA, OPTIMAL))))
				.expectNextMatches(validator.apply(EnumSet.of(OPTIMAL)))
				.verifyComplete();

		assertThat(responseTypes)
				.hasSize(EnumSet.allOf(TravelPlanningResponse.TypeEnum.class).size());

	}

}