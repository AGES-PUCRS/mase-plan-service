package br.pucrs.ages.mase.plan_service.service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.pucrs.ages.mase.plan_service.client.NotificationClient;
import br.pucrs.ages.mase.plan_service.client.UserClient;
import br.pucrs.ages.mase.plan_service.dto.DisasterNotificationDto;
import br.pucrs.ages.mase.plan_service.dto.PlanExecutionDto;
import br.pucrs.ages.mase.plan_service.dto.PlanTemplateDto;
import br.pucrs.ages.mase.plan_service.dto.VolunteerDto;
import br.pucrs.ages.mase.plan_service.dto.VolunteersNeededDto;
import br.pucrs.ages.mase.plan_service.entity.PlanExecution;
import br.pucrs.ages.mase.plan_service.entity.PlanTemplate;
import br.pucrs.ages.mase.plan_service.repository.PlanExecutionRepository;
import br.pucrs.ages.mase.plan_service.repository.PlanTemplateRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class PlanService {
	
	private final PlanTemplateRepository planTemplateRepository;
	private final PlanExecutionRepository planExecutionRepository;
	private final UserClient userClient;
	private final NotificationClient notificationClient;
	private final ObjectMapper objectMapper;
	
	public PlanService(PlanTemplateRepository planTemplateRepository, PlanExecutionRepository planExecutionRepository,
			UserClient userClient, NotificationClient notiticationClient, ObjectMapper objectMapper) {
		this.planTemplateRepository = planTemplateRepository;
		this.planExecutionRepository = planExecutionRepository;
		this.userClient = userClient;
		this.notificationClient = notiticationClient;
		this.objectMapper = objectMapper;
	}
	
	public Mono<PlanTemplateDto> insert(PlanTemplateDto planTemplateDto) {
		return planTemplateRepository.save(objectMapper.convertValue(planTemplateDto, PlanTemplate.class))
				.subscribeOn(Schedulers.elastic())
				.map(planTemplate -> objectMapper.convertValue(planTemplate, PlanTemplateDto.class));
	}
	
	public Flux<PlanTemplateDto> getAllPlanTemplates() {
		return planTemplateRepository.findAll()
				.subscribeOn(Schedulers.elastic())
				.map(planTemplate -> objectMapper.convertValue(planTemplate, PlanTemplateDto.class));
	}
	
	public Mono<PlanExecutionDto> execute(PlanExecutionDto planExecutionDto) {
		scheduleNotifications(planExecutionDto);
		return save(planExecutionDto);
	}
	
	private Mono<PlanExecutionDto> save(PlanExecutionDto planExecutionDto) {
		return planExecutionRepository.save(objectMapper.convertValue(planExecutionDto, PlanExecution.class))
				.subscribeOn(Schedulers.elastic())
				.map(planExecution -> objectMapper.convertValue(planExecution, PlanExecutionDto.class));
	}
	
	private void scheduleNotifications(PlanExecutionDto planExecutionDto) {
		planExecutionDto.getDisasterNotifications()
				.keySet()
				.forEach(notificationTime -> scheduleNotification(
						getMobileIds(planExecutionDto.getDisasterNotifications().get(notificationTime)),
						notificationTime));
	}
	
	private void scheduleNotification(Flux<String> mobileIds, Integer notificationTime) {
		Executors.newSingleThreadScheduledExecutor()
				.schedule(() -> notificationClient.notify(mobileIds), notificationTime, TimeUnit.SECONDS);
	}
	
	private Flux<String> getMobileIds(DisasterNotificationDto disasterNotification) {
		return Flux.fromStream(disasterNotification.getVolunteersNeeded().stream())
				.map(VolunteersNeededDto::getOccupationNeeded)
				.flatMap(userClient::getAllByOccupation)
				.map(VolunteerDto::getMobileId);
	}
	
}
