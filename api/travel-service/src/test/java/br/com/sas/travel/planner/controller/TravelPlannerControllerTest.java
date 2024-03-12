package br.com.sas.travel.planner.controller;

import static br.com.sas.travel.planner.api.model.TravelPlanningResponse.TypeEnum.CRITERIA;
import static br.com.sas.travel.planner.api.model.TravelPlanningResponse.TypeEnum.OPTIMAL;
import static java.util.EnumSet.complementOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sas.travel.accommodation.entity.AccommodationEntity;
import br.com.sas.travel.accommodation.repository.AccommodationRepository;
import br.com.sas.travel.activity.entity.ActivityEntity;
import br.com.sas.travel.activity.repository.ActivityRepository;
import br.com.sas.travel.destination.entity.DestinationEntity;
import br.com.sas.travel.destination.repository.DestinationRepository;
import br.com.sas.travel.flight.entity.AirportEntity;
import br.com.sas.travel.flight.entity.FlightEntity;
import br.com.sas.travel.flight.repository.AirportRepository;
import br.com.sas.travel.flight.repository.FlightRepository;
import br.com.sas.travel.planner.TravelServiceApplication;
import br.com.sas.travel.planner.api.model.TravelPlanningRequest;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.tips.entity.TravelTipEntity;
import br.com.sas.travel.tips.repository.TipsRepository;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import reactor.test.StepVerifier;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@Slf4j
@SpringBootTest(classes = { TravelServiceApplication.class },
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TravelPlannerControllerTest {

	@LocalServerPort
	private int port;

	@BeforeAll
	public static void setup(
			@Autowired FlightRepository flightRepository,
			@Autowired AirportRepository airportRepository,
			@Autowired DestinationRepository destinationRepository,
			@Autowired AccommodationRepository accommodationRepository,
			@Autowired ActivityRepository activityRepository,
			@Autowired TipsRepository tipsRepository) {
		var factory = new PodamFactoryImpl();

		var airportABC = factory.manufacturePojo(AirportEntity.class);
		var airportDEF = factory.manufacturePojo(AirportEntity.class);
		airportABC.setCode("ABC");
		airportABC.setLocation("BR");
		airportDEF.setCode("DEF");
		airportDEF.setLocation("BR");
		StepVerifier.create(airportRepository
				.deleteAll()
				.then(airportRepository
						.saveAll(List.of(airportABC, airportDEF))
						.then())).verifyComplete();

		var flightABCToDEF = factory.manufacturePojo(FlightEntity.class);
		var flightDEFToABC = factory.manufacturePojo(FlightEntity.class);
		flightABCToDEF.setDepartureAirportCode("ABC");
		flightABCToDEF.setArrivalAirportCode("DEF");
		flightDEFToABC.setDepartureAirportCode("DEF");
		flightDEFToABC.setArrivalAirportCode("ABC");
		StepVerifier.create(flightRepository.deleteAll()
				.then(flightRepository
						.saveAll(List.of(flightABCToDEF, flightDEFToABC))
						.then())).verifyComplete();

		var destination = factory.manufacturePojo(DestinationEntity.class);
		destination.setCode("BR");
		destination.setDestination("Brazil");
		StepVerifier.create(destinationRepository.deleteAll()
				.then(destinationRepository
						.save(destination)
						.then())).verifyComplete();

		var accommodation = factory.manufacturePojo(AccommodationEntity.class);
		StepVerifier.create(accommodationRepository.deleteAll()
				.then(accommodationRepository
						.save(accommodation)
						.then())).verifyComplete();

		var activity = factory.manufacturePojo(ActivityEntity.class);
		StepVerifier.create(activityRepository.deleteAll()
				.then(activityRepository
						.save(activity)
						.then())).verifyComplete();

		var tip = factory.manufacturePojo(TravelTipEntity.class);
		StepVerifier.create(tipsRepository.deleteAll()
				.then(tipsRepository
						.save(tip)
						.then())).verifyComplete();
	}

	@AfterAll
	public static void cleanUp(
			@Autowired FlightRepository flightRepository,
			@Autowired AirportRepository airportRepository,
			@Autowired DestinationRepository destinationRepository,
			@Autowired AccommodationRepository accommodationRepository,
			@Autowired ActivityRepository activityRepository,
			@Autowired TipsRepository tipsRepository) {
		StepVerifier.create(flightRepository.deleteAll()).verifyComplete();
		StepVerifier.create(airportRepository.deleteAll()).verifyComplete();
		StepVerifier.create(destinationRepository.deleteAll()).verifyComplete();
		StepVerifier.create(accommodationRepository.deleteAll()).verifyComplete();
		StepVerifier.create(activityRepository.deleteAll()).verifyComplete();
		StepVerifier.create(tipsRepository.deleteAll()).verifyComplete();
	}

	@Test
	void travelPlanning() {
		Flux<TravelPlanningResponse> responseFlux = postForFlux(
				getUriBuilder("/travel").toUriString(),
				new TravelPlanningRequest().searchTerm("test"),
				TravelPlanningRequest.class,
				TravelPlanningResponse.class);

		var responseTypes = EnumSet.noneOf(TravelPlanningResponse.TypeEnum.class);
		Function<EnumSet<TravelPlanningResponse.TypeEnum>, Predicate<TravelPlanningResponse>> validator =
				(acceptableTypes) -> (response) -> {
					var validDataNonDuplicatedType = !responseTypes.contains(response.getType());
					responseTypes.add(response.getType());
					return validDataNonDuplicatedType && acceptableTypes.contains(response.getType());
				};

		// TODO: increment with data assertions
		StepVerifier.create(responseFlux)
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

	private UriComponentsBuilder getUriBuilder(String path) {
		return UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/api/v1" + path);
	}

	private <R, T> Flux<T> postForFlux(String url, R payload, Class<R> requestClass, Class<T> responseClass) {
		return buildTestWebClient()
				.post()
				.uri(url)
				.headers(getHttpHeadersConsumer())
				.body(BodyInserters.fromPublisher(Mono.just(payload), requestClass))
				.retrieve()
				.bodyToFlux(responseClass).onErrorMap(DecodingException.class,
						e -> new IllegalStateException("Problem parsing response flux", e));
	}

	private WebClient buildTestWebClient() {
		return WebClient.builder()
				.clientConnector(new ReactorClientHttpConnector(HttpClient.create()
						.wiretap(this.getClass().getCanonicalName(), LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL)))
				.build();
	}

	private Consumer<HttpHeaders> getHttpHeadersConsumer() {
		return httpHeaders -> {
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setAccept(Lists.newArrayList(MediaType.TEXT_EVENT_STREAM));
		};
	}

}