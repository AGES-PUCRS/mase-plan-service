package br.pucrs.ages.mase.plan_service.client;

import java.nio.charset.Charset;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import br.pucrs.ages.mase.plan_service.dto.NotificationDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class NotificationClient {
	
	private static final String TITLE = "Precisamos de você!";
	private static final String BODY = "Está ocorrendo um desastre, e precisamos da sua ajuda como voluntário!";
	
	public Mono<ClientResponse> notify(Flux<String> mobileIds) {
		return WebClient.create("https://exp.host")
				.post()
				.uri("/--/api/v2/push/send")
				.acceptCharset(Charset.forName("UTF-8"))
				.body(toNotifications(mobileIds), NotificationDto.class)
				.exchange();
	}
	
	private Flux<NotificationDto> toNotifications(Flux<String> mobileIds) {
		return mobileIds.map(this::toNotification);
	}
	
	private NotificationDto toNotification(String mobileId) {
		return new NotificationDto(mobileId, TITLE, BODY);
	}
	
}
