package br.com.sas.travel.flight.service;

import org.springframework.stereotype.Service;

import br.com.sas.travel.flight.model.Airport;
import br.com.sas.travel.flight.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AirportService {

	private final AirportMapper mapper = AirportMapper.INSTANCE;

	private final AirportRepository repository;

	// TODO: add memory cache
	public Mono<Airport> getByCode(String code) {
		return repository.findById(code)
				.map(mapper::map);
	}

}
