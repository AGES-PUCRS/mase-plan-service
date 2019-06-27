package br.pucrs.ages.mase.plan_service.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.pucrs.ages.mase.plan_service.client.NotificationClient;
import br.pucrs.ages.mase.plan_service.client.UserClient;
import br.pucrs.ages.mase.plan_service.dto.DisasterAreaDto;
import br.pucrs.ages.mase.plan_service.dto.DisasterNotificationDto;
import br.pucrs.ages.mase.plan_service.dto.PlanExecutionDto;
import br.pucrs.ages.mase.plan_service.dto.VolunteerDto;
import br.pucrs.ages.mase.plan_service.dto.VolunteersNeededDto;
import br.pucrs.ages.mase.plan_service.entity.PlanExecution;
import br.pucrs.ages.mase.plan_service.repository.PlanExecutionRepository;
import br.pucrs.ages.mase.plan_service.repository.PlanTemplateRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class PlanServiceTest {
	
	private final PlanService planService;
	private final PlanTemplateRepository planTemplateRepository;
	private final PlanExecutionRepository planExecutionRepository;
	private final UserClient userClient;
	private final NotificationClient notificationClient;
	private final ObjectMapper objectMapper;
	
	public PlanServiceTest() {
		planTemplateRepository = mock(PlanTemplateRepository.class);
		planExecutionRepository = mock(PlanExecutionRepository.class);
		userClient = mock(UserClient.class);
		notificationClient = mock(NotificationClient.class);
		objectMapper = new ObjectMapper();
		planService = new PlanService(planTemplateRepository, planExecutionRepository, userClient, notificationClient, objectMapper);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testPlanExecutionSuccess() {
		PlanExecutionDto expected = planExecution();
		when(userClient.getAllByOccupation(anyString())).thenReturn(volunteers());
		when(planExecutionRepository.save(any(PlanExecution.class)))
				.thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
		StepVerifier.create(planService.execute(expected))
				.assertNext(actual -> assertEquals(expected, actual))
				.verifyComplete();
		verify(notificationClient).notify(any(Flux.class));
	}
	
	private PlanExecutionDto planExecution() {
		return new PlanExecutionDto("description", disasterNotifications(), LocalDateTime.now(),
				new DisasterAreaDto(BigDecimal.valueOf(1L), BigDecimal.valueOf(1L)));
	}
	
	private Map<Integer, DisasterNotificationDto> disasterNotifications() {
		Map<Integer, DisasterNotificationDto> disasterNotifications = new HashMap<>();
		disasterNotifications.put(0, new DisasterNotificationDto(Arrays.asList(new VolunteersNeededDto("doctor", 5))));
		return disasterNotifications;
	}
	
	private Flux<VolunteerDto> volunteers() {
		return Flux.just(new VolunteerDto("1"), new VolunteerDto("2"), new VolunteerDto("3"));
	}
	
	private void assertEquals(PlanExecutionDto expected, PlanExecutionDto actual) {
		assert expected.getDescription().equals(actual.getDescription());
		assert expected.getDisasterArea().getLatitude().equals(actual.getDisasterArea().getLatitude());
		assert expected.getDisasterArea().getLongitude().equals(actual.getDisasterArea().getLongitude());
		assert expected.getStartTime().equals(actual.getStartTime());
		assertEquals(expected.getDisasterNotifications(), actual.getDisasterNotifications());
	}
	
	private void assertEquals(Map<Integer, DisasterNotificationDto> expected, Map<Integer, DisasterNotificationDto> actual) {
		assert expected.size() == actual.size();
		expected.forEach((k,v) -> {
			assert actual.containsKey(k);
			assertEquals(expected.get(k), v);
		});
	}
	
	private void assertEquals(DisasterNotificationDto expected, DisasterNotificationDto actual) {
		assert expected.getVolunteersNeeded().size() == actual.getVolunteersNeeded().size();
		Iterator<VolunteersNeededDto> expectedVolunteersNeeded = expected.getVolunteersNeeded().iterator();
		Iterator<VolunteersNeededDto> actualVolunteersNeeded = actual.getVolunteersNeeded().iterator();
		while(expectedVolunteersNeeded.hasNext()) {
			assertEquals(expectedVolunteersNeeded.next(), actualVolunteersNeeded.next());
		}
	}
	
	private void assertEquals(VolunteersNeededDto expected, VolunteersNeededDto actual) {
		assert expected.getNumberOfVolunteersNeeded().equals(actual.getNumberOfVolunteersNeeded());
		assert expected.getOccupationNeeded().equals(actual.getOccupationNeeded());
	}
	
}
