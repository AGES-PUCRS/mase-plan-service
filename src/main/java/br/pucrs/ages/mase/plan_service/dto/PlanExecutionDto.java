package br.pucrs.ages.mase.plan_service.dto;

import java.time.LocalDateTime;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlanExecutionDto extends PlanTemplateDto {
	
	private @NotNull LocalDateTime startTime;
	private @Valid @NotNull DisasterAreaDto disasterArea;
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public DisasterAreaDto getDisasterArea() {
		return disasterArea;
	}
	
}
