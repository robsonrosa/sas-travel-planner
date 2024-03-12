package br.com.sas.travel.flight.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sas.travel.criteria.model.TravelPlanningCriteria;
import br.com.sas.travel.flight.entity.AirportEntity;
import br.com.sas.travel.flight.entity.FlightEntity;
import br.com.sas.travel.flight.model.FlightPlanOptions;
import br.com.sas.travel.flight.repository.AirportRepository;
import br.com.sas.travel.flight.repository.FlightRepository;
import reactor.test.StepVerifier;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
class FlightServiceTest {

	@Autowired
	private FlightService service;

	@BeforeAll
	public static void setup(@Autowired FlightRepository flightRepository, @Autowired AirportRepository airportRepository) {
		var factory = new PodamFactoryImpl();

		var airportABC = factory.manufacturePojo(AirportEntity.class);
		var airportDEF = factory.manufacturePojo(AirportEntity.class);
		var airportGHI = factory.manufacturePojo(AirportEntity.class);
		var airportJKL = factory.manufacturePojo(AirportEntity.class);
		var airportMNO = factory.manufacturePojo(AirportEntity.class);

		airportABC.setCode("ABC");
		airportDEF.setCode("DEF");
		airportGHI.setCode("GHI");
		airportJKL.setCode("JKL");
		airportMNO.setCode("MNO");

		var flightABCToDEF = factory.manufacturePojo(FlightEntity.class);
		var flightDEFToGHI = factory.manufacturePojo(FlightEntity.class);
		var flightGHIToJKL = factory.manufacturePojo(FlightEntity.class);
		var flightJKLToMNO = factory.manufacturePojo(FlightEntity.class);
		var flightMNOToABC = factory.manufacturePojo(FlightEntity.class);

		flightABCToDEF.setDepartureAirportCode("ABC");
		flightABCToDEF.setArrivalAirportCode("DEF");
		flightDEFToGHI.setDepartureAirportCode("DEF");
		flightDEFToGHI.setArrivalAirportCode("GHI");
		flightGHIToJKL.setDepartureAirportCode("GHI");
		flightGHIToJKL.setArrivalAirportCode("JKL");
		flightJKLToMNO.setDepartureAirportCode("JKL");
		flightJKLToMNO.setArrivalAirportCode("MNO");
		flightMNOToABC.setDepartureAirportCode("MNO");
		flightMNOToABC.setArrivalAirportCode("ABC");

		StepVerifier.create(airportRepository.deleteAll().then(airportRepository.saveAll(List.of(
				airportABC,
				airportDEF,
				airportGHI,
				airportJKL,
				airportMNO
		)).then())).verifyComplete();

		StepVerifier.create(flightRepository.deleteAll().then(flightRepository.saveAll(List.of(
				flightABCToDEF,
				flightDEFToGHI,
				flightGHIToJKL,
				flightJKLToMNO,
				flightMNOToABC
		)).then())).verifyComplete();
	}

	@AfterAll
	public static void cleanUp(@Autowired FlightRepository flightRepository, @Autowired AirportRepository airportRepository) {
		StepVerifier.create(flightRepository.deleteAll()).verifyComplete();
		StepVerifier.create(airportRepository.deleteAll()).verifyComplete();
	}

	@ParameterizedTest
	@ValueSource(strings = { "ABC", "DEF", "GHI", "JKL", "MNO" })
	public void shouldSearchForFlightPlansMappingToModelWithAirports(String destination) {

		var airports = List.of("ABC", "DEF", "GHI", "JKL", "MNO");
		var maxIndex = airports.size() - 1;
		var index = airports.indexOf(destination);
		var departure = airports.get(index > 0 ? index - 1 : maxIndex);
		var finalDestination = airports.get(index < maxIndex ? index + 1 : 0);
		var criteria = TravelPlanningCriteria.builder().destinationCode(destination).build();

		var result = service.search(criteria);

		StepVerifier.create(result)
				.assertNext(plan -> {
					var outwardFlight = plan.getOptions().get(0).getOutwardFlight();
					var returnFlight = plan.getOptions().get(0).getReturnFlight();
					assertThat(plan)
							.returns(criteria, FlightPlanOptions::getCriteria)
							.returns(1, p -> p.getOptions().size());
					assertThat(outwardFlight)
							.returns(departure, flight -> flight.getDepartureAirport().getCode())
							.returns(destination, flight -> flight.getArrivalAirport().getCode());
					assertThat(returnFlight)
							.returns(destination, flight -> flight.getDepartureAirport().getCode())
							.returns(finalDestination, flight -> flight.getArrivalAirport().getCode());
				})
				.verifyComplete();

	}
}