package br.com.sas.travel.accommodation.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sas.travel.accommodation.entity.AccommodationEntity;
import reactor.test.StepVerifier;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
class AccommodationRepositoryTest {

	@Autowired
	AccommodationRepository repository;

	@AfterEach
	public void cleanUp() {
		StepVerifier.create(repository.deleteAll()).verifyComplete();
	}

	@Test
	public void givenEntityIsCreated_whenCallFindById_thenEntityIsFound() {

		var factory = new PodamFactoryImpl();
		var entity = factory.manufacturePojo(AccommodationEntity.class);
		var id = entity.getCode();

		var result = repository.save(entity)
				.then(repository.findById(id));

		StepVerifier.create(result)
				.expectNext(entity)
				.verifyComplete();
	}

}