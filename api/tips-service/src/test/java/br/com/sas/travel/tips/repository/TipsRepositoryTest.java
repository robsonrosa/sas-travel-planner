package br.com.sas.travel.tips.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sas.travel.tips.model.TravelTip;
import reactor.test.StepVerifier;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@SpringBootTest
class TipsRepositoryTest {

	@Autowired
	TipsRepository repository;

	@AfterEach
	public void cleanUp() {
		repository.deleteAll().subscribe();
	}

	@Test
	public void givenEntityIsCreated_whenCallFindById_thenEntityIsFound() {

		var factory = new PodamFactoryImpl();
		var entity = factory.manufacturePojo(TravelTip.class);
		var id = entity.getId();

		var result = repository.save(entity)
				.then(repository.findById(id));

		StepVerifier.create(result)
				.expectNext(entity)
				.verifyComplete();
	}

}