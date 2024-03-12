package br.com.sas.travel.flight.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.sas.travel.flight.entity.AirportEntity;
import br.com.sas.travel.flight.entity.FlightEntity;
import br.com.sas.travel.flight.model.Flight;
import uk.co.jemos.podam.api.PodamFactoryImpl;

class FlightMapperTest {

	private final FlightMapper flightMapper = FlightMapper.INSTANCE;
	private final AirportMapper airportMapper = AirportMapper.INSTANCE;

	@Test
	public void shouldMapFlightAndItsAirports() {
		var factory = new PodamFactoryImpl();
		var flightEntity = factory.manufacturePojo(FlightEntity.class);
		var arrivalAirportEntity = factory.manufacturePojo(AirportEntity.class);
		var departureAirportEntity = factory.manufacturePojo(AirportEntity.class);

		var departureAirport = airportMapper.map(departureAirportEntity);
		var arrivalAirport = airportMapper.map(arrivalAirportEntity);
		var model = flightMapper.map(flightEntity, departureAirport, arrivalAirport);

		Assertions.assertThat(model)
				.returns(flightEntity.getCode(), Flight::getCode)
				.returns(arrivalAirportEntity.getAddress(), m -> m.getArrivalAirport().getAddress())
				.returns(departureAirportEntity.getAddress(), m -> m.getDepartureAirport().getAddress());
	}

}