package br.com.sas.travel.planner.service;

import static br.com.sas.travel.planner.model.TravelDataType.CRITERIA;
import static br.com.sas.travel.planner.model.TravelDataType.OPTIMAL;
import static java.util.EnumSet.complementOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sas.travel.accommodation.model.AccommodationOptions;
import br.com.sas.travel.accommodation.service.AccommodationService;
import br.com.sas.travel.activity.model.ActivityOptions;
import br.com.sas.travel.activity.service.ActivityService;
import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.criteria.service.CriteriaService;
import br.com.sas.travel.destination.model.DestinationOptions;
import br.com.sas.travel.destination.service.DestinationService;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import br.com.sas.travel.flight.service.FlightService;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.planner.model.TravelDataType;
import br.com.sas.travel.planner.model.TravelPlanning;
import br.com.sas.travel.tips.model.TravelTips;
import br.com.sas.travel.tips.service.TipService;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@ExtendWith(MockitoExtension.class)
class TravelServiceTest {

	@InjectMocks
	private TravelService service;

	@Mock
	private CriteriaService criteriaService;
	@Mock
	private DestinationService destinationService;
	@Mock
	private FlightService flightService;
	@Mock
	private AccommodationService accommodationService;
	@Mock
	private ActivityService activityService;
	@Mock
	private TipService tipService;

	public void setupFor(String searchTerm) {
		var factory = new PodamFactoryImpl();

		var criteria = TravelPlanningCriteria.builder().searchTerm(searchTerm).build();
		var destinationOptions = factory.manufacturePojo(DestinationOptions.class);
		var flightOptions = factory.manufacturePojo(FlightPlanOptions.class);
		var accommodationOptions = factory.manufacturePojo(AccommodationOptions.class);
		var activityOptions = factory.manufacturePojo(ActivityOptions.class);
		var travelTips = factory.manufacturePojo(TravelTips.class);

		when(criteriaService.search(searchTerm)).thenReturn(Mono.just(criteria));
		when(destinationService.search(any(TravelPlanningCriteria.class))).thenReturn(Mono.just(destinationOptions));
		when(flightService.search(any(TravelPlanningCriteria.class))).thenReturn(Mono.just(flightOptions));
		when(accommodationService.search(any(TravelPlanningCriteria.class))).thenReturn(Mono.just(accommodationOptions));
		when(activityService.search(any(TravelPlanningCriteria.class))).thenReturn(Mono.just(activityOptions));
		when(tipService.search(any(TravelPlanningCriteria.class))).thenReturn(Mono.just(travelTips));
	}

	@Test
	public void shouldAllEventsInCorrectOrder() {

		var searchTerm = "teste";
		setupFor(searchTerm);

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