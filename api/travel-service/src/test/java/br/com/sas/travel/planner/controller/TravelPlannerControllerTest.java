package br.com.sas.travel.planner.controller;

import java.util.EnumSet;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sas.travel.planner.api.model.TravelPlanningRequest;
import br.com.sas.travel.planner.api.model.TravelPlanningResponse;
import br.com.sas.travel.planner.TravelServiceApplication;
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

		EnumSet<TravelPlanningResponse.TypeEnum> responseTypes = EnumSet.noneOf(TravelPlanningResponse.TypeEnum.class);
		Predicate<TravelPlanningResponse> validator = (response) -> {
			var nonDuplicatedType = !responseTypes.contains(response.getType());
			responseTypes.add(response.getType());
			return nonDuplicatedType;
		};

		StepVerifier.create(responseFlux)
				.expectNextMatches(validator)
				.expectNextMatches(validator)
				.expectNextMatches(validator)
				.expectNextMatches(validator)
				.expectNextMatches(validator)
				.expectNextMatches(validator)
				.expectNextMatches(validator)
				.verifyComplete();

		Assertions.assertThat(responseTypes).hasSize(EnumSet.allOf(TravelPlanningResponse.TypeEnum.class).size());
	}

	private UriComponentsBuilder getUriBuilder(String path) {
		return UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/api/v1" + path);
	}

	private <R, T> Mono<ResponseEntity<R>> postForMono(String url, T payload, Class<T> requestClass, Class<R> responseClass) {
		return buildTestWebClient()
				.post()
				.uri(url)
				.headers(getHttpHeadersConsumer())
				.body(BodyInserters.fromPublisher(Mono.just(payload), requestClass))
				.retrieve()
				.onStatus(httpStatus -> !httpStatus.is2xxSuccessful() && !httpStatus.equals(HttpStatus.BAD_REQUEST),
						response -> response.bodyToMono(String.class).map(IllegalStateException::new))
				.toEntity(responseClass);
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
				.filters(exchangeFilterFunctions -> {
					exchangeFilterFunctions.add(logRequest());
					exchangeFilterFunctions.add(logResponse());
				}).build();
	}

	private Consumer<HttpHeaders> getHttpHeadersConsumer() {
		return httpHeaders -> {
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			httpHeaders.setAccept(Lists.newArrayList(MediaType.TEXT_EVENT_STREAM));
		};
	}

	ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			StringBuilder sb = new StringBuilder("Request: \n");
			clientRequest
					.headers()
					.forEach((name, values) -> values.forEach(value -> sb.append(name).append(" : ").append(value)));
			log.info(sb.toString());
			return Mono.just(clientRequest);
		});
	}

	ExchangeFilterFunction logResponse() {
		return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
			StringBuilder sb = new StringBuilder("Response: \n");
			clientResponse
					.headers().asHttpHeaders()
					.forEach((name, values) -> values.forEach(value -> sb.append(name).append(" : ").append(value)));
			log.info(sb.toString());
			return Mono.just(clientResponse);
		});
	}
}