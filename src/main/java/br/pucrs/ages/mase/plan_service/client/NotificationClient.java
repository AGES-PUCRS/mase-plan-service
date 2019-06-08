package br.pucrs.ages.mase.plan_service.client;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import br.pucrs.ages.mase.plan_service.dto.NotificationDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class NotificationClient {
	
	private @Value("${notification.title}") String title;
	private @Value("${notification.body}") String body;
	
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
		return new NotificationDto(mobileId, title, body);
	}
	
}
