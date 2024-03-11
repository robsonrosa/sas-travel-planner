package br.com.sas.travel.destination.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sas.travel.destination.model.Destination;
import reactor.test.StepVerifier;

@SpringBootTest
class DestinationRepositoryTest {

	@Autowired
	DestinationRepository destinationRepository;

	@AfterEach
	public void cleanUp() {
		destinationRepository.deleteAll().subscribe();
	}

	@Test
	public void givenDestinationIsCreated_whenCallFindByCode_thenDestinationIsFound() {

		var destination = new Destination();
		destination.setDestination("Brazil");
		destination.setRating(5.0);
		destination.setScore(4.5);
		destination.setCode("BR");

		var result = destinationRepository.save(destination)
				.then(destinationRepository.findById("BR"));

		StepVerifier.create(result)
				.expectNext(destination)
				.verifyComplete();
	}

}