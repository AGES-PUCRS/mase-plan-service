package br.pucrs.ages.mase.plan_service.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.pucrs.ages.mase.plan_service.client.UserClient;
import br.pucrs.ages.mase.plan_service.dto.PlanExecutionDto;
import br.pucrs.ages.mase.plan_service.dto.PlanTemplateDto;
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
	private final ObjectMapper objectMapper;
	
	public PlanService(PlanTemplateRepository planTemplateRepository, PlanExecutionRepository planExecutionRepository,
			UserClient userClient, ObjectMapper objectMapper) {
		this.planTemplateRepository = planTemplateRepository;
		this.planExecutionRepository = planExecutionRepository;
		this.userClient = userClient;
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
		return Mono.error(new RuntimeException());
	}
	
}
