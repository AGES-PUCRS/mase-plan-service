package br.pucrs.ages.mase.plan_service.client;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import br.pucrs.ages.mase.plan_service.dto.VolunteerDto;
import reactor.core.publisher.Flux;

@Component
public class UserClient {
	
	public Flux<VolunteerDto> getAllByOccupation(String occupation) {
		return WebClient.create("http://localhost:8080")
				.get()
				.uri("/api/user/volunteers/ocupation/".concat(occupation))
				.retrieve()
				.bodyToFlux(VolunteerDto.class);
	}
	
}
