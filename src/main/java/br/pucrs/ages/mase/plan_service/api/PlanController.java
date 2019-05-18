package br.pucrs.ages.mase.plan_service.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.pucrs.ages.mase.plan_service.dto.PlanExecutionDto;
import br.pucrs.ages.mase.plan_service.dto.PlanTemplateDto;
import br.pucrs.ages.mase.plan_service.service.PlanService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
	
	private final PlanService planService;
	
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@GetMapping("/template")
	public Flux<PlanTemplateDto> getAllPlanTemplates() {
		return planService.getAllPlanTemplates();
	}
	
	@PostMapping("/template")
	public Mono<PlanTemplateDto> insert(@Valid @RequestBody PlanTemplateDto planTemplateDto) {
		return planService.insert(planTemplateDto);
	}
	
	@PostMapping("/execution")
	public Mono<PlanExecutionDto> execute(@Valid @RequestBody PlanExecutionDto planExecutionDto) {
		return planService.execute(planExecutionDto);
	}
	
}
