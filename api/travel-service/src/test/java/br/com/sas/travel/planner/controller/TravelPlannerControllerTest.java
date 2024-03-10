package br.com.sas.travel.planner.controller;

import static br.com.sas.travel.planner.api.model.TravelPlanningResponse.TypeEnum.CRITERIA;
import static br.com.sas.travel.planner.api.model.TravelPlanningResponse.TypeEnum.OPTIMAL;
import static java.util.EnumSet.complementOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.EnumSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sas.travel.planner.TravelServiceApplication;
import br.com.sas.travel.planner.api.model.TravelPlanningRequest;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import reactor.test.StepVerifier;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest(classes = { TravelServiceApplication.class },
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TravelPlannerControllerTest {

	@LocalServerPort
	private int port;

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