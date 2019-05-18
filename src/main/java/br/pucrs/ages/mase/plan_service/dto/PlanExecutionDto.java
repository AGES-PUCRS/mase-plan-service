package br.pucrs.ages.mase.plan_service.dto;

import java.time.LocalDateTime;

public class PlanExecutionDto extends PlanTemplateDto {
	
	private LocalDateTime startTime;
	private DisasterAreaDto disasterArea;
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public DisasterAreaDto getDisasterArea() {
		return disasterArea;
	}
	
}
